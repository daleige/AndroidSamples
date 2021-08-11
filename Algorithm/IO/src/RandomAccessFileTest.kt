import java.io.File
import java.io.RandomAccessFile

//RandomAccessFileTest提供了对文件内容的访问
//只读(r) 读写(rw)
/*
* write(int)写一个字节，并指向下一个位置
* read()只读一个字节
* */

object RandomAccessFileTest {
    @JvmStatic
    fun main(args: Array<String>) {
        val str = "Hello world Java IO"
        val bytes = str.encodeToByteArray()
        val srcFile = File("srcFile.dat")
        if (srcFile.exists()) {
            srcFile.delete()
        } else {
            srcFile.createNewFile()
        }
        val raf = RandomAccessFile(srcFile, "rw")
        raf.write(bytes)

        val destBytes = ByteArray(bytes.size)
        //读文件 时把指针指向文件头部
        raf.seek(0)
        raf.read(destBytes)
        println("读取结果：${String(destBytes)}")

        raf.close()
    }
}