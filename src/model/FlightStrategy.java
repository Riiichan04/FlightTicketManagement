package model;

import utilities.JDialogCreator;

import java.util.List;

public abstract class FlightStrategy {
    public abstract JDialogCreator execute(Flight flight, ListFlight listFlight);
}
