package reader

import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.InputStreamReader
import java.io.OutputStreamWriter

object InputStreamReader_Test {
    @JvmStatic
    fun main(args: Array<String>) {

        val ips = FileInputStream("srcFile.dat")
        val reader = InputStreamReader(ips, "utf-8")
        val writer = OutputStreamWriter(FileOutputStream("write.dat"))
        val data = CharArray(1024)
        var c: Int
        while ((reader.read(data, 0, data.size).also { c = it }) != -1) {
            print(String(data, 0, data.size))
            writer.write(data, 0, data.size)
            writer.flush()
        }
        writer.close()
        reader.close()
    }

}