package view;

import model.*;
import utilities.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Arrays;

public class FlightPanel extends JPanel {
    JTable table;
    JButton[] listBtn;
    Object[][] data;
    String[] listColumn = {"Mã chuyến bay", "Số hiệu máy bay", "Tên máy bay", "Hãng bay", "Số lượng ghế", "Trọng lượng", "Nơi đi", "Nơi đến", "Ngày cất cánh"};
    Model model;
    public FlightPanel(Rectangle bounds, ListFlight listFlight, Model model) {
        this.model = model;
        Font robotoLight = FontLoader.loadFont("src/asset/font/Roboto-Light.ttf");
        Font robotoMedium = FontLoader.loadFont("src/asset/font/Roboto-Medium.ttf");
        this.data = formatData(listFlight);
        JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(700, 200));
        DefaultTableModel dtm = new DefaultTableModel(data, listColumn) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(dtm);
        table.setFont(FontLoader.loadCustomizeFont(robotoLight, 13f));
        table.setRowHeight(20);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(table);
        for (int i = 0; i < listColumn.length; i++) {
            table.getColumnModel().getColumn(i).setMinWidth(100);
            if (i == 6 || i == 7) table.getColumnModel().getColumn(i).setMinWidth(200);
        }
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
//        String[] listTitle = {"Thêm chuyến bay", "Chỉnh sửa chuyến bay", "Xóa chuyến bay"};
        String[] listTitle = {"Thêm chuyến bay", "Chỉnh sửa chuyến bay"};
        this.listBtn = new JButton[listTitle.length];
        for (int i = 0; i < listBtn.length; i++) {
            listBtn[i] = new JButton(listTitle[i]);
            listBtn[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            listBtn[i].setFont(FontLoader.loadCustomizeFont(robotoMedium, 15f));
            listBtn[i].setFocusable(false);
            btnPanel.add(listBtn[i]);
        }
        listBtn[0].addActionListener(addFlight(listFlight));
        listBtn[1].addActionListener(updateFlight(listFlight));
//        listBtn[2].addActionListener(removeFlight(listFlight));
        add(scrollPane);
        add(btnPanel);
    }

    public Object[][] formatData(ListFlight listFlight) {
        return listFlight.getListFlight().stream().map(obj -> new String[]{
                obj.getId(), obj.getPlane().getId(), obj.getPlane().getName(),
                obj.getPlane().getBrand(), ""+obj.getPlane().getSeatCount(), ""+obj.getPlane().getWeight(),
                obj.getRoute().getArrival(), obj.getRoute().getDeparture(),
                obj.getDate().toString()
        }).toArray(String[][]::new);
    }

    public ActionListener addFlight(ListFlight listFlight) {
        return e -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Only txt file", "txt"));
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                Flight flight = FileLoader.createFlightFromFile(selectedFile);
                if(model.addFlight(flight, listFlight)) {
                    this.data = formatData(listFlight);
                    this.table.setModel(new DefaultTableModel(data, listColumn) {
                        //Refrest model to update data
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    });
                }
            }
        };
    }

    public ActionListener updateFlight(ListFlight listFlight) {
        return e -> {
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("Only txt file", "txt"));
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                Flight flight = FileLoader.createFlightFromFile(selectedFile);
                if(model.updateFlight(flight, listFlight)) {
                    this.data = formatData(listFlight);
                    this.table.setModel(new DefaultTableModel(data, listColumn) {
                        //Refrest model to update data
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    });
                    for (int i = 0; i < listColumn.length; i++) {
                        if (i == 6 || i == 7) table.getColumnModel().getColumn(i).setMinWidth(200);
                        table.getColumnModel().getColumn(i).setMinWidth(100);
                    }
                }
            }
        };
    }

    public ActionListener removeFlight(ListFlight listFlight) {
        return e -> {
            JPanel mainPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            CustomLabel mainLabel = new CustomLabel("Nhập mã chuyến bay cần xóa: ");
            JTextField input = new JTextField(10);
            mainPanel.add(mainLabel);
            mainPanel.add(input);
            JDialogCreator dialog = new JDialogCreator(mainPanel, JDialogCreator.WARNING_DIALOG, event -> {
                if (this.model.removeFlight(input.getText(), listFlight)) {
                    this.data = formatData(listFlight);
                    this.table.setModel(new DefaultTableModel(data, listColumn) {
                        //Refrest model to update data
                        @Override
                        public boolean isCellEditable(int row, int column) {
                            return false;
                        }
                    });
                    for (int i = 0; i < listColumn.length; i++) {
                        table.getColumnModel().getColumn(i).setMinWidth(100);
                        if (i == 6 || i == 7) table.getColumnModel().getColumn(i).setMinWidth(200);
                    }
                }
            }, new Dimension(400, 100));
            dialog.setVisible(true);
        };
    }
}
