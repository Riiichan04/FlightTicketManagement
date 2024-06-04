package view;

import model.*;
import utilities.CustomLabel;
import utilities.FontLoader;
import utilities.JDialogCreator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;
public class TicketPanel extends JPanel {
    JTabbedPane tabbedPane;
    Model model;
    JScrollPane resultPane;
    JTextField[] topFilter;
    JTextField[] botFilter;
    Object[][] data;
    String[] listColView = {"Mã số ghế", "Mã hành khách"};
    String[] listColStatistic = {"Mã chuyến bay", "Số hiệu máy bay", "Tên máy bay", "Hãng bay", "Số lượng ghế", "Trọng lượng", "Nơi đi", "Nơi đến", "Ngày cất cánh"};
    JPanel wrapperTab;
    JPanel viewTab;
    JTextField viewInput;
    JTable viewTable;
    public TicketPanel(Rectangle r, ListFlight listFlight, Model model) {
        setBounds(r);
        this.tabbedPane = createTabbedPane(listFlight);
        this.model = model;
        add(tabbedPane);
    }

    JTabbedPane createTabbedPane(ListFlight listFlight) {
        this.wrapperTab = new JPanel();
        JTabbedPane result = new JTabbedPane();
        JPanel statisticTab = new JPanel();
        statisticTab.setLayout(new BorderLayout());
        Font robotoLight = FontLoader.loadFont("src/asset/font/Roboto-Light.ttf");
        Font robotoMedium = FontLoader.loadFont("src/asset/font/Roboto-Medium.ttf");
        CustomLabel[] labels = {new CustomLabel("Theo số lượng"), new CustomLabel("Theo hãng bay"), new CustomLabel("Theo ngày/tháng/năm:")};
        this.topFilter = new JTextField[]{new JTextField(10), new JTextField(10)};
        JPanel topSubPanel = createFilterView(labels, topFilter,
                new GridLayout(3, 2, 5, 0), FontLoader.loadCustomizeFont(robotoLight, 15f));
        this.botFilter = new JTextField[6];
        for (int i = 0; i < botFilter.length; i++) {
            botFilter[i] = new JTextField(5);
        }
        JPanel bottomSubPanel = createFilterView(new CustomLabel[]{new CustomLabel("Từ ngày"), new CustomLabel("Đến ngày")}, botFilter, new GridLayout(2, 4, 0, 0), FontLoader.loadCustomizeFont(robotoLight, 15f));
        JButton btn = new JButton("Thống kê");
        btn.setFont(FontLoader.loadCustomizeFont(robotoMedium, 15f));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusable(false);
        btn.addActionListener(statistic(listFlight));


        statisticTab.add(topSubPanel, BorderLayout.NORTH);
        statisticTab.add(Box.createRigidArea(new Dimension(50, 20)), BorderLayout.CENTER);
        statisticTab.add(bottomSubPanel, BorderLayout.CENTER);
        statisticTab.add(btn, BorderLayout.SOUTH);

        viewTab = new JPanel();
        viewTab.setLayout(new BorderLayout());
        JPanel infoPanel = new JPanel();
        CustomLabel label = new CustomLabel("Nhập mã chuyến bay cần xem: ");
        viewInput = new JTextField(10);

        label.setCustomFont(robotoLight, 15f);
        viewInput.setFont(FontLoader.loadCustomizeFont(robotoLight, 15f));

        infoPanel.add(label);
        infoPanel.add(viewInput);

        JButton sbtn = new JButton("Xác nhận");
        sbtn.setFont(FontLoader.loadCustomizeFont(robotoMedium, 15f));
        sbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sbtn.setFocusable(false);
        sbtn.addActionListener(e -> {
            this.data = model.displayTicket(viewInput.getText(), listFlight);
            if (this.data == null) {
                new JDialogCreator("Không tìm thấy chuyến bay!!!").setVisible(true);
            }
            else {
                viewTable.setModel(new DefaultTableModel(data, listColView) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                });
                for (int i = 0; i < listColView.length; i++) {
                    viewTable.getColumnModel().getColumn(i).setMinWidth(150);
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(300, 200));
        DefaultTableModel dtm = new DefaultTableModel(data, listColView) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        viewTable = new JTable(dtm);
        viewTable.setRowHeight(20);
        viewTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        viewTable.getTableHeader().setReorderingAllowed(false);
        viewTable.setFont(FontLoader.loadCustomizeFont(robotoLight, 13f));
        viewTable.setAutoCreateRowSorter(true);
        scrollPane.setViewportView(viewTable);
        for (int i = 0; i < listColView.length; i++) {
            viewTable.getColumnModel().getColumn(i).setMinWidth(150);
        }
        JPanel tableWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        viewTab.add(infoPanel, BorderLayout.NORTH);
        viewTab.add(sbtn, BorderLayout.CENTER);
        tableWrapper.add(scrollPane);
        viewTab.add(tableWrapper, BorderLayout.SOUTH);

        wrapperTab.add(statisticTab);
        result.addTab("Thống kê", wrapperTab);
        result.addTab("Xem vé", viewTab);
        return result;
    }

    JScrollPane createScrollPane() {
        Font robotoLight = FontLoader.loadFont("src/asset/font/Roboto-Light.ttf");
        Font robotoMedium = FontLoader.loadFont("src/asset/font/Roboto-Medium.ttf");
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(700, 200));
        return new JScrollPane();
    }

    private JPanel createFilterView(CustomLabel[] listLabel, JTextField[] listInput, GridLayout layout, Font font) {
        JPanel panel = new JPanel();
        panel.setLayout(layout);
        for (int i = 0; i < Math.max(listInput.length, listLabel.length); i++) {
            if (i < listLabel.length && listLabel.length >= listInput.length) {
                listLabel[i].setFont(font);
                panel.add(listLabel[i]);
            }
            if (listLabel.length < listInput.length) {
                if (i % (listInput.length / listLabel.length) == 0) {
                    listLabel[i / (listInput.length / listLabel.length)].setFont(font);
                    panel.add(listLabel[i / (listInput.length / listLabel.length)]);
                }
            }
            if (i < listInput.length) {
                listInput[i].setFont(font);
                panel.add(listInput[i]);
            }
        }
        return panel;
    }

    public ActionListener statistic(ListFlight listFlight) {
        return e -> {
            TicketDecorator decorator = new NoFilter();
            if (isAmountFilter()) {
                decorator = new AmountFilter(decorator, Integer.parseInt(topFilter[0].getText()));
            }
            if (isBrandFilter()) {
                decorator = new BrandFilter(decorator, topFilter[1].getText());
            }
            if (isDateFilter()) {
                Date fromDate = new Date(Integer.parseInt(botFilter[0].getText()), Integer.parseInt(botFilter[1].getText()), Integer.parseInt(botFilter[2].getText()));
                Date toDate = new Date(Integer.parseInt(botFilter[3].getText()), Integer.parseInt(botFilter[4].getText()), Integer.parseInt(botFilter[5].getText()));
                decorator = new DateFilter(decorator, fromDate, toDate);
            }
            model.statistic(decorator, listFlight);
        };
    }

    public ActionListener showTicketInfo(String id, ListFlight listFlight) {
        return e -> {
            this.data = model.displayTicket(id, listFlight);
            if (this.data == null) {
                new JDialogCreator("Không tìm thấy chuyến bay!!!").setVisible(true);
            }
            else {
                Font robotoLight = FontLoader.loadFont("src/asset/font/Roboto-Light.ttf");
                JScrollPane scrollPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
                scrollPane.setPreferredSize(new Dimension(700, 200));
                DefaultTableModel dtm = new DefaultTableModel(data, listColView) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };
                JTable table = new JTable(dtm);
                table.setFont(FontLoader.loadCustomizeFont(robotoLight, 13f));
                table.setRowHeight(20);
                table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                table.getTableHeader().setReorderingAllowed(false);
                scrollPane.setViewportView(table);
                for (int i = 0; i < listColView.length; i++) {
                    table.getColumnModel().getColumn(i).setMinWidth(100);
                }
                add(table);
            }
        };
    }

