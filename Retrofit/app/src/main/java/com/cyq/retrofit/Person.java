package com.cyq.retrofit;

/**
 * @author : ChenYangQi
 * date   : 1/28/21 23:25
 * desc   :
 */
public class Person {

    private String phone;
    private String name;
    private Integer id;
    private Integer age;
    private String email;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "phone='" + phone + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
