package com.cyq.handle.core;

public class Message {
    public int waht;
    public Handle target;
    public Object obj;

    public Message() {

    }

    public Message(Object obj) {
        this.obj = obj;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
