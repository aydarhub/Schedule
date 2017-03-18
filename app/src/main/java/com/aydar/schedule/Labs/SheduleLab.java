package com.aydar.schedule.Labs;

import com.aydar.schedule.parser.API;

import java.util.List;

/**
 * Created by aydar on 20.02.2017.
 */

public class SheduleLab {

    private List<List<API.Shedule>> mShedule;

    protected void setShedule(List<List<API.Shedule>> shedule) {
        mShedule = shedule;
    }

    public List<List<API.Shedule>> getShedule() {
        return mShedule;
    }

    public List<API.Shedule> getSheduleForDay(int dayNumber) {
        if (mShedule == null) {
            return null;
        }
        return mShedule.get(dayNumber);
    }

}
