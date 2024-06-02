package model;

import java.util.List;

public class NoFilter extends Filter {

    public NoFilter(TicketDecorator wrapper) {
        super(wrapper);
    }

    @Override
    public List<Flight> execute(List<Flight> listFlight) {
        return listFlight;
    }


}
