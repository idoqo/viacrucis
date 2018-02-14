package io.github.idoqo.viacrucis.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Station {
    @JsonProperty("position")
    private int stationID;
    @JsonProperty("roman_numeral")
    private String romanNumeral;
    private String title;
    private String scripture;
    private String reading;
    @JsonProperty("meditation")
    private Meditation meditation;
    private String hymn;
    @JsonProperty("let_us_pray")
    private String prayer;
    @JsonProperty("image_path")
    private String imagePath;

    public int getStationID() {
        return stationID;
    }

    public void setStationID(int stationID) {
        this.stationID = stationID;
    }

    public String getRomanNumeral() {
        return romanNumeral;
    }

    public void setRomanNumeral(String romanNumeral) {
        this.romanNumeral = romanNumeral;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getScripture() {
        return scripture;
    }

    public void setScripture(String scripture) {
        this.scripture = scripture;
    }

    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    public Meditation getMeditation() {
        return meditation;
    }

    public void setMeditation(Meditation meditation) {
        this.meditation = meditation;
    }

    public String getHymn() {
        return hymn;
    }

    public void setHymn(String hymn) {
        this.hymn = hymn;
    }

    public String getPrayer() {
        return prayer;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setPrayer(String prayer) {
        this.prayer = prayer;
    }
}
