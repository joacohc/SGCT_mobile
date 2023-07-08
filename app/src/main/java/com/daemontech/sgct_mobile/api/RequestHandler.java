package com.daemontech.sgct_mobile.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

    /**
     * Created by Belal on 9/9/2017.
     */
    public class RequestHandler {

        //Method to send httpPostRequest
        //This method is taking two arguments
        //First argument is the URL of the script to which we will send the request
        //Other is an HashMap with name value pairs containing the data to be send with the request
        public String sendPostRequest(String requestURL, JSONObject jsonObject) {
            //Creating a URL
            URL url;

            //StringBuilder object to store the message retrieved from the server
            StringBuilder sb = new StringBuilder();
            try {

                //Initializing Url
                url = new URL(requestURL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(jsonObject.toString());
                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();
                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    sb = new StringBuilder();
                    String response;
                    while ((response = br.readLine()) != null) {
                        sb.append(response);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sb.toString();
        }

        public String sendGetRequest(String requestURL) {
            StringBuilder sb = new StringBuilder();
            try {
                URL url = new URL(requestURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String s;
                while ((s = bufferedReader.readLine()) != null) {
                    sb.append(s + "\n");
                }
            } catch (Exception e) {
            }
            return sb.toString();
        }


        private String getPostDataString(HashMap<String, String> params) throws JSONException, UnsupportedEncodingException {
            JSONObject postData = new JSONObject();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                postData.put(entry.getKey(), entry.getValue());
            }
            return postData.toString();
        }
    }
