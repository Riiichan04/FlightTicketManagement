package model;

import java.util.*;
import java.util.stream.Collectors;

public class DateFilter extends Filter {
    private Date fromDate;
    private Date toDate;

    public DateFilter(TicketDecorator wrapper, Date fromDate, Date toDate) {
        super(wrapper);
        this.fromDate = fromDate;
        this.toDate = toDate;
    }


    @Override
    public List<Flight> execute(List<Flight> listFlight) {
        return wrapper.execute(listFlight.stream().filter(obj -> obj.getDate().toMilisecond() >= this.fromDate.toMilisecond() &&
                obj.getDate().toMilisecond() <= this.toDate.toMilisecond()).toList());
    }
}
