package com.company.多态;

public class Cat extends Anima{
    @Override
    protected void getName() {
        super.getName();
        System.out.println("猫咪...");
    }
}
