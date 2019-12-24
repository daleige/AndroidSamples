package com.cyq.greendaodemo.bean;

import androidx.annotation.NonNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

/**
 * Time: 2019-12-24 23:21
 * Author: ChenYangQi
 * Description:
 */
@Entity
public class Student {

    @Id
    private long id;
    private String name;
    private int age;

    @Generated(hash = 1156616942)
    public Student(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "id :" + id + "  age:" + age + " name:" + name;
    }
}
