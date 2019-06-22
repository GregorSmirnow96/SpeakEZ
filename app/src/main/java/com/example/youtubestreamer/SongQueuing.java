package com.example.youtubestreamer;

import android.os.AsyncTask;

public class SongQueuing
{
    public static void cueSong(String songTitle)
    {
        AsyncYouTubeAPIRequest request = new AsyncYouTubeAPIRequest();
        String[] songTitleParameter = {songTitle};
        request.executeOnExecutor(
                AsyncTask.THREAD_POOL_EXECUTOR,
                songTitleParameter);
    }
}
