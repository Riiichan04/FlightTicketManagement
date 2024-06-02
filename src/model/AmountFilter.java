package model;

import java.util.List;
import java.util.Objects;

public class AmountFilter extends Filter{
    private int amount;
    public AmountFilter(TicketDecorator wrapper, int amount) {
        super(wrapper);
        this.amount = amount;
    }

    @Override
    public List<Flight> execute(List<Flight> listFlight) {
        //Get ListSeat -> Use values() to get list of Customer Id. Then filter list Customer Id with not null. Then count it and compare with amount.
        //Finally, filter the flight which have amount of customer id that not null > amount and collect it.
        return this.wrapper.execute(listFlight.stream().filter(obj -> obj.getListSeat().values().stream().filter(Objects::nonNull).count() >= this.amount).toList());
    }
}
