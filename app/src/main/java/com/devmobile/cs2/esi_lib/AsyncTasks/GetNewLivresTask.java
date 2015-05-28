package com.devmobile.cs2.esi_lib.AsyncTasks;

/**
 * Created by Ahmed-PC on 26-05-2015.
 */

import android.content.Context;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Ahmed-PC on 22-05-2015.
 */
public class GetNewLivresTask extends AsyncTask<Void,Void,String> {

    private Context context ;
    private String user ;
    String httpcode;

    public GetNewLivresTask(Context context, String user){
        this.context=context ;
        this.user=user ;
    }


    @Override
    protected String doInBackground(Void... params) {
        //Emulateur

        String url ="http://192.168.1.2:8080/getnewlivres?user='"+user+"'";
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

    @Override
    protected void onPostExecute(String s) {
        // Toast.makeText(context, httpcode, Toast.LENGTH_LONG).show();

        if(httpcode==null || !httpcode.equals("200")){
          //  Toast.makeText(context, "Impossible d'établir une connection", Toast.LENGTH_LONG).show();

        }

        else{
            if(!s.equals("{}")){

                // Toast.makeText(context, "true", Toast.LENGTH_LONG).show();
            }

            else{
              //  Toast.makeText(context, "Pseudo ou mot de passe incorrect", Toast.LENGTH_LONG).show();

            }
        }
    }


}

