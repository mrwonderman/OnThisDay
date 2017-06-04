package ch.halcyon.onthisday.api;

import ch.halcyon.onthisday.api.model.Export;
import ch.halcyon.onthisday.api.model.Happening;
import ch.halcyon.onthisday.api.model.Month;
import com.google.gson.Gson;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.apache.commons.lang3.ArrayUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class OnThisDayAPI {
    private static String BASE_URL = "https://en.wikipedia.org/wiki/";
    private static List<Month> MONTHS = new ArrayList<>();
    private static List<Happening> happenings = new ArrayList<>();


    private static boolean validateDate(int month) {
        if (month > 12 || month < 1) {
            return false;
        }
        return true;
    }

    private static String constructDateUrl(int month, int day) {
        return BASE_URL + getMonth(month).getName() + "_" + day;
    }

    private static String getRawData(int month, int day) {
        try {
            String url = constructDateUrl(month, day);
            System.out.println("connecting to " + url);
            return Unirest.get(url).asString().getBody();
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static List<Happening> cleanHTML(String rawData) {
        Document document = Jsoup.parse(rawData);

        parseToHappening(document.getElementById("Events").parent().nextElementSibling(), Happening.TYPES.EVENT);
        parseToHappening(document.getElementById("Births").parent().nextElementSibling(), Happening.TYPES.BIRTH);
        parseToHappening(document.getElementById("Deaths").parent().nextElementSibling(), Happening.TYPES.DEATH);
        return happenings;
    }

    private static void parseToHappening(Node node, Happening.TYPES types) {
        String s = node.toString();
        Pattern myPattern = Pattern.compile("<.*?>");
        String[] split = myPattern.split(s);

        for (int i = 0; i < split.length; i++) {
            if (split[i].contains(" – ")) {
                String year = split[i - 1];
                if(split[i].contains(" – ") && !year.matches(".*\\d+.*")){
                    year = split[i].substring(0,split[i].indexOf("–")).trim();
                }
                String info = String.join("", ArrayUtils.subarray(split, i + 1, i + 20));
                if (info.contains("–") && info.length() > 4) {
                    info = info.substring(0, info.indexOf("\n"));
                }
                info = info.replace("\n", "");
                info = info.trim();

                addHappening(year, info, types);
            }
        }
    }

    private static List<Happening> getInformation(int month, int day) {
        if (MONTHS.size() < 12) {
            populateMonths();
        }
        if (!validateDate(month)) {
            return null;
        }

        cleanHTML(getRawData(month, day));

        return happenings;
    }

    private static void populateMonths() {
        MONTHS.add(new Month(1, "January"));
        MONTHS.add(new Month(2, "February"));
        MONTHS.add(new Month(3, "March"));
        MONTHS.add(new Month(4, "April"));
        MONTHS.add(new Month(5, "May"));
        MONTHS.add(new Month(6, "June"));
        MONTHS.add(new Month(7, "July"));
        MONTHS.add(new Month(8, "August"));
        MONTHS.add(new Month(9, "September"));
        MONTHS.add(new Month(10, "October"));
        MONTHS.add(new Month(11, "November"));
        MONTHS.add(new Month(12, "December"));
    }

    private static Month getMonth(int month) {
        return MONTHS.get(month - 1);
    }

    private static void addHappening(String year, String happening, Happening.TYPES type) {
        happenings.add(new Happening(year, happening, type));
    }

    public static Export getHappeningsExport(int month, int day) {
        Export export = new Export();

        for (Happening happening : OnThisDayAPI.getInformation(month, day)) {
            switch (happening.getType()) {
                case BIRTH:
                    export.addBirth(happening);
                    break;
                case DEATH:
                    export.addDeath(happening);
                    break;
                case EVENT:
                    export.addEvent(happening);
                    break;
            }
        }
        return export;
    }

    public static String getHappeningsAsJson(int month, int day) {
        Gson g = new Gson();
        return g.toJson(getHappeningsExport(month, day));
    }

    public static void getHappeningsToFile(int month, int day) {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream("export_" + month + "_" + day + ".json"), "utf-8"))) {
            writer.write(getHappeningsAsJson(month, day));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
