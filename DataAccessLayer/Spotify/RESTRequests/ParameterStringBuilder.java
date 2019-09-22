package com.example.speakez.DataAccessLayer.Spotify.RESTRequests;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

public class ParameterStringBuilder
{
    public static String getParametersString(Map<String, String> parameters)
        throws UnsupportedEncodingException
    {
        StringBuilder parameterStringBuilder = new StringBuilder();
        parameterStringBuilder.append("?");

        for (Map.Entry<String, String> entry : parameters.entrySet())
        {
            parameterStringBuilder.append(
                URLEncoder.encode(entry.getKey(),
                "UTF-8"));
            parameterStringBuilder.append("=");
            parameterStringBuilder.append(
                URLEncoder.encode(entry.getValue(),
                "UTF-8"));
            parameterStringBuilder.append("&");
        }

        String resultString = parameterStringBuilder.toString();
        return resultString.length() > 0
            ? resultString.substring(0, resultString.length() - 1)
            : resultString;
    }
}
