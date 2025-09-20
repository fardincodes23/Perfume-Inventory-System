package info.hccis.bus.pass.entity;

import info.hccis.bus.pass.jpa.entity.BusPass;

import java.util.ArrayList;

/**
 * Entity class to hold the attributes of the bus pass related reports.
 * @author bjmaclean
 * @since 20241010
 */
public class Translate {
    private String english;
    private String pirate;

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getPirate() {
        return pirate;
    }

    public void setPirate(String pirate) {
        this.pirate = pirate;
    }
}
