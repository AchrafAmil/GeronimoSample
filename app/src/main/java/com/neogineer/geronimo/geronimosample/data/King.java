package com.neogineer.geronimo.geronimosample.data;

/**
 * Just a POJO
 *
 * Created by AchrafAmil (@neogineer) on 22/07/2018.
 */
public class King {
    public final String title;
    public final String desc;
    public final int drawable;

    public King(String title, String desc, int drawable) {
        this.title = title;
        this.desc = desc;
        this.drawable = drawable;
    }

    public String getDetails(){
        return "Details : " + title;
    }

    public String getShareableText(){
        return "Hello : " + title;
    }
}
