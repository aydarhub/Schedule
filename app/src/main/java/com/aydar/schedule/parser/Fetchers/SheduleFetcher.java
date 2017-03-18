package com.aydar.schedule.parser.Fetchers;

import com.aydar.schedule.parser.API;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aydar on 20.02.2017.
 */

public class SheduleFetcher extends Fetcher {

    protected void parseShedule(List<API.Shedule> shedule, String jsonBody)
            throws IOException, JSONException {
        API api = parse(jsonBody);
        if (api.shedule != null) {
            shedule.addAll(api.shedule);
        }

    }

    protected List<List<API.Shedule>> getSheduleForDays(List<API.Shedule> shedule) {

        if (shedule.size() == 0) {
            return null;
        }
        List<List<API.Shedule>> sheduleForDays = new ArrayList<>();
        int j = 0;
        for (int i = 1; i <= 5; i++) {
            List<API.Shedule> sheduleForDay = new ArrayList<>();
            for (; j < shedule.size(); j++) {
                if (shedule.get(j).getDay() > i) {
                    break;
                }
                sheduleForDay.add(shedule.get(j));
            }
            sheduleForDays.add(sheduleForDay);
        }
        return sheduleForDays;
    }

}
