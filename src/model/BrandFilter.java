package model;

import java.util.List;
import java.util.Objects;

public class BrandFilter extends Filter {
    private String brand;

    public BrandFilter(TicketDecorator wrapper, String brand) {
        super(wrapper);
        this.brand = brand;
    }

    @Override
    public List<Flight> execute(List<Flight> listFlight) {
        return this.wrapper.execute(listFlight.stream().filter(obj -> obj.getPlane().getBrand().equals(this.brand)).toList());
    }
}
