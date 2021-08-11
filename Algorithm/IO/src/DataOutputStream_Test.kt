import java.io.*

/*
* 对流功能的扩展，可以更方便的读取Int、String、Long等数据类型
* */
object DataOutputStream_Test {


    @JvmStatic
    fun main(args: Array<String>) {

        val file = File("test_data.dat")
        val dos = DataOutputStream(FileOutputStream(file))
        dos.writeInt(22)
        dos.writeLong(10000L)
        dos.writeUTF("你好 China")

        dos.close()

        val dis = DataInputStream(FileInputStream(file))
        val a = dis.readInt()
        val l = dis.readLong()
        val utf = dis.readUTF()
        println("a=$a l=$l  utf=$utf")
    }

}