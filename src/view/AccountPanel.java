package view;

import model.*;
import utilities.CustomLabel;
import utilities.FileConverter;
import utilities.FontLoader;
import utilities.JDialogCreator;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AccountPanel extends JPanel {
    JButton[] listButton = new JButton[4];
    Model model;
    Account currentAccount;

    public AccountPanel(Rectangle bounds, Account currentAccount, ListAccount listAccount, Model model) {
        this.model = model;
//		setBounds(bounds);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 0, 5));
        Font robotoLight = FontLoader.loadFont("src/asset/font/Roboto-Light.ttf");
        Font robotoMedium = FontLoader.loadFont("src/asset/font/Roboto-Medium.ttf");
        String[] listTitle = {"Tên tài khoản: ", currentAccount.getUsername(), "Tên nhân viên: ", currentAccount.getInfo().getName(), "Mã nhân viên: ", currentAccount.getInfo().getId(), "Chức vụ: ", currentAccount.getInfo().getPosition()};
        CustomLabel[] listLabel = new CustomLabel[8];
        for (int i = 0; i < listLabel.length; i++) {
            listLabel[i] = new CustomLabel(listTitle[i]);
            if (i % 2 == 0) listLabel[i].setCustomFont(robotoLight, 15f);
            else listLabel[i].setCustomFont(robotoMedium, 15f);
            panel.add(listLabel[i]);
        }
        add(panel);
        add(Box.createRigidArea(new Dimension(800, 20)));
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] btnTitle = {"Cấp tài khoản mới", "Xóa tài khoản", "Thay đổi tên tài khoản", "Thay đổi mật khẩu"};
        for (int i = 0; i < btnTitle.length; i++) {
            listButton[i] = new JButton(btnTitle[i]);
            listButton[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            listButton[i].setFont(FontLoader.loadCustomizeFont(robotoMedium, 15f));
            listButton[i].setPreferredSize(new Dimension(190, 30));
            listButton[i].setFocusable(false);
            btnPanel.add(listButton[i]);
        }
        listButton[0].addActionListener(addAccount(listAccount));
        listButton[1].addActionListener(deleteAccount(listAccount));
        listButton[2].addActionListener(updateUsername(listAccount));
        listButton[3].addActionListener(updatePassword(listAccount));
        add(btnPanel);
    }

    public ActionListener addAccount(ListAccount listAccount) {
        return e -> {
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridLayout(3, 2, 0, 5));
            Font robotoLight = FontLoader.loadFont("src/asset/font/Roboto-Light.ttf");
            Font robotoMedium = FontLoader.loadFont("src/asset/font/Roboto-Medium.ttf");
            CustomLabel[] listLabel = {new CustomLabel("Tên nhân viên: "), new CustomLabel("Mã nhân viên: "), new CustomLabel("Chức vụ: ")};
            JTextField[] listInput = {new JTextField(15), new JTextField(15), new JTextField(15)};
            for (int i = 0; i < listLabel.length; i++) {
                listLabel[i].setCustomFont(robotoLight, 15f);
                listInput[i].setFont(FontLoader.loadCustomizeFont(robotoLight, 15f));
                mainPanel.add(listLabel[i]);
                mainPanel.add(listInput[i]);
            }
            new JDialogCreator(mainPanel, JDialogCreator.WARNING_DIALOG, event -> {
                String[] listInfo = new String[3];
                for (int i = 0; i < listInfo.length; i++) {
                    listInfo[i] = listInput[i].getText();
                }
                int nextAccountId = listAccount.getListAccount().size();
                switch (listInfo[2].toLowerCase()) {
                    case "staff" -> {
                        model.createAccount(new StaffAccount("staff" + nextAccountId, listInfo[1], new Employee(listInfo[0], listInfo[1], listInfo[2]), false), listAccount);
                    }
                    case "manager" -> {
                        model.createAccount(new ManagerAccount("manager" + nextAccountId, listInfo[1], new Employee(listInfo[0], listInfo[1], listInfo[2]), false), listAccount);
                    }
                }
            }, new Dimension(500, 150)).setVisible(true);
        };
    }

    public ActionListener deleteAccount(ListAccount listAccount) {
        return e -> {
            JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            Font robotoLight = FontLoader.loadFont("src/asset/font/Roboto-Light.ttf");
            CustomLabel label = new CustomLabel("Nhập tên tài khoản muốn xóa:");
            JTextField input = new JTextField(15);
            label.setFont(FontLoader.loadCustomizeFont(robotoLight, 15f));
            input.setFont(FontLoader.loadCustomizeFont(robotoLight, 15f));
            mainPanel.add(label);
            mainPanel.add(input);
            new JDialogCreator(mainPanel, JDialogCreator.WARNING_DIALOG, event -> {
                this.model.deleteAccount(input.getText(), listAccount);
            }, new Dimension(500, 150)).setVisible(true);
        };
    }

    public ActionListener updateUsername(ListAccount listAccount) {
        return e -> {
            JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            Font robotoLight = FontLoader.loadFont("src/asset/font/Roboto-Light.ttf");
            CustomLabel label = new CustomLabel("Nhập tên tài khoản mới");
            JTextField input = new JTextField(15);
            label.setFont(FontLoader.loadCustomizeFont(robotoLight, 15f));
            input.setFont(FontLoader.loadCustomizeFont(robotoLight, 15f));
            mainPanel.add(label);
            mainPanel.add(input);
            new JDialogCreator(mainPanel, JDialogCreator.WARNING_DIALOG, event -> {
                this.model.updateUsername(input.getText(), listAccount);
            }, new Dimension(500, 150)).setVisible(true);
        };
    }
    public ActionListener updatePassword(ListAccount listAccount) {
        return e -> {
            JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            JPanel infoPanel = new JPanel(new GridLayout(2, 2, 5, 0));
            Font robotoLight = FontLoader.loadFont("src/asset/font/Roboto-Light.ttf");
            CustomLabel passwdLabel = new CustomLabel("Nhập mật khẩu mới");
            JPasswordField input = new JPasswordField(15);
            CustomLabel confirmPasswdLabel = new CustomLabel("Xác nhận mật khẩu mới: ");
            JPasswordField confirmInput = new JPasswordField(15);
            passwdLabel.setFont(FontLoader.loadCustomizeFont(robotoLight, 15f));
            confirmPasswdLabel.setFont(FontLoader.loadCustomizeFont(robotoLight, 15f));
            infoPanel.add(passwdLabel);
            infoPanel.add(input);
            infoPanel.add(confirmPasswdLabel);
            infoPanel.add(confirmInput);
            mainPanel.add(infoPanel);
            new JDialogCreator(mainPanel, JDialogCreator.WARNING_DIALOG, event -> {
                this.model.updatePassword(new String(input.getPassword()), new String(confirmInput.getPassword()), listAccount);
            }, new Dimension(500, 150)).setVisible(true);
        };
    }
}
