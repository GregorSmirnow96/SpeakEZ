package com.example.speakez.DataAccessLayer.Spotify.RESTRequests;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class RequestSender extends AsyncTask<Void, Void, Void>
{
    private final String mEndpoint;
    private final String mRequestMethod;
    private final Map<String, String> mRequestParameters;
    private final Map<String, String> mRequestHeaderValues;
    private final String mRequestBody;
    private HttpURLConnection mURLConnection;
    public boolean mResponseHasBeenReceived;
    public String mResponse;

    public RequestSender(
        String endpoint,
        String requestMethod,
        Map<String, String> requestParameters,
        Map<String, String> requestHeaderValues,
        String requestBody)
    {
        mEndpoint = endpoint;
        mRequestMethod = requestMethod;
        mRequestParameters = requestParameters;
        mRequestHeaderValues = requestHeaderValues;
        mRequestBody = requestBody;
        mResponseHasBeenReceived = false;
        mResponse = null;
    }

    @Override
    protected Void doInBackground(Void... voids)
    {
        try
        {
            sendRequest();
        }
        catch (Exception e)
        {
            System.out.print(2);
            // TODO: Log exception?
        }

        return null;
    }

    private void sendRequest() throws IOException
    {
        String fullURL = mEndpoint + (mRequestParameters.size() > 0
            ? ParameterStringBuilder.getParametersString(mRequestParameters)
            : "");

        mURLConnection = getURLConnection(fullURL);

        mURLConnection.setRequestMethod(mRequestMethod);
        setRequestHeader(
            mURLConnection,
            mRequestHeaderValues);
        setRequestBody();

        mURLConnection.connect();

        InputStream inputStream = mURLConnection.getInputStream();
        try
        {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String nextLine;
            while ((nextLine = bufferedReader.readLine()) != null)
            {
                mResponse += nextLine;
            }
        }
        catch (IOException e)
        {
            // TODO: Log the error?
        }

        mURLConnection.disconnect();

        mResponseHasBeenReceived = true;
    }

    private HttpURLConnection getURLConnection(String endpoint) throws IOException
    {
        URL url = new URL(endpoint);
        return (HttpURLConnection) url.openConnection();
    }

    private void setRequestBody()
    {
        if (mRequestBody == null)
        {
            return;
        }

        mURLConnection.setDoOutput(true);

        try
        {
            DataOutputStream outputStream = new DataOutputStream(mURLConnection.getOutputStream());

            byte[] input = mRequestBody.getBytes("utf-8");
            outputStream.write(input, 0, input.length);

            outputStream.flush();
            outputStream.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void setRequestHeader(
        HttpURLConnection urlConnection,
        Map<String, String> requestParameters)
    {
        for (Map.Entry<String, String> entry : requestParameters.entrySet())
        {
            urlConnection.setRequestProperty(
                entry.getKey(),
                entry.getValue());
        }
    }

    public String waitForResponse()
    {
        while (!mResponseHasBeenReceived);
        return mResponse;
    }
}
