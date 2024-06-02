package model;

import utilities.JDialogCreator;

import java.util.List;

public abstract class FlightStrategy extends ListFlight {
    public abstract JDialogCreator execute(Flight flight) throws Exception;
}
