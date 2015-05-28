package com.devmobile.cs2.esi_lib.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by oSunshine on 27/05/2015.
 */
public class RatingTask extends AsyncTask<Void,Void,String> {

    private Context context ;
    private String query ;

    String httpcode;

    public RatingTask(Context context, String query) {

        Log.e("query",query);
        this.context = context;
        this.query = query;

    }

    @Override
    protected String doInBackground(Void... params) {

        String url ="http://192.168.1.2:8080/rateLivre?"+query ;
        HttpClient httpClient =new DefaultHttpClient() ;
        HttpGet httpGet = new HttpGet(url) ;
        String resultat ="" ;
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet) ;
            httpcode = ((Integer) httpResponse.getStatusLine().getStatusCode()).toString() ;
            resultat= EntityUtils.toString(httpResponse.getEntity()) ;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultat ;
    }
}
