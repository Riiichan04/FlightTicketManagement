package model;

public class Employee {
    protected String name;
    protected String id;
    protected String position;
    public Employee(String name, String id, String position) {
        this.name = name;
        this.id = id;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }
}
