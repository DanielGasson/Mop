package mop.main.java.backend.services;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import mop.main.java.backend.services.responses.MovieFinderResponse;
import mop.main.java.backend.utilities.Log;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.Logger;

public class MovieFinderService {

    private static final Logger log = Log.getLog(MovieFinderService.class);

    private final Gson jsonConverter;

    public MovieFinderService() {

        jsonConverter = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
            .create();
    }

    public MovieFinderResponse requestMovie(String title, String year) throws IOException {

        if(title == null || title.equals("")) {

            log.error("Unable to request movie from null or empty title.");
            throw new IllegalArgumentException("title");
        }

        String requestUrl = this.constructRequestUrl(title, year);

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet request = new HttpGet(requestUrl);

        StringBuilder jsonResponse = new StringBuilder();

        try (CloseableHttpResponse response = httpclient.execute(request)) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String inputLine;

            while ((inputLine = reader.readLine()) != null) {

                jsonResponse.append(inputLine);
            }

            reader.close();
        }

        return jsonConverter.fromJson(jsonResponse.toString(), MovieFinderResponse.class);
    }

    private String constructRequestUrl(String title, String year) {

        String requestFriendlyTitle = title.replaceAll(" ", "+"); // spaces in a title should be replaced with '+'
        if(year == null) {

            year = "";
        }

        String baseUrl = "http://www.omdbapi.com/";
        return baseUrl + "?" + "t=" + requestFriendlyTitle + "&" + "y=" + year + "&" + "plot=short" + "&" + "r=json";
    }
}
