package model;

import utilities.FileLoader;

import java.util.List;

public class ListFlight {
    private List<Flight> listFlight;

    public void loadFlight() {
        try {
            this.listFlight = FileLoader.loadFlight();
        }
        catch (Exception e) {
            System.out.println("Không thể load danh sách chuyến bay");
        }
    }

    public List<Flight> getListFlight() {
        return listFlight;
    }
}
