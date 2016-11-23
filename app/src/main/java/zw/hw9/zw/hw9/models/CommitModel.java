package zw.hw9.zw.hw9.models;

import java.io.Serializable;

/**
 * Created by et on 11/22/16.
 */

public class CommitModel implements Serializable {
    private String id;
    private String name;
    private String chamber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChamber() {
        return chamber;
    }

    public void setChamber(String chamber) {
        this.chamber = chamber;
    }
}
