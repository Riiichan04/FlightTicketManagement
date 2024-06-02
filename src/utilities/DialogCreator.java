package utilities;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class DialogCreator extends JFrame {
    String content;
    static final int SHOW = 1;
    static final int HIDE = 2;
    JDialog dialog;
    boolean isDialogVisible = false;

    JButton btn;

    public DialogCreator(String content) {
        super();
        this.content = content;

        setTitle("Button");

        JPanel panel = new JPanel();
        btn = new JButton(getButtonLabel(SHOW));
        btn.addActionListener(event());
        panel.add(btn);

        getContentPane().add(panel);
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public DialogCreator() throws HeadlessException {
    }

    private ActionListener event() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isDialogVisible) {
                    int i = JOptionPane.showConfirmDialog(null, "Bạn có muốn sử dụng tính năng này không?", "Thông báo",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (i == JOptionPane.YES_OPTION) {
                        showDialog();
                        btn.setText(getButtonLabel(HIDE));
                    }
                } else {
                    int i = JOptionPane.showConfirmDialog(null, "Bạn có hủy sử dụng tính năng này không?", "Thông báo",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (i == JOptionPane.YES_OPTION) {
                        cancelDialog();
                        btn.setText(getButtonLabel(SHOW));
                    }
                }
            }
        };
    }

    private void showDialog() {
        dialog = new JDialog(this, content);
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        isDialogVisible = true;
    }

    private void cancelDialog() {
        if (dialog != null) {
            dialog.setVisible(false);
            isDialogVisible = false;
        }
    }

    private String getButtonLabel(int state) {
        switch (state) {
            case SHOW:
                return "Show Dialog";
            case HIDE:
                return "Hide Dialog";
            default:
                return "Unknown";
        }
    }

    public static void main(String[] args) {
        new DialogCreator("Dialog");
    }
}
