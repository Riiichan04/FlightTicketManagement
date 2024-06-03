package view;

import model.Employee;
import model.Flight;
import model.ListFlight;
import model.ManagerAccount;
import utilities.FileLoader;
import utilities.FontLoader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Arrays;

public class FlightPanel extends JPanel {
    JButton[] listBtn;
    Object[][] data;
    String[] listColumn = {"Mã chuyến bay", "Số hiệu máy bay", "Tên máy bay", "Hãng bay", "Số lượng ghế", "Trọng lượng", "Nơi đi", "Nơi đến", "Ngày cất cánh"};
    public FlightPanel(Rectangle bounds, ListFlight listFlight) throws Exception {
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
        JTable table = new JTable(dtm);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.getTableHeader().setReorderingAllowed(false);
        scrollPane.setViewportView(table);
        for (int i = 0; i < listColumn.length; i++) {
            table.getColumnModel().getColumn(i).setMinWidth(100);
            if (i == 6 || i == 7) table.getColumnModel().getColumn(i).setMinWidth(200);
        }
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String[] listTitle = {"Thêm chuyến bay", "Chỉnh sửa chuyến bay", "Xóa chuyến bay"};
        this.listBtn = new JButton[listTitle.length];
        for (int i = 0; i < listBtn.length; i++) {
            listBtn[i] = new JButton(listTitle[i]);
            listBtn[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            listBtn[i].setFont(FontLoader.loadCustomizeFont(robotoMedium, 15f));
            listBtn[i].setFocusable(false);
            btnPanel.add(listBtn[i]);
        }

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

    public static void main(String[] args) throws Exception {
        ListFlight lf = new ListFlight();
        lf.loadFlight();
        JFrame frame = new JFrame("Account Panel");
        frame.setSize(800,400);
        frame.setLocationRelativeTo(null);
        frame.add(new FlightPanel(new Rectangle(0, 0, 0, 0), lf));
        frame.setVisible(true);
    }

}
