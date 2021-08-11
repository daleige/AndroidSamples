import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object ObjectInputStream_Test {
    @JvmStatic
    fun main(args: Array<String>) {
        val oos = ObjectOutputStream(FileOutputStream("student.dat"))
        val ois = ObjectInputStream(FileInputStream("student.dat"))

        val student = Student("张三", "202100012323", 18)
        oos.writeObject(student)
        oos.flush()
        oos.close()

        val readObj: Student = ois.readObject() as Student
        println("反序列化：$readObj")
    }
}