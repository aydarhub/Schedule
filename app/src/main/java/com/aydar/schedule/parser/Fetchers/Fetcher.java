package com.aydar.schedule.parser.Fetchers;

import com.aydar.schedule.parser.API;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by aydar on 13.02.2017.
 */

public class Fetcher {

    private static final String URL = "http://192.168.0.103/";

    protected static String getURL() {
        return URL;
    }

    private byte[] getUrlBytes(String urlSpec) throws IOException {
        java.net.URL url = new URL(urlSpec);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            InputStream in = connection.getInputStream();

            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new IOException(connection.getResponseMessage() +
                        ": with " +
                        urlSpec);
            }

            int bytesRead;
            byte[] buffer = new byte[1024];
            while ((bytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, bytesRead);
            }
            out.close();
            return out.toByteArray();
        } finally {
            connection.disconnect();
        }
    }

    protected String getUrlString(String urlSpec) throws IOException {
        return new String(getUrlBytes(urlSpec));
    }

    protected API parse(String  jsonBody)
            throws IOException, JSONException {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(jsonBody, API.class);
    }
}
