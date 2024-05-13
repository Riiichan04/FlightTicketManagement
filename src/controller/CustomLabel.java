package controller;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {
    public CustomLabel(String text, Icon icon, int horizontalAlignment) {
        super(text, icon, horizontalAlignment);
    }

    public CustomLabel(String text, int horizontalAlignment) {
        super(text, horizontalAlignment);
    }

    public CustomLabel(String text) {
        super(text);
    }

    public CustomLabel(Icon image, int horizontalAlignment) {
        super(image, horizontalAlignment);
    }

    public CustomLabel(Icon image) {
        super(image);
    }

    public CustomLabel() {
    }
    //Set font with custom font by file path and size
    public void setCustomFont(String fontPath , float size) throws Exception {
        super.setFont(FontLoader.loadCustomizeFont(fontPath, size));
    }
    //Set font with custom font and size
    public void setCustomFont(Font font , float size) {
        super.setFont(FontLoader.loadCustomizeFont(font, size));
    }
}
