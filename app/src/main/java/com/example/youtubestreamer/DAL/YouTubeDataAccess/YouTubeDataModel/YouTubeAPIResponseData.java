package com.example.youtubestreamer.DAL.YouTubeDataAccess.YouTubeDataModel;

import com.example.youtubestreamer.DAL.Common.IAPIResponse;

import java.util.List;

public class YouTubeAPIResponseData implements IAPIResponse
{
    public final String kind;
    public final String etag;
    public final String nextPageToken;
    public final String regionCode;
    public final YouTubePageInfo pageInfo;
    public final List<YouTubeItem> items;

    // This constructor will most likely need to be refactors. JSON deserialization should be used
    // with the response from the YouTube API.
    public YouTubeAPIResponseData(
        String kind,
        String etag,
        String nextPageToken,
        String regionCode,
        YouTubePageInfo pageInfo,
        List<YouTubeItem> items)
    {
        this.kind = kind;
        this.etag = etag;
        this.nextPageToken = nextPageToken;
        this.regionCode = regionCode;
        this.pageInfo = pageInfo;
        this.items = items;
    }
}
