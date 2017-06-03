package ch.halcyon.onthisday.api.model;

/**
 * Created by yannick on 03/06/2017.
 */
public class Happening {
    String year;
    String text;
    TYPES type;

    public Happening() {
    }

    public Happening(String year, String text, TYPES type) {
        this.year = year;
        this.text = text;
        this.type = type;
    }

    public TYPES getType() {
        return type;
    }

    public void setType(TYPES type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public enum TYPES {
        EVENT,
        BIRTH,
        DEATH;
    }
}
