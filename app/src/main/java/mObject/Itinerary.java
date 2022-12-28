package mObject;

import java.io.Serializable;
import java.util.Date;

public class Itinerary implements Serializable {
    private Date dateStart;
    private Date dateEnd;
    private String name;
    private String description; //內容
    private String guest; //對象
    private String place; //地點

    public Itinerary(Date dateStart, Date dateEnd, String name ,String description, String guest, String place){
        this.dateStart=dateStart;
        this.dateEnd=dateEnd;
        this.name=name;
        this.description=description;
        this.guest=guest;
        this.place=place;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
