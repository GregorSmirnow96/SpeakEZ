package com.example.youtubestreamer.DAL.YouTubeDataAccess;

import android.os.AsyncTask;

import com.example.youtubestreamer.Config;
import com.example.youtubestreamer.DAL.Common.IAPIRequest;
import com.example.youtubestreamer.DAL.Common.IAPIResponse;
import com.example.youtubestreamer.DAL.Common.RequestStringFormatter;
import com.example.youtubestreamer.DAL.YouTubeDataAccess.YouTubeDataModel.YouTubeAPIResponseData;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// YouTubeAPIRequest holds the data required to send a query to YouTube's API. It provids a
// constructor for ease of creating YouTube API request strings.
public class YouTubeAPIRequest extends AsyncTask<String, Void, YouTubeAPIResponseData> implements IAPIRequest
{
    private final String mQueryString;

    // YouTubeAPIRequest's constructor. It creates the request string from the provided parameters
    // using the static RequestStringFormatter utility class.
    public YouTubeAPIRequest(
        String songTitle,
        Integer maxReults)
    {
        Map<String, String> requestArguments = new HashMap<>();
        requestArguments.put("part", "snippet");
        requestArguments.put("order", "viewCount");
        requestArguments.put("q", songTitle);
        requestArguments.put("maxResults", maxReults.toString());
        requestArguments.put("key", Config.YOUTUBE_API_KEY);

        mQueryString = RequestStringFormatter.formatRequestString(
            RequestStringFormatter.YOUTUBE_API_URL,
            requestArguments);
    }

    @Override
    protected YouTubeAPIResponseData doInBackground(String... parameters)
    {
        try
        {
            return send();
        }
        catch (Exception e)
        {
            System.out.println();
        }

        return null;
    }

    @Override
    public YouTubeAPIResponseData send() throws IOException, JSONException {
        // Send request string.
        // Parse response into a YouTubeAPIResponseData object.
        Document responseDocument = Jsoup
            .connect(this.mQueryString)
            .ignoreContentType(true)
            .timeout(10 * 1000)
            .get();
        String responseString = responseDocument.text();

        JSONObject jsonObject = (JSONObject) new JSONTokener(responseString).nextValue();
        JSONObject jsonItem1 = jsonObject.getJSONArray("items").getJSONObject(0);
        JSONObject jsonID = jsonItem1.getJSONObject("id");
        String videoID = jsonID.getString("videoId");

        return null;
    }
}
