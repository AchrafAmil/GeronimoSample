package com.neogineer.geronimo.geronimosample.data;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Just a POJO
 *
 * Created by AchrafAmil (@neogineer) on 22/07/2018.
 */
@Entity(tableName = "king")
public class King {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String desc;
    private int drawable;

    @Ignore
    public King(String title, String desc, int drawable) {
        this.title = title;
        this.desc = desc;
        this.drawable = drawable;
    }

    public King(int id, String title, String desc, int drawable) {
        this(title, desc, drawable);
        this.id = id;
    }

    public String getDetails(){
        return "Details : " + title;
    }

    public String getShareableText(){
        return "Hello : " + title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
