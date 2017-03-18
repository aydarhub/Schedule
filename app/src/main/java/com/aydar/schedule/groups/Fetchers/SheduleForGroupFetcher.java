package com.aydar.schedule.groups.Fetchers;

import android.net.Uri;
import android.util.Log;

import com.aydar.schedule.parser.API;
import com.aydar.schedule.parser.Fetchers.SheduleFetcher;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aydar on 01.02.2017.
 */

public class SheduleForGroupFetcher extends SheduleFetcher {

    private static final String TAG = "SheduleForGroupFetcher";

    private List<API.Shedule> fetchShedule(int groupId) {

        List<API.Shedule> shedule = new ArrayList<>();

        try {
            String url = Uri.parse(getURL() + "get_shedule_for_group.php")
                    .buildUpon()
                    .appendQueryParameter("group_id", String.valueOf(groupId))
                    .build().toString();
            String jsonBody = getUrlString(url);
            Log.i(TAG, "Received JSON: " + jsonBody);
            parseShedule(shedule, jsonBody);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch shedule", ioe);
        }
        return shedule;
    }

    public List<List<API.Shedule>> getSheduleForDays(int groupId) {

        List<API.Shedule> shedule = fetchShedule(groupId);
        return super.getSheduleForDays(shedule);
    }
}
