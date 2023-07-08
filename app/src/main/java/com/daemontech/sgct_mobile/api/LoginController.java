package com.daemontech.sgct_mobile.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import com.android.volley.NetworkError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginController {
    private static final String TAG = LoginController.class.getSimpleName();

    private final String loginUrl;
    private final RequestQueue requestQueue;
    private final SharedPreferences preferences;

    public LoginController(Context context, String loginUrl) {
        this.loginUrl = loginUrl;
        this.requestQueue = Volley.newRequestQueue(context);
        this.preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public interface LoginCallback {
        void onSuccess(String username,String role);

        void onError(String message);
    }

    public void login(String username, String password, LoginCallback callback) {
        JSONObject params = new JSONObject();
        try {
            params.put("usuario", username);
            params.put("contrasenia", password);
        } catch (JSONException e) {
            Log.e(TAG, "Error creating JSON params", e);
            callback.onError("Error de aplicación");
            return;
        }

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, loginUrl, params,
                response -> {
                    try {
                        String result = response.getString("result");
                        if (!result.equals("success")) {
                            callback.onError("Contrasenia o usuario invalidos.");
                            return;
                        }
                        result = response.getString("result");
                        String role = response.getString("rol");
                        String user = response.getString("usuario");

                        preferences.edit().putString("rol", role).apply();
                        preferences.edit().putString("usuario",user).apply();
                        callback.onSuccess(user,role);
                    } catch (JSONException e) {
                        Log.e(TAG, "Error parsing JSON response", e);
                        callback.onError("Error de aplicación");
                    }
                },
                error -> {
                    if (error instanceof NetworkError) {
                        callback.onError("Error de red");
                    } else {
                        callback.onError("Error de aplicación");
                    }
                });

        requestQueue.add(request);

    }
}
