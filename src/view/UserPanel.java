package view;

import utilities.CustomLabel;
import utilities.FontLoader;
import model.Account;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class UserPanel extends JPanel {
    protected Account currentAccount;
    protected JPanel sidePanel;
    protected JPanel resultPanel;
    public UserPanel(Account currentAccount) throws Exception {
        this.currentAccount = currentAccount;
        setLayout(null);
        this.sidePanel = this.createSidePanel(new Rectangle(0, 0, 200, 680));
        this.resultPanel = this.createResultPanel(new Rectangle(200, 0, 800, 680));
        add(sidePanel);
        add(resultPanel);
    }
    //Set Panel despite function that user choose and type of user
    public void setSidePanel(JPanel sidePanel) {
        this.sidePanel = sidePanel;
        super.repaint();
    }

    public void setResultPanel(JPanel resultPanel) {
        this.resultPanel = resultPanel;
        super.repaint();
    }
    public JPanel createSidePanel(Rectangle bounds) throws Exception {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBounds(bounds);
        panel.setBorder(new MatteBorder(0, 0, 0, 1, Color.BLACK));

        java.util.List<String> listString = new ArrayList<>();
        listString.add("Xin chào, " + this.currentAccount.getUsername());
        listString.add("Quản lý chuyến bay");
        listString.add("Quản lý thông tin vé");
        listString.add("Quản lý tài khoản");

        java.util.List<JButton> listButton = new ArrayList<>();

        Font robotoMedium = FontLoader.loadFont("src/asset/font/Roboto-Medium.ttf");
        Font robotoLight = FontLoader.loadFont("src/asset/font/Roboto-Light.ttf");

        java.util.List<JPanel> listPanel = List.of(new JPanel(), new JPanel(), new JPanel());

        CustomLabel titleLabel = new CustomLabel(listString.get(0));
        titleLabel.setCustomFont(robotoLight, 15f);
        titleLabel.setPreferredSize(new Dimension(150, 30));
        panel.add(titleLabel);

        for (int i = 1; i < listString.size(); i++) {
            JButton btn = new JButton(listString.get(i));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setFont(FontLoader.loadCustomizeFont(robotoMedium, 17f));
            btn.setPreferredSize(new Dimension(190, 30));
            btn.setBackground(new Color(0, 0, 0, 0));
            btn.setFocusable(false);
            btn.setOpaque(false);
            btn.setBorder(null);
            panel.add(btn);

        }


        return panel;
    }
    public JPanel createResultPanel(Rectangle bounds) throws Exception {
        JPanel panel = new JPanel(new CardLayout());
        panel.setBounds(bounds);
        CustomLabel label = new CustomLabel("Hãy chọn một tính năng để thực hiện");
        Font robotoLight = FontLoader.loadFont("src/asset/font/Roboto-Light.ttf");
        label.setCustomFont(robotoLight, 30f);
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label);
        return panel;
    }
}


