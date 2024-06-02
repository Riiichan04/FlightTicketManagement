package model;

import java.util.List;

public interface TicketDecorator {
    List<Flight> execute(List<Flight> listFlight);
}
