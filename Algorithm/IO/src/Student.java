import java.io.Serializable;

public class Student implements Serializable {

    private String username;
    private String id;
    private int age;

    public Student(String username, String id, int age) {
        this.username = username;
        this.id = id;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "username='" + username + '\'' +
                ", id='" + id + '\'' +
                ", age=" + age +
                '}';
    }
}
