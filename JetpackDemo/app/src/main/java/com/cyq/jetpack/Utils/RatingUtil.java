package com.cyq.jetpack.Utils;

/**
 * @author : ChenYangQi
 * date   : 2020/10/10 14:32
 * desc   :
 */
public class RatingUtil {
    public static String getRating(int rating) {
        switch (rating) {
            case 1:
                return "一星";
            case 2:
                return "二星";
            case 3:
                return "三星";
            case 4:
                return "四星";
            case 5:
                return "五星好评";
            default:
                return "暂无评分";
        }
    }
}
