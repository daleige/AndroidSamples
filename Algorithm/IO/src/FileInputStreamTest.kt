import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.RandomAccessFile

object FileInputStreamTest {

    @JvmStatic
    fun main(args: Array<String>) {
        val srcFile = File("srcFile.dat")
        val destFile = File("destFile.dat")
        setFileContent()

        val inputStream = FileInputStream(srcFile)
        //如果目标文件不存在会自动创建
        val outputStream = FileOutputStream(destFile)

        val data = ByteArray(10 * 1024)

        while ((inputStream.read(data, 0, data.size)) != -1) {
            outputStream.write(data)
            outputStream.flush()
        }

        outputStream.close()
        inputStream.close()
    }

    @JvmStatic
    fun setFileContent() {
        val str = "Hello FileInputStream  and FileOutputStream"
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