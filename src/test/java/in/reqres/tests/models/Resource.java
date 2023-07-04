package in.reqres.tests.models;

import io.restassured.response.Response;

public class Resource {
    private int id;
    private String name;
    private int year;
    private String color;
    private String pantoneValue;

    public Resource() {}

    public Resource(int id) {
        setId(id);
    }

    public Resource(int id, String name, int year, String color, String pantone_value) {
        setId(id);
        setName(name);
        setYear(year);
        setColor(color);
        setPantoneValue(pantone_value);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPantoneValue() {
        return pantoneValue;
    }

    public void setPantoneValue(String pantoneValue) {
        this.pantoneValue = pantoneValue;
    }

}
