package reader

import java.io.*

object BufferedReader_Test {

    @JvmStatic
    fun main(args: Array<String>) {
        val bufferedReader = BufferedReader(InputStreamReader(FileInputStream("srcFile.dat")))
        val bufferedWriter = BufferedWriter(OutputStreamWriter(FileOutputStream("destFile.dat")))
        var line: String?
        while (bufferedReader.readLine().also { line = it } != null) {
            bufferedWriter.write(line)
            bufferedWriter.newLine()
            bufferedWriter.flush()
        }
        bufferedReader.close()
        bufferedWriter.close()
    }
}