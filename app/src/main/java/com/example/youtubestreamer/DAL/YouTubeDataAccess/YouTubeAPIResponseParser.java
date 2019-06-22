package com.example.youtubestreamer.DAL.YouTubeDataAccess;

import com.example.youtubestreamer.DAL.Common.IAPIResponseParser;
import com.example.youtubestreamer.DAL.Common.ISongData;
import com.example.youtubestreamer.DAL.YouTubeDataAccess.YouTubeDataModel.YouTubeAPIResponseData;

import java.util.List;

// YouTubeAPIResponseParser is YouTube's implementation of IAPIResponseParser.
// It defines the protocal for retrieving data from a YouTube API response.
public class YouTubeAPIResponseParser implements IAPIResponseParser
{
    private final YouTubeAPIResponseData data;

    public YouTubeAPIResponseParser(YouTubeAPIResponseData data)
    {
        this.data = data;
    }

    @Override
    public List<ISongData> getSongData()
    {
        // IMPLEMENT THIS.
        return null;
    }
}
