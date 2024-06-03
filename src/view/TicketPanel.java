package view;

import model.Employee;
import model.ManagerAccount;
import utilities.CustomLabel;
import utilities.FontLoader;

import javax.swing.*;
import java.awt.*;

public class TicketPanel extends JPanel {
    JTabbedPane tabbedPane;
    JScrollPane resultPane;

    public TicketPanel(Rectangle r) throws Exception {
        setBounds(r);
        this.tabbedPane = createTabbedPane();
        add(tabbedPane);
    }

    JTabbedPane createTabbedPane() throws Exception {
        JTabbedPane result = new JTabbedPane();
        JPanel wrapperTab = new JPanel();
        JPanel statisticTab = new JPanel();
        statisticTab.setLayout(new BorderLayout());
        Font robotoLight = FontLoader.loadFont("src/asset/font/Roboto-Light.ttf");
        Font robotoMedium = FontLoader.loadFont("src/asset/font/Roboto-Medium.ttf");
        CustomLabel[] labels = {new CustomLabel("Theo số lượng"), new CustomLabel("Theo hãng bay"), new CustomLabel("Theo ngày/tháng/năm:")};
        JPanel topSubPanel = createFilterView(labels, new JTextField[]{new JTextField(10), new JTextField(10)},
                new GridLayout(3, 2, 5, 0), FontLoader.loadCustomizeFont(robotoLight, 15f));
        JTextField[] botField = new JTextField[6];
        for (int i = 0; i < botField.length; i++) {
            botField[i] = new JTextField(5);
        }
        JPanel bottomSubPanel = createFilterView(new CustomLabel[0], botField, new GridLayout(2, 3, 0, 0), FontLoader.loadCustomizeFont(robotoLight, 15f));

        JButton btn = new JButton("Xác nhận");
        btn.setFont(FontLoader.loadCustomizeFont(robotoMedium, 15f));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusable(false);

        JButton sbtn = new JButton("Xác nhận");
        sbtn.setFont(FontLoader.loadCustomizeFont(robotoMedium, 15f));
        sbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sbtn.setFocusable(false);

        statisticTab.add(topSubPanel, BorderLayout.NORTH);
        statisticTab.add(Box.createRigidArea(new Dimension(50, 20)), BorderLayout.CENTER);
        statisticTab.add(bottomSubPanel, BorderLayout.CENTER);
        statisticTab.add(sbtn, BorderLayout.SOUTH);

        JPanel viewTab = new JPanel();
        viewTab.setLayout(new BorderLayout());
        JPanel infoPanel = new JPanel();
        CustomLabel label = new CustomLabel("Nhập mã chuyến bay cần xem: ");
        JTextField input = new JTextField(10);

        label.setCustomFont(robotoLight, 15f);
        input.setFont(FontLoader.loadCustomizeFont(robotoLight, 15f));

        infoPanel.add(label);
        infoPanel.add(input);

        viewTab.add(infoPanel, BorderLayout.CENTER);
        viewTab.add(btn, BorderLayout.SOUTH);

        wrapperTab.add(statisticTab);

        result.addTab("Thống kê", wrapperTab);
        result.addTab("Xem vé", viewTab);
        return result;
    }
    JScrollPane createScrollPane() throws Exception {
        JTextArea result = new JTextArea();
        result.setColumns(2);
        return new JScrollPane();
    }
    private JPanel createFilterView(CustomLabel[] listLabel, JTextField[] listInput, GridLayout layout, Font font) {
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        for (int i = 0; i < Math.max(listInput.length, listLabel.length); i++) {
            if (i < listLabel.length) {
                listLabel[i].setFont(font);
                panel.add(listLabel[i]);
            }
            if (i < listInput.length) {
                listInput[i].setFont(font);
                panel.add(listInput[i]);
            }
        }
        return panel;
    }

    public static void main(String[] args) throws Exception {
        JFrame frame = new JFrame("Account Panel");
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.add(new TicketPanel(new Rectangle(0, 0, 0, 0)));
        frame.setVisible(true);
    }
}
