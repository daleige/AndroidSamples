package com.cyq.bluetooth.utils;

import android.util.Log;

/**
 * @author chenyq113@midea.com
 * @describe xxx
 * @time 2021/4/13 18:31
 */
public class ByteUtil {
    /**
     * 将十六进制的字符串转换成字节
     *
     * @param commandStr 7E 18 00 07 00 04 01 02 03 04 00 05 00 1A 7E
     * @return
     * @throws NumberFormatException
     */
    public static byte[] parseCommand(String commandStr) throws NumberFormatException {
        String[] tempStr = commandStr.split(" ");
        byte[] commands = new byte[tempStr.length];
        for (int i = 0; i < tempStr.length; i++) {
            try {
                commands[i] = (byte) Integer.parseInt(tempStr[i], 16);
            } catch (Exception o_o) {
                commands[i] = 00;
                Log.e("命令转换出错", tempStr[i]);
            }
        }
        return commands;
    }

    /**
     * 将字节转换为十六进制的字符串（）
     *
     * @param bytesCommand
     * @return
     * @from 忘了
     */
    public static String bytesToHexString(byte[] bytesCommand) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (bytesCommand == null || bytesCommand.length <= 0) {
            return null;
        }
        for (int i = 0; i < bytesCommand.length; i++) {
            int v = bytesCommand[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
