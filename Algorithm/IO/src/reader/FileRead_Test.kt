package reader

import java.io.FileReader
import java.io.FileWriter

object FileRead_Test {

    @JvmStatic
    fun main(args: Array<String>) {
        val fileReader=FileReader("srcFile.dat")
        val fileWriter=FileWriter("destFile.dat")
        val chars=CharArray(1024)
        var c:Int
        while ((fileReader.read(chars,0,chars.size).also { c = it })!=-1){
            fileWriter.write(chars)
            fileWriter.flush()
        }
        fileReader.close()
        fileWriter.close()
    }
}