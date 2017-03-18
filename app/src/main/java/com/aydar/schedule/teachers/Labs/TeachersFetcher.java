package com.aydar.schedule.teachers.Labs;

import android.net.Uri;
import android.util.Log;

import com.aydar.schedule.parser.API;
import com.aydar.schedule.parser.Fetchers.Fetcher;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aydar on 21.02.2017.
 */

public class TeachersFetcher extends Fetcher {
    private static final String TAG = "TeachersFetcher";

    public List<API.Teacher> getTeachersList() {

        List<API.Teacher> teachers = new ArrayList<>();

        try {
            String url = Uri.parse(getURL() + "get_teachers_list.php")
                    .buildUpon()
                    .build().toString();
            String jsonString = getUrlString(url);

            Log.i(TAG, "Received JSON: " + jsonString);
            parseTeachersList(teachers, jsonString);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch shedule", ioe);
        }
        return teachers;
    }

    private void parseTeachersList(List<API.Teacher> teachers, String  jsonBody)
            throws IOException, JSONException {
        API api = parse(jsonBody);
        if (api.teachers != null) {
            teachers.addAll(api.teachers);
        }
    }
}
