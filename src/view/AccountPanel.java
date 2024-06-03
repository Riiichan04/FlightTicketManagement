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
	public AccountPanel(Rectangle bounds, Account currentAccount) throws Exception {
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
		add(btnPanel);
	}
	public AccountPanel(Account account, Model model) throws Exception {
		new AccountPanel(new Rectangle(0, 0, 0, 0), account);
		this.currentAccount = account;
		this.model = model;
	}

	public ActionListener addAccount() {
		return e -> {
			try {
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
					int nextAccountId = this.model.getListAccount().size();
					switch (listInfo[2].toLowerCase()) {
						case "staff" -> {
							try {
								model.createAccount(new StaffAccount("staff"+nextAccountId, listInfo[1], new Employee(listInfo[0], listInfo[1], listInfo[2]), false));
							} catch (Exception ex) {
								new JDialogCreator("Thực hiện thất bại");
							}
						}
						case "manager" -> {
							try {
								model.createAccount(new ManagerAccount("manager"+nextAccountId, listInfo[1], new Employee(listInfo[0], listInfo[1], listInfo[2]), false));
							} catch (Exception ex) {
								new JDialogCreator("Thực hiện thất bại");
							}
						}
					}
				});
			} catch (Exception ex) {
				new JDialogCreator("Thực hiện thất bại");
			}
		};
	}
}
