package model;

import java.util.List;
import java.util.Map;

public class Flight {
    private String id;
    private Plane plane;
    private Route route;
    Map<String, String> listSeat;
    Date date;

    public Flight() {
    }

    public Flight(String id, Plane plane, Route route, Map<String, String> listSeat, Date date) {
        this.id = id;
        this.plane = plane;
        this.route = route;
        this.listSeat = listSeat;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Plane getPlane() {
        return plane;
    }

    public void setPlane(Plane plane) {
        this.plane = plane;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Map<String, String> getListSeat() {
        return listSeat;
    }

    public void setListSeat(Map<String, String> listSeat) {
        this.listSeat = listSeat;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !this.getClass().equals(obj.getClass())) return false;
        Flight other = (Flight) obj;
        return this.id.equals(other.id) && this.plane.equals(other.plane) && this.route.equals(other.route);
    }

    @Override
    public String toString() {
        return this.id + this.plane.toString() + this.route.toString() + date.toString();
    }
}
