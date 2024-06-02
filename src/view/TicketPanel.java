package view;

import utilities.CustomLabel;
import utilities.FontLoader;

import javax.swing.*;
import java.awt.*;

public class TicketPanel extends JPanel {
    JTabbedPane tabbedPane;
    JScrollPane resultPane;

    public TicketPanel(int x, int y, int w, int h) throws Exception {
        setBounds(x, y, w, h);
        this.tabbedPane = createTabbedPane();
    }

    JTabbedPane createTabbedPane() throws Exception {
        JTabbedPane result = new JTabbedPane();
        JPanel statisticTab = new JPanel();
        statisticTab.setLayout(new BorderLayout());
        Font robotoLight = FontLoader.loadFont("src/asset/font/Roboto-Light.ttf");
        CustomLabel[] labels = {new CustomLabel("Theo số lượng"), new CustomLabel("Theo hãng bay"), new CustomLabel("Theo ngày/tháng/năm:")};
        JPanel topSubPanel = createFilterView(labels, new JTextField[]{new JTextField(10), new JTextField(10)},
                new GridLayout(3, 2, 5, 0), FontLoader.loadCustomizeFont(robotoLight, 15f));
        JTextField[] botField = new JTextField[6];
        for (int i = 0; i < botField.length; i++) {
            botField[i] = new JTextField(5);
        }
        JPanel bottomSubPanel = createFilterView(new CustomLabel[0], botField, new GridLayout(2, 3, 0, 0), FontLoader.loadCustomizeFont(robotoLight, 15f));

        statisticTab.add(topSubPanel, BorderLayout.NORTH);
        statisticTab.add(Box.createRigidArea(new Dimension(50, 20)), BorderLayout.CENTER);
        statisticTab.add(bottomSubPanel, BorderLayout.CENTER);
        statisticTab.add(new JButton("Xác nhận"), BorderLayout.SOUTH);

        JPanel viewTab = new JPanel();
        viewTab.setLayout(new BorderLayout());
        JPanel infoPanel = new JPanel();
        CustomLabel label = new CustomLabel("Nhập mã chuyến bay cần xem: ");
        JTextField input = new JTextField(10);
        JButton btn = new JButton("Xác nhận");
        label.setCustomFont(robotoLight, 15f);
        input.setFont(FontLoader.loadCustomizeFont(robotoLight, 15f));
        btn.setFont(FontLoader.loadCustomizeFont(robotoLight, 15f));
        infoPanel.add(label);
        infoPanel.add(input);

        viewTab.add(infoPanel, BorderLayout.CENTER);
        viewTab.add(btn, BorderLayout.SOUTH);

        result.addTab("Thống kê", statisticTab);
        result.addTab("Xem vé", viewTab);
        return result;
    }
    JScrollPane createScrollPane() throws Exception {
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

}
