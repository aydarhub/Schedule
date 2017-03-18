package com.aydar.schedule.teachers.Fetchers;

import android.net.Uri;
import android.util.Log;

import com.aydar.schedule.parser.API;
import com.aydar.schedule.parser.Fetchers.SheduleFetcher;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aydar on 20.02.2017.
 */

public class SheduleForTeacherFetcher extends SheduleFetcher {

    private static final String TAG = "SheduleTeacherFetcher";

    private List<API.Shedule> fetchShedule(int teacherId) {

        List<API.Shedule> shedule = new ArrayList<>();

        try {
            String url = Uri.parse(getURL() + "get_shedule_for_teacher.php")
                    .buildUpon()
                    .appendQueryParameter("teacher_id", String.valueOf(teacherId))
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

    public List<List<API.Shedule>> getSheduleForDays(int teacherId) {

        List<API.Shedule> shedule = fetchShedule(teacherId);
        return super.getSheduleForDays(shedule);
    }

}
