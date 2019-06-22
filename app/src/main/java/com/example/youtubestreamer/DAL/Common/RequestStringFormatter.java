package com.example.youtubestreamer.DAL.Common;

import java.util.Iterator;
import java.util.Map;

// RequestStringFormatter is a static utility class for constructing API request strings.
public class RequestStringFormatter
{
    // The supported API's URLs:
    public static final String YOUTUBE_API_URL = "https://www.googleapis.com/youtube/v3/search";
    public static final String SPOTIFY_API_URL = "TBD... XD OwO pounces on you";

    // Given an API's URL and a set of request arguments, this method constructs a request string
    // that will retrieve the desired data.
    public static String formatRequestString(
        String apiURL,
        Map<String, String> queryArguments)
    {
        String requestString = apiURL + "?";

        Iterator argumentIterator = queryArguments.entrySet().iterator();
        boolean nextElementExists = argumentIterator.hasNext();
        while (nextElementExists) {
            Map.Entry pair = (Map.Entry) argumentIterator.next();
            requestString += pair.getKey() + "=" + pair.getValue();
            argumentIterator.remove();

            nextElementExists = argumentIterator.hasNext();
            if (argumentIterator.hasNext())
            {
                requestString += "&";
            }
        }

        return requestString;
    }
}
