package com.example.ble.ble.bean

/**
 * @author:YangQi.Chen
 * @date:2023/7/2 13:14
 * @description:业务层蓝牙通用发送，接受通用数据包定义
 */
class Packager {
    /**包序号，六位随机数*/
    var seq: Int? = null

    /**数据体*/
    var payLoad: ByteArray? = null
}