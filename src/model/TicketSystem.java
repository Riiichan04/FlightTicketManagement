package model;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class TicketSystem extends ListFlight {
    private TicketDecorator ticketDecorator;
    public TicketSystem() { }
    public void setDecorator(TicketDecorator ticketDecorator) {
        this.ticketDecorator = ticketDecorator;
    }
    public int statistic() {
        return this.ticketDecorator.execute(this.getListFlight()).stream().mapToInt(obj -> Math.toIntExact(obj.getListSeat().values().stream().filter(Objects::nonNull).count())).sum();
    }
    public List<Flight> viewFlightIncluded() {
        return this.ticketDecorator.execute(getListFlight());
    }

    public JScrollPane displayTicket(String id) {
        Flight flight = this.getListFlight().stream().filter(obj -> obj.getId().equals(id)).toList().getFirst();
        JScrollPane pane = new JScrollPane();
        JTextArea result = new JTextArea();
        result.setEditable(false);
        result.setOpaque(false);
        StringBuilder info = new StringBuilder();
        flight.getListSeat().forEach((k, v) -> info.append("Mã ghế: ").append(k).append(" - Mã khách hàng: ").append(v).append("\n"));
        result.setText(String.valueOf(info));
        pane.setViewportView(result);
        return pane;
    }
}
