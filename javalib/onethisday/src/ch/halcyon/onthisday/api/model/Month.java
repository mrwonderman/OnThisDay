package ch.halcyon.onthisday.api.model;

/**
 * Created by yannick on 03/06/2017.
 */
public class Month {
    private int number;
    private String name;

    public Month() {
    }

    public Month(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
