import java.io.Serializable

class Student(private val username: String, private val id: String, private val age: Int) : Serializable {
    override fun toString(): String {
        return "Student{" +
                "username='" + username + '\'' +
                ", id='" + id + '\'' +
                ", age=" + age +
                '}'
    }
}