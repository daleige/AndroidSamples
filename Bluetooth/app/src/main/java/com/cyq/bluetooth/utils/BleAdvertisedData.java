package com.cyq.bluetooth.utils;

import java.util.List;
import java.util.UUID;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/4/2 17:57
 */
public class BleAdvertisedData {
    private List<UUID> mUuids;
    private String mName;
    public BleAdvertisedData(List<UUID> uuids, String name){
        mUuids = uuids;
        mName = name;
    }

    public List<UUID> getUuids(){
        return mUuids;
    }

    public String getName(){
        return mName;
    }
}