package utilities;

import javax.swing.*;
import java.awt.*;

public interface ImageLoader {
    //Load image with custom width and height
    static ImageIcon loadImage(String filePath, int width, int height) {
        Image tempImage = new ImageIcon(filePath).getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(tempImage);
    }
}
