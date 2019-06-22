package com.example.youtubestreamer.DAL.Common;

import java.util.List;

// IAPIResponseParser is the interface for data structures returned by song providers' APIs.
public interface IAPIResponseParser
{
    public List<ISongData> getSongData();
}
