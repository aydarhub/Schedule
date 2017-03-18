package com.aydar.schedule.groups.Fetchers;

import android.net.Uri;
import android.util.Log;

import com.aydar.schedule.parser.API;
import com.aydar.schedule.parser.Fetchers.Fetcher;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aydar on 13.02.2017.
 */

public class GroupsFetcher extends Fetcher {

    private static final String TAG = "GroupsFetcher";

    public List<API.Group> getGroupsList() {

        List<API.Group> groups = new ArrayList<>();

        try {
            String url = Uri.parse(getURL() + "get_groups_list.php")
                    .buildUpon()
                    .build().toString();
            String jsonString = getUrlString(url);

            Log.i(TAG, "Received JSON: " + jsonString);
            parseGroupsList(groups, jsonString);
        } catch (JSONException je) {
            Log.e(TAG, "Failed to parse JSON", je);
        } catch (IOException ioe) {
            Log.e(TAG, "Failed to fetch shedule", ioe);
        }
        return groups;
    }

    private void parseGroupsList(List<API.Group> groups, String  jsonBody)
            throws IOException, JSONException {
        API api = parse(jsonBody);
        if (api.groups != null) {
            groups.addAll(api.groups);
        }
    }
}
