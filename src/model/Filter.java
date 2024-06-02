package model;

import java.util.List;

public abstract class Filter implements TicketDecorator {
    protected TicketDecorator wrapper;

    public Filter(TicketDecorator wrapper) {
        this.wrapper = wrapper;
    }

    public abstract List<Flight> execute(List<Flight> listFlight);
}
