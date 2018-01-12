package com.tess;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by 618 on 2018/1/8.
 *
 * @author lingfengsan
 */
public class Phone {
    /**
     * ABD_PATH此处应更改为自己的adb目录
     * HERO_PATH更改自己存放图片的地址
     */
    private static final String ADB_PATH = "D:\\tools\\platform(adb&fastboot)-tools-latest-windows\\platform-tools\\adb";
    private static final String HERO_PATH = "D:\\__screenshot";
    private static final Long MIN_IMAGE_SIZE=1000L;

    public File getImage() {
        //获取当前时间作为名字
        Date current = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDate = df.format(current);
        File curPhoto = new File(HERO_PATH, curDate + ".png");
        //截屏存到手机本地
        try {
            while(!curPhoto.exists() || curPhoto.length()<MIN_IMAGE_SIZE) {
                // adb 调用手机截图命令
                Runtime.getRuntime().exec(ADB_PATH + " shell /system/bin/screencap -p /sdcard/screenshot.png");
                Thread.sleep(700);
                // 将截图放在电脑本地
                Runtime.getRuntime().exec(ADB_PATH + " pull /sdcard/screenshot.png " + curPhoto.getAbsolutePath());
                Thread.sleep(200);
            }
            // 返回当前图片文件
            return curPhoto;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("获取图片失败");
        return null;
    }
    
    public String getImagePath() {
        //获取当前时间作为名字
        Date current = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String curDate = df.format(current);
        File curPhoto = new File(HERO_PATH, curDate + ".png");
        //截屏存到手机本地
        try {
            while(!curPhoto.exists() || curPhoto.length()<MIN_IMAGE_SIZE) {
                // adb 调用手机截图命令
                Runtime.getRuntime().exec(ADB_PATH + " shell /system/bin/screencap -p /sdcard/screenshot.png");
                Thread.sleep(700);
                // 将截图放在电脑本地
                Runtime.getRuntime().exec(ADB_PATH + " pull /sdcard/screenshot.png " + curPhoto.getAbsolutePath());
                Thread.sleep(200);
            }
            // 返回当前图片文件
            return HERO_PATH + File.separator + curDate + ".png";
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.err.println("获取图片失败");
        return null;
    }

    public static void main(String[] args) {
        new Phone().getImage();
    }
}
