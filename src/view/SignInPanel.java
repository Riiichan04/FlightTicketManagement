package view;

import model.ListAccount;
import model.ListFlight;
import model.Model;
import utilities.CustomLabel;
import utilities.FontLoader;
import utilities.JDialogCreator;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Arrays;

public class SignInPanel extends JPanel {
    public SignInPanel(Model model, ListAccount listAccount, ListFlight listFlight) throws Exception {
        setLayout(null);
        setBackground(Color.DARK_GRAY);
        add(new SignInForm(model, listAccount, listFlight));
    }

    class SignInForm extends JPanel implements View, Observer {
        JPanel infoPanel;   //Trick: Tạo 1 JPanel lồng các JTextField và JLabel lại
        JTextField account;
        JPasswordField passwd;
        JButton button;
        Model model;

        public SignInForm(Model model, ListAccount listAccount, ListFlight listFlight) {
            this.model = model;
            model.addObserver(this);
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
            this.button.addActionListener(e -> {
                if (model.signIn(this.account.getText(), new String(this.passwd.getPassword()), listAccount)) {
                    model.removeObserver(0);
                    ((JFrame) SwingUtilities.getWindowAncestor(this)).dispose();
                    model.addObserver(new WorkFrame(new UserPanel((model.getMainSystem().getCurrentAccount()), model, listFlight)));
                }
            });

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

        @Override
        public void update(JDialogCreator dialog) {
            dialog.setVisible(true);
        }
    }
}



