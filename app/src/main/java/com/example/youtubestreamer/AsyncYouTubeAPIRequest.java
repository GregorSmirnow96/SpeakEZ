package com.example.youtubestreamer;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class AsyncYouTubeAPIRequest extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... parameters) {
        try
        {
            queueSong(parameters[0]);
        }
        catch (Exception e) { }

        return null;
    }

    private void queueSong(String searchKeywordWithSpaces) throws IOException, JSONException
    {
        String keyword = searchKeywordWithSpaces.replace(" ", "+");

        String url = createYouTubeAPIRequestString(keyword);

        Document doc = Jsoup.connect(url).ignoreContentType(true).timeout(10 * 1000).get();
        String getJson = doc.text();

        JSONObject jsonObject = (JSONObject) new JSONTokener(getJson ).nextValue();
        JSONObject jsonItem1 = jsonObject.getJSONArray("items").getJSONObject(0);
        JSONObject jsonID = jsonItem1.getJSONObject("id");
        String videoID = jsonID.getString("videoId");

        Playlist.getInstance().cueSong(videoID);
    }

    private String createYouTubeAPIRequestString(String keyword)
    {
        return
            "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&order=viewCount&q="
                + keyword + "&key=" + Config.YOUTUBE_API_KEY;
    }
}
