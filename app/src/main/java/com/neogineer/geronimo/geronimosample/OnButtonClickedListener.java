package com.neogineer.geronimo.geronimosample;

import com.neogineer.geronimo.geronimosample.data.King;

/**
 * Created by AchrafAmil (@neogineer) on 23/07/2018.
 */
public interface OnButtonClickedListener {

    void onDetailsClicked(King king);

    void onShareClicked(King king);
}