    private boolean isNoFilter() {
        return !isAmountFilter() && !isBrandFilter() && !isDateFilter();
    }
    private boolean isAmountFilter() {
        try {
            Integer.parseInt(topFilter[0].getText());
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    private boolean isBrandFilter() {
       return !topFilter[1].getText().equals("");
    }
    private boolean isDateFilter() {
        for (JTextField jTextField : botFilter) {
            if (jTextField.getText().equals("")) return false;
        }
        try {
            Date fromDate = new Date(Integer.parseInt(botFilter[0].getText()), Integer.parseInt(botFilter[1].getText()), Integer.parseInt(botFilter[2].getText()));
            Date toDate = new Date(Integer.parseInt(botFilter[3].getText()), Integer.parseInt(botFilter[4].getText()), Integer.parseInt(botFilter[5].getText()));
            return fromDate.toMilisecond() <= toDate.toMilisecond();
        }
        catch (Exception e) {
            return false;
        }
    }

    private Object[][] formatData(List<Flight> listFlight) {
        return listFlight.stream().map(obj -> new String[]{
                obj.getId(), obj.getPlane().getId(), obj.getPlane().getName(),
                obj.getPlane().getBrand(), ""+obj.getPlane().getSeatCount(), ""+obj.getPlane().getWeight(),
                obj.getRoute().getArrival(), obj.getRoute().getDeparture(),
                obj.getDate().toString()
        }).toArray(String[][]::new);
    }
}
