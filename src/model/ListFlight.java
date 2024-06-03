package model;

import utilities.FileLoader;

import java.util.List;

public class ListFlight {
    private List<Flight> listFlight;

    public void loadFlight() throws Exception {
        this.listFlight = FileLoader.loadFlight();
    }

    public List<Flight> getListFlight() {
        return listFlight;
    }
}
