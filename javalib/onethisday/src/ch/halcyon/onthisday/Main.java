package ch.halcyon.onthisday;

import ch.halcyon.onthisday.api.OnThisDayAPI;

public class Main {

    public static void main(String[] args) {
        System.out.println(OnThisDayAPI.getHappeningsAsJson(11,9));
    }


}
