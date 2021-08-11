package com.chenyangqi.hostfix

import android.content.Context
import android.os.Environment
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.reflect.Array


object HotFixUtil {

    fun install(context: Context) {
        getFixDexFile(context)?.let {
            Log.d("HotFixUtil", "存在补丁文件:${it}，执行热修复")
            val fixedDexFile = File(it)
            if (!fixedDexFile.exists()) {
                return
            }
            val pathListField = ReflectUtil.findField(
                context.classLoader,
                "pathList"
            )
            val dexPathList = pathListField.get(context.classLoader)
            dexPathList ?: let {
                return
            }
            //DexPathList类里面的makeDexElements方法
            val makeDexElements = ReflectUtil.findMethod(
                dexPathList,
                "makeDexElements",
                List::class.java,
                File::class.java,
                List::class.java,
                ClassLoader::class.java
            )

            //把要加载的补丁文件，添加到另一个列表中
            val filesToBeInstalled = ArrayList<File>()
            filesToBeInstalled.add(fixedDexFile)

            //准备调用makeDexElements方法所需要的其他参数
            val optimizedDirectory = File(context.filesDir, "fixed_dex")
            val suppressedExceptions = ArrayList<IOException>()

            // 得到 待修复Dex 对应的 Element[]
            val extraElements = makeDexElements.invoke(
                dexPathList,
                filesToBeInstalled,
                optimizedDirectory,
                suppressedExceptions,
                context.classLoader
            ) as kotlin.Array<*>

            // 获取 原始的 Element[]
            val dexElementsField = ReflectUtil.findField(
                dexPathList, "dexElements"
            )
            val originalElements = dexElementsField.get(
                dexPathList
            ) as kotlin.Array<*>
            // 创建一个新的数组，用于合并
            val combinedElements = Array.newInstance(
                originalElements.javaClass.componentType,
                originalElements.size + extraElements.size
            ) as kotlin.Array<*>

            // 合并新旧数组
            System.arraycopy(
                extraElements,
                0,
                combinedElements,
                0,
                extraElements.size
            )
            System.arraycopy(
                originalElements,
                0,
                combinedElements,
                extraElements.size,
                originalElements.size
            )

            // 替换系统原来的 dexElements 数组
            dexElementsField.set(dexPathList, combinedElements)

        } ?: let {
            Log.d("HotFixUtil", "不存在补丁文件!")
        }
    }

    /**
     * 获取assets下的补丁文件,并复制到沙盒中返回文件地址
     */
    private fun getFixDexFile(context: Context): String? {
        val assetsInputStream = context.assets.open("fixed.dex")
        if (assetsInputStream.available() <= 0) {
            return null
        }
        val downloadFileDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
        val file = File(
            "${downloadFileDir?.absolutePath}${File.separator}fixed_${System.currentTimeMillis()}.dex"
        )
        if (!file.exists()) {
            file.createNewFile()
        }
        val fileOutput = FileOutputStream(file)
        var read: Int
        val bytes = ByteArray(1024)

        while ((assetsInputStream.read(bytes).also { read = it }) != -1) {
            fileOutput.write(bytes, 0, read)
        }
        return file.absolutePath
    }
}