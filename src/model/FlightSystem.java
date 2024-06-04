package model;

import utilities.JDialogCreator;

import java.util.List;

public class FlightSystem {
    private FlightStrategy flightStrategy;
    public FlightSystem(FlightStrategy flightStrategy) {
        this.flightStrategy = flightStrategy;
    }
    public FlightSystem() {}
    public void setStrategy(FlightStrategy flightStrategy) {
        this.flightStrategy = flightStrategy;
    }
    public JDialogCreator execute(Flight flight, ListFlight listFlight) {
        return this.flightStrategy.execute(flight, listFlight);
    }
}
