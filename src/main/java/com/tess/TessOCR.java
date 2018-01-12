package com.tess;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import net.sourceforge.tess4j.util.LoadLibs;

import java.awt.*;
import java.io.File;

/**
 * Created by 618 on 2018/1/8.
 * @author lingfengsan
 */
public class TessOCR {
    public static String getOCR(File imageFile){
        Tesseract instance = Tesseract.getInstance(); // JNA Interface Mapping
        // Tesseract1 instance = new Tesseract1(); // JNA Direct Mapping
        instance.setLanguage("chi_sim");
        //Set the tessdata path
        File tessDataFolder = LoadLibs.extractTessResources("tessdata");
        instance.setDatapath(tessDataFolder.getAbsolutePath());
        try {
            Rectangle rectangle = new Rectangle(100, 300, 900, 900);
            return instance.doOCR(imageFile,rectangle);
        } catch (TesseractException e) {
            System.err.println("提取文字失败："+e.getMessage());
        }
        return null;
    }

    public static void main(String[] args) {
        File imageFile=new File("D:\\__screenshot\\chi_sim1.png");
        System.out.println(TessOCR.getOCR(imageFile));
    }
}
