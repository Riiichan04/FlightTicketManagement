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
        if (fromDate.toMilisecond() > toDate.toMilisecond()) return new LinkedList<>();
        if (fromDate.toMilisecond() == -1 || toDate.toMilisecond() == -1) return new LinkedList<>();
        return wrapper.execute(listFlight.stream().filter(obj -> obj.getDate().toMilisecond() >= this.fromDate.toMilisecond() &&
                obj.getDate().toMilisecond() <= this.toDate.toMilisecond() && !obj.getPlane().isLanding()).toList());
    }
}
