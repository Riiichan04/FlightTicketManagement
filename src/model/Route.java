package model;

public class Route {
	private String departure;
    private String arrival;
	public Route(String departure, String arrival) {
		this.departure = departure;
		this.arrival = arrival;
	}
	public String getDeparture() {
		return departure;
	}
	public void setDeparture(String departure) {
		this.departure = departure;
	}
	public String getArrival() {
		return arrival;
	}
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || !this.getClass().equals(obj.getClass())) return false;
		Route route = (Route) obj;
		return this.departure.equals(route.departure) && this.arrival.equals(route.arrival);
	}

	@Override
	public String toString() {
		return this.departure + this.arrival;
	}
}
