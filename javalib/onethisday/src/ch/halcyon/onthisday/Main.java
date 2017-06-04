package ch.halcyon.onthisday;

import ch.halcyon.onthisday.api.OnThisDayAPI;
import ch.halcyon.onthisday.api.model.Export;

public class Main {

    public static void main(String[] args) {

        Export happeningsExport = OnThisDayAPI.getHappeningsExport(11, 11);

        System.out.println("births: "+ happeningsExport.getBirths().size()); // 189
        System.out.println("deaths: "+ happeningsExport.getDeaths().size()); // 105
        System.out.println("events: "+ happeningsExport.getEvents().size()); // 57

    }


}
