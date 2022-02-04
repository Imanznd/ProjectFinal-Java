package com.example.dong;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil {

    public static Image getFileFromResource(String fileName) throws IOException {
        InputStream is = FileUtil.class.getClassLoader().getResourceAsStream(fileName);
        if(is == null) throw new FileNotFoundException();
        return ImageIO.read(is);
    }


}
