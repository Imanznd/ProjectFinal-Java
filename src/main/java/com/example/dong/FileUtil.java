package com.example.dong;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class FileUtil {

    public Image getFileFromResource(String fileName){

        InputStream is = getClass().getClassLoader().getResourceAsStream(fileName);

        try {
            return ImageIO.read(is);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
