package com.csw.vediocut.controller;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;

import java.io.File;

/**
 * <p>
 * description
 * </p>
 *
 * @author ll
 * @date 06/11/20 10:23
 */
public class VideoTimeReader {

    public static void main(String[] args) {
        System.out.println(readTime(new File("C:\\Program Files\\WindowsApps\\AppUp.IntelGraphicsExperience_1.100.3370.0_x64__8j3eq9eme6ctt\\Assets\\en-us\\videos\\video3.mp4")));
    }

    // 获取视频时长
    private static String readTime(File source) {

        Encoder encoder = new Encoder();
        String length = "";
        try {
            MultimediaInfo m = encoder.getInfo(source);
            long ls = m.getDuration() / 1000;
            int hour = (int) (ls / 3600);
            int minute = (int) (ls % 3600) / 60;
            int second = (int) (ls - hour * 3600 - minute * 60);
            length = hour + "小时" + minute + "分" + second + "秒";
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return length;
    }

}