// IReceiveMsgListener.aidl
package com.chenyangqi.aidl.service;
import com.chenyangqi.aidl.service.Msg;
// Declare any non-default types here with import statements

interface IReceiveMsgListener {
    void onReceive(in Msg msg);
}