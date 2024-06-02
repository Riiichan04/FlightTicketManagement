package utilities;

import java.awt.*;
import java.io.File;

public interface FontLoader {
    //Load customize font by file path
    static Font loadFont(String filePath) throws Exception {
       return Font.createFont(Font.TRUETYPE_FONT,  new File(filePath));
    }
    //Load customize font and set size by file path
    static Font loadCustomizeFont(String filePath, float size) throws Exception {
        Font font = Font.createFont(Font.TRUETYPE_FONT,  new File(filePath));
        return font.deriveFont(size);
    }
    //Load current font and set size for font
    static Font loadCustomizeFont(Font font, float size) {
        return font.deriveFont(size);
    }
}
