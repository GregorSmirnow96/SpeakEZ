package com.example.youtubestreamer.DAL;

import android.os.AsyncTask;

import com.example.youtubestreamer.DAL.Common.ISongData;
import com.example.youtubestreamer.DAL.YouTubeDataAccess.YouTubeAPIRequest;
import com.example.youtubestreamer.DAL.YouTubeDataAccess.YouTubeAPIResponseParser;
import com.example.youtubestreamer.DAL.YouTubeDataAccess.YouTubeDataModel.YouTubeAPIResponseData;
import com.example.youtubestreamer.DAL.YouTubeDataAccess.YouTubeSongData;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StaticAccess
{
    public static List<YouTubeSongData> QueryYouTubeSongData(YouTubeAPIRequest youTubeAPIRequest)
    {
        YouTubeAPIResponseData response = null;
        try
        {
            response = youTubeAPIRequest.executeOnExecutor(
                AsyncTask.THREAD_POOL_EXECUTOR,
                null).get();
        }
        catch (Exception e) {}

        YouTubeAPIResponseParser parser = new YouTubeAPIResponseParser(response);
        List<ISongData> songData = parser.getSongData();

        List<YouTubeSongData> youTubeSongData = new ArrayList<>();
        for (ISongData data : songData)
        {
            youTubeSongData.add((YouTubeSongData) data);
        }

        return youTubeSongData;
    }
}
