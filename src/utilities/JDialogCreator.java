package utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JDialogCreator {
    private JDialog dialog;
    private JPanel panel;
    public static final int CONFIRM_DIALOG = 0;
    public static final int WARNING_DIALOG = 1;
    public boolean status;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public JDialogCreator(JPanel mainPanel, int type, ActionListener actionListener, Dimension size) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.panel = new JPanel(new BorderLayout());
        this.panel.add(mainPanel, BorderLayout.CENTER);
        this.panel.add(createMainButton(type, actionListener), BorderLayout.SOUTH);
        this.dialog = new JDialog(frame, "Thông báo", true);
        this.dialog.setSize(size);
        this.dialog.setLocationRelativeTo(null);
        this.dialog.add(panel);

    }

    public JDialogCreator(String content, int type, ActionListener actionListener, Dimension size) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.panel = new JPanel(new BorderLayout());
        JPanel subPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subPanel.add(new JLabel(content));
        this.panel.add(subPanel, BorderLayout.CENTER);
        this.panel.add(createMainButton(type, actionListener), BorderLayout.SOUTH);
        this.dialog = new JDialog(frame, "Thông báo", true);
        this.dialog.setLocationRelativeTo(null);
        this.dialog.setSize(size);
        this.dialog.add(panel);

    }

    public JDialogCreator(JPanel mainPanel, Dimension size) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.panel = new JPanel(new BorderLayout());
        this.panel.add(mainPanel, BorderLayout.CENTER);
        this.panel.add(createMainButton(0, null), BorderLayout.SOUTH);
        this.dialog = new JDialog(frame, "Thông báo", true);
        this.dialog.setLocationRelativeTo(null);
        this.dialog.setSize(size);
        this.dialog.add(panel);
    }

    public JDialogCreator(String content) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.panel = new JPanel(new BorderLayout());
        JPanel subPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        subPanel.add(new JLabel(content));
        this.panel.add(subPanel, BorderLayout.CENTER);
        this.panel.add(createMainButton(0, null), BorderLayout.SOUTH);
        this.dialog = new JDialog(frame, "Thông báo", true);
        this.dialog.setSize(300, 100);
        this.dialog.setLocationRelativeTo(null);
        this.dialog.add(panel);
    }

    public JDialogCreator(String content, Dimension dimension) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JTextArea textArea = new JTextArea(content);
        textArea.setEditable(false);
        textArea.setOpaque(false);
        textArea.setBackground(new Color(0, 0, 0, 0));
        this.panel = new JPanel(new BorderLayout());
        this.panel.add(textArea, BorderLayout.CENTER);
        this.panel.add(createMainButton(0, null), BorderLayout.SOUTH);
        this.dialog = new JDialog(frame, "Thông báo", true);
        this.dialog.setSize(dimension);
        this.dialog.setLocationRelativeTo(null);
        this.dialog.add(panel);
    }


    JPanel createMainButton(int type, ActionListener actionListener) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        switch (type) {
            case CONFIRM_DIALOG -> {
                JButton confirmBtn = new JButton("Xác nhận");
                confirmBtn.addActionListener(e -> this.dialog.setVisible(false));
                panel.add(confirmBtn);
            }
            case WARNING_DIALOG -> {
                JButton confirmBtn = new JButton("Xác nhận");
                confirmBtn.addActionListener(actionListener);
                JButton cancelBtn = new JButton("Hủy");
                cancelBtn.addActionListener(e -> this.dialog.setVisible(false));
                panel.add(confirmBtn);
                panel.add(cancelBtn);
            }
        }
        return panel;
    }

    public void setVisible(boolean b) {
        dialog.setVisible(b);
    }
}
