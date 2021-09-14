package com.csw.vediocut.controller;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * <p>
 * description
 * </p>
 *
 * @author ll
 * @date 06/04/20 10:29
 */
public class VideoCoverCut {
    public static void main(String[] args) {
        try {
            VideoCoverCut.videoImage("C:\\Program Files\\WindowsApps\\AppUp.IntelGraphicsExperience_1.100.3370.0_x64__8j3eq9eme6ctt\\Assets\\en-us\\videos\\video3.mp4", "D:\\");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 截取视频第六帧的图片
     *
     * @param filePath 视频路径
     * @param dir      文件存放的根目录
     * @return 图片的相对路径 例：pic/1.png
     */
    public static String videoImage(String filePath, String dir) throws Exception {
        String pngPath = "";
//        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(filePath);
        FFmpegFrameGrabber ff = FFmpegFrameGrabber.createDefault(new File(filePath));

        ff.start();
        int ffLength = ff.getLengthInFrames();
        Frame f;
        int i = 0;
        while (i < ffLength) {
            f = ff.grabFrame();
            //截取第6帧
            if ((i > 5) && (f.image != null)) {
                //生成图片的相对路径 例如：pic/uuid.png
                pngPath = getPngPath();
                //执行截图并放入指定位置
                System.out.println("存储图片 ： " + (dir + pngPath));
                doExecuteFrame(f, dir + pngPath);
                break;
            }
            i++;
        }
        ff.stop();

        return pngPath;
    }

    /**
     * 生成图片的相对路径
     *
     * @return 图片的相对路径 例：pic/1.png
     */
    private static String getPngPath() {
        return getUUID() + ".png";
    }


    /**
     * 生成唯一的uuid
     *
     * @return uuid
     */
    private static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    /**
     * 截取缩略图
     *
     * @param f                       Frame
     * @param targerFilePath:封面图片存放路径
     */
    private static void doExecuteFrame(Frame f, String targerFilePath) {
        String imagemat = "png";
        if (null == f || null == f.image) {
            return;
        }
        Java2DFrameConverter converter = new Java2DFrameConverter();
        BufferedImage bi = converter.getBufferedImage(f);
        File output = new File(targerFilePath);
        try {
            ImageIO.write(bi, imagemat, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
