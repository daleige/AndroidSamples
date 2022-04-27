// IMsgManager.aidl
package com.chenyangqi.aidl.service;

import com.chenyangqi.aidl.service.IReceiveMsgListener;
import com.chenyangqi.aidl.service.Msg;

// Declare any non-default types here with import statements

interface IMsgManager {
     void sendMsg(in Msg msg);

     void registerReceiveListener(IReceiveMsgListener receiveListener);

     void unregisterReceiveListener(IReceiveMsgListener receiveListener);
}