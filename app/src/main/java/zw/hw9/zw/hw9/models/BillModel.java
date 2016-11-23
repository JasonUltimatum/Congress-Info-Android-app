package zw.hw9.zw.hw9.models;

import java.io.Serializable;

/**
 * Created by et on 11/22/16.
 */

public class BillModel implements Serializable {
    private String id;
    private String titile;
    private String intro;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitile() {
        return titile;
    }

    public void setTitile(String titile) {
        this.titile = titile;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
