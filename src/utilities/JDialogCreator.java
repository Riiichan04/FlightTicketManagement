package utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class JDialogCreator {
    private JDialog dialog;
    private JPanel panel;
    public static final int CONFIRM_DIALOG = 0;
    public static final int WARNING_DIALOG = 1;
    public JDialogCreator(JPanel mainPanel, int type, ActionListener actionListener) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        this.dialog = new JDialog(frame, "Thông báo", true);
        this.panel = new JPanel(new BorderLayout());
        this.panel.add(mainPanel, BorderLayout.CENTER);
        this.panel.add(createMainButton(type, actionListener), BorderLayout.SOUTH);

    }

    public JDialogCreator(String content, int type, ActionListener actionListener) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        this.dialog = new JDialog(frame, "Thông báo", true);
        this.panel = new JPanel(new BorderLayout());
        this.panel.add(new JLabel(content), BorderLayout.CENTER);
        this.panel.add(createMainButton(type, actionListener), BorderLayout.SOUTH);
    }

    public JDialogCreator(JPanel mainPanel) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        this.dialog = new JDialog(frame, "Thông báo", true);
        this.panel = new JPanel(new BorderLayout());
        this.panel.add(mainPanel, BorderLayout.CENTER);
        this.panel.add(createMainButton(0, null), BorderLayout.SOUTH);
    }

    public JDialogCreator(String content) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        this.dialog = new JDialog(frame, "Thông báo", true);
        this.panel = new JPanel(new BorderLayout());
        this.panel.add(new JLabel(content), BorderLayout.CENTER);
        this.panel.add(createMainButton(0, null), BorderLayout.SOUTH);
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
}
