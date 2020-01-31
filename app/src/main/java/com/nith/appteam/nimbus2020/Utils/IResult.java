package com.nith.appteam.nimbus2020.Utils;

import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IResult {
    public void notifySuccess(String requestType, JSONObject response, JSONArray jsonArray);

    public void notifyError(String requestType, VolleyError error);
}