package zw.hw9.zw.hw9.models;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by et on 11/20/16.
 */

public class LegislatorModel {
    private String name;
    private String party;
    private String state;
    private String district;
    private String bioguide_id;
    private String photo;
    public String getBioguide_id() {
        return bioguide_id;
    }

    public void setBioguide_id(String bioguide_id) {
        this.bioguide_id = bioguide_id;
        this.photo = "http://theunitedstates.io/images/congress/original/"+bioguide_id+".jpg";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo=photo;
    }
}
