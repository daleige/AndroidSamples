package com.company.多态;

public class Main {

    public static void main(String[] args) {
        Anima anima1=new Dog();
        Anima anima2=new Cat();
        printName(anima1);
        printName(anima2);
    }

    private static void printName(Anima anima){
        anima.getName();
    }
}
