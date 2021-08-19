package com.chenyangqi.pdf.preview

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.Environment
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView.layoutManager = LinearLayoutManager(this)
        var adapter: MyAdapter

        Thread {
            val filePath = copyFileToSdcard(this)
            startActivity(Intent(this,TestActivity::class.java))
//            filePath?.let {
//                val odfFile = File(it)
//                val pdfDescriptor =
//                    ParcelFileDescriptor.open(odfFile, ParcelFileDescriptor.MODE_READ_ONLY)
//                val pdfRender = PdfRenderer(pdfDescriptor)
//                val pageCount = pdfRender.pageCount
//                Log.d("test", "总页数：$pageCount")
//                val bitmapArr = arrayOfNulls<Bitmap>(pageCount)
//                val start = System.currentTimeMillis()
//                for (i in 0 until pageCount) {
//                    val page: PdfRenderer.Page = pdfRender.openPage(i)
//                    Log.d("test","width=${page.width} height=${page.height}")
//                    val bitmap = Bitmap.createBitmap(page.width, page.height, Bitmap.Config.ARGB_8888)
//                    page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
//                    bitmapArr[i] = bitmap
//                    page.close()
//                }
//                Log.d("test", "生产${pageCount}张 Bitmap耗时=${System.currentTimeMillis() - start}")
//                adapter = MyAdapter(this, pdfRender, bitmapArr)
//                runOnUiThread(Runnable {
//                    mRecyclerView.adapter = adapter
//                })
//            }
        }.start()
    }

    /**
     * 获取assets下的pef,并复制到沙盒中返回文件地址
     */
    private fun copyFileToSdcard(context: Context): String? {
        val assetsInputStream = context.assets.open("阿里巴巴android开发手册.pdf")
        if (assetsInputStream.available() <= 0) {
            return null
        }
        val downloadFileDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
        val file = File(
            "${downloadFileDir?.absolutePath}${File.separator}阿里巴巴android开发手册.pdf"
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
        Log.d("test", "生产的文件地址=" + file.absolutePath)
        return file.absolutePath
    }
}
