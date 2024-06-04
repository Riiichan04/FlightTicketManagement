package model;

import java.util.List;

public class NoFilter implements TicketDecorator {
    @Override
    public List<Flight> execute(List<Flight> listFlight) {
        return listFlight;
    }


}
