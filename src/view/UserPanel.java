package view;

import model.*;
import utilities.CustomLabel;
import utilities.FontLoader;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserPanel extends JPanel {
    protected IModel model;
    protected Account currentAccount;
    protected JPanel sidePanel;
    protected JPanel resultPanel;
    protected AccountPanel accountPanel;
    protected TicketPanel ticketPanel;
    protected FlightPanel flightPanel;
    protected CardLayout cardLayout = new CardLayout();
    public UserPanel(Account currentAccount, IModel model, ListFlight lf, ListAccount la) {
        this.currentAccount = currentAccount;
        this.model = model;
        setLayout(null);
        this.sidePanel = this.createSidePanel(new Rectangle(0, 0, 200, 680));
        this.resultPanel = this.createResultPanel(new Rectangle(200, 0, 800, 680));
        accountPanel = new AccountPanel(new Rectangle(200, 0, 800, 600), currentAccount, la, model);
        ticketPanel = new TicketPanel(new Rectangle(200, 0, 800, 600), lf, model);
        flightPanel = new FlightPanel(new Rectangle(200, 0, 800, 600), lf, model);
        resultPanel.add("ap", accountPanel);
        resultPanel.add("tp", ticketPanel);
        resultPanel.add("fp", flightPanel);
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
    public JPanel createSidePanel(Rectangle bounds) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBounds(bounds);
        panel.setBorder(new MatteBorder(0, 0, 0, 1, Color.BLACK));

        List<String> listString = new ArrayList<>();
        listString.add("Xin chào, " + this.currentAccount.getUsername());
        listString.add("Quản lý chuyến bay");
        listString.add("Quản lý thông tin vé");
        listString.add("Quản lý tài khoản");

        List<JButton> listButton = new ArrayList<>();

        Font robotoMedium = FontLoader.loadFont("src/asset/font/Roboto-Medium.ttf");
        Font robotoLight = FontLoader.loadFont("src/asset/font/Roboto-Light.ttf");

        List<JPanel> listPanel = List.of(new JPanel(), new JPanel(), new JPanel());

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

            switch (i) {
                case 1 -> btn.addActionListener(e -> cardLayout.show(resultPanel, "fp"));
                case 2 -> btn.addActionListener(e -> cardLayout.show(resultPanel, "tp"));
                case 3 -> btn.addActionListener(e -> cardLayout.show(resultPanel, "ap"));
            }
        }
        JButton signOut = new JButton("Đăng xuất");
        signOut.setHorizontalAlignment(SwingConstants.LEFT);
        signOut.setFont(FontLoader.loadCustomizeFont(robotoMedium, 17f));
        signOut.setPreferredSize(new Dimension(190, 30));
        signOut.setBackground(new Color(0, 0, 0, 0));
        signOut.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signOut.setFocusable(false);
        signOut.setOpaque(false);
        signOut.setBorder(null);
        signOut.addActionListener(e -> {
           JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
           frame.dispose();
           System.exit(0);
        });
        panel.add(Box.createRigidArea(new Dimension(190, 500)));
        panel.add(signOut);
        return panel;
    }
    public JPanel createResultPanel(Rectangle bounds) {
        JPanel panel = new JPanel(cardLayout);
        panel.setBounds(bounds);
        CustomLabel label = new CustomLabel("Hãy chọn một tính năng để thực hiện");
        Font robotoLight = FontLoader.loadFont("src/asset/font/Roboto-Light.ttf");
        label.setCustomFont(robotoLight, 30f);
        label.setHorizontalAlignment(JLabel.CENTER);
        panel.add(label);
        return panel;
    }
}


