package com.neogineer.geronimo.geronimosample;

import com.neogineer.geronimo.geronimosample.data.King;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AchrafAmil (@neogineer) on 28/07/2018.
 */
public class Utils {

    public static King getRandomKing(){
        List<King> kings = getHardcodedList();
        return kings.get( (int) (Math.random()*kings.size()));
    }

    public static List<King> getHardcodedList() {
        ArrayList<King> kings = new ArrayList<>();
        kings.add(new King("The king Ragnar", "Vikings", R.drawable.sample_viking));
        kings.add(new King("The king of the north", "Game of thrones", R.drawable.sample_got));
        kings.add(new King("The king of nothing", "Something else", R.drawable.sample_nothing));
        kings.add(new King("The king Ragnar", "Vikings", R.drawable.sample_viking));
        kings.add(new King("The king of the north", "Game of thrones", R.drawable.sample_got));
        kings.add(new King("The king of nothing", "Something else", R.drawable.sample_nothing));

        return kings;
    }
}
