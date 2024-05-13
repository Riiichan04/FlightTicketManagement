package view;

import controller.CustomLabel;
import controller.FontLoader;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SignInPanel extends JPanel {
    public SignInPanel() throws Exception {
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        add(new SignInForm());
    }
}

class SignInForm extends JPanel {
    JPanel infoPanel;   //Trick: Tạo 1 JPanel lồng các JTextField và JLabel lại
    JTextField account;
    JPasswordField passwd;
    JButton button;
    public SignInForm() throws Exception {
        Font robotoMedium = FontLoader.loadFont("src/asset/font/Roboto-Medium.ttf");
        Font robotoLight = FontLoader.loadFont("src/asset/font/Roboto-Light.ttf");

        setLayout(new FlowLayout());
        setBounds(275, 150, 450, 300); //Trick: Dùng FlowLayout + setPreferredSize để đẩy JButton xuống chính giữa ở dưới infoPanel và đẩy title ở chính giữa bên trên infoPanel
//        setBackground(new Color(246, 203, 203));
        
        this.infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(this.infoPanel, BoxLayout.Y_AXIS));

        CustomLabel title = new CustomLabel("Đăng nhập");
        CustomLabel description = new CustomLabel("Hãy đăng nhập để có thể truy cập vào hệ thống");

        this.account = new JTextField(20);
        this.passwd = new JPasswordField(20);
        this.button = new JButton("Đăng nhập");

        this.account.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Tên đăng nhập", 0, 0, FontLoader.loadCustomizeFont(robotoMedium, 12f)));
        this.passwd.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Mật khẩu", 0, 0, FontLoader.loadCustomizeFont(robotoMedium, 12f)));
        this.account.setOpaque(false);
        this.passwd.setOpaque(false);

        title.setCustomFont(robotoMedium, 30f);
        description.setCustomFont(robotoLight, 12f);

        this.account.setFont(FontLoader.loadCustomizeFont(robotoLight, 15f));
        this.passwd.setFont(FontLoader.loadCustomizeFont(robotoLight, 15f));
        this.button.setFont(FontLoader.loadCustomizeFont(robotoMedium, 18f));

        this.button.setFocusable(false);
        this.button.setBackground(new Color(0, 227, 114));

        add(Box.createRigidArea(new Dimension(450, 20)));
        add(title);

        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));   //Box.createRigidArea -> tạo 1 box vô hình (Trick: Dùng để spacing 2 component)
        infoPanel.add(description);

        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(this.account);

        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(this.passwd);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        add(this.infoPanel);
        add(button);
    }
}

