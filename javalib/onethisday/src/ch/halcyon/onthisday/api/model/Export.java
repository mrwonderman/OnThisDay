package ch.halcyon.onthisday.api.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yannick on 03/06/2017.
 */
public class Export {

    List<Happening> events = new ArrayList<>();
    List<Happening> births = new ArrayList<>();
    List<Happening> deaths = new ArrayList<>();

    public Export(List<Happening> events, List<Happening> births, List<Happening> deaths) {
        this.events = events;
        this.births = births;
        this.deaths = deaths;
    }

    public Export() {
    }

    public Export(List<Happening> deaths) {
        this.deaths = deaths;
    }

    public List<Happening> getEvents() {
        return events;
    }

    public void setEvents(List<Happening> events) {
        this.events = events;
    }

    public List<Happening> getBirths() {
        return births;
    }

    public void setBirths(List<Happening> births) {
        this.births = births;
    }

    public List<Happening> getDeaths() {
        return deaths;
    }

    public void setDeaths(List<Happening> deaths) {
        this.deaths = deaths;
    }

    public void addBirth(Happening happening){
        births.add(happening);
    }

    public void addEvent(Happening happening){
        events.add(happening);
    }

    public void addDeath(Happening happening){
        deaths.add(happening);
    }
}
