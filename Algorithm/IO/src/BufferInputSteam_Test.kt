import java.io.*
import java.lang.IllegalArgumentException

object BufferInputSteam_Test {

    @JvmStatic
    fun main(args: Array<String>) {
        val srcFile = File("srcFile.dat")
        copyFileByBuffer(srcFile, File("buffered.dat"))
    }

    private fun copyFileByBuffer(srcFile: File, destFile: File) {
        if (!srcFile.exists()) {
            throw IllegalArgumentException("srcFile is empty!")
        }

        val bis = BufferedInputStream(FileInputStream(srcFile))
        val bos = BufferedOutputStream(FileOutputStream(destFile))
        //val data = ByteArray(1024)
        var c: Int
        while ((bis.read().also { c = it }) != -1) {
            bos.write(c)
            bos.flush()
        }
        bos.close()
        bis.close()
    }
}