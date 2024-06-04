package model;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class TicketSystem {
    private TicketDecorator ticketDecorator;
    public TicketSystem() { }
    public void setDecorator(TicketDecorator ticketDecorator) {
        this.ticketDecorator = ticketDecorator;
    }
    public int statistic(ListFlight listFlight) {
        return this.ticketDecorator.execute(listFlight.getListFlight()).stream().mapToInt(obj -> Math.toIntExact(obj.getListSeat().values().stream().filter(Objects::nonNull).count())).sum();
    }
    public List<Flight> viewFlightIncluded(ListFlight listFlight) {
        return this.ticketDecorator.execute(listFlight.getListFlight());
    }

    public String[][] displayTicket(String id, ListFlight listFlight) {
        List<Flight> list = listFlight.getListFlight().stream().filter(obj -> obj.getId().equals(id)).toList();
        if (list.size() > 0) {
            Flight flight = list.getFirst();
            return flight.getListSeat().entrySet().stream().map(obj -> new String[]{obj.getKey(), obj.getValue()}).toArray(String[][]::new);
        }
        else return null;
    }
}
