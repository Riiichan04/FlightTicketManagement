package model;

public class Plane {
    private String id;
    private String name;
    private String brand;
    private int seatCount;
    private double weight;
    private boolean isLanding;  //False -> Flight will be taken off here. Else flight will be landed here

    public Plane(String id, String name, String brand, int seatCount, double weight, boolean isLanding) {
        super();
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.seatCount = seatCount;
        this.weight = weight;
        this.isLanding = isLanding;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isLanding() {
        return isLanding;
    }

    public void setLanding(boolean isLanding) {
        this.isLanding = isLanding;
    }

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || !this.getClass().equals(obj.getClass())) return false;
		Plane plane = (Plane) obj;
		return this.id.equals(plane.id) && this.name.equals(plane.name) && this.isLanding == plane.isLanding;
	}
}
