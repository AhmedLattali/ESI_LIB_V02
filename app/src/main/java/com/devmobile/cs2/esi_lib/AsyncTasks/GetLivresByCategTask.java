package com.devmobile.cs2.esi_lib.AsyncTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Ahmed-PC on 22-05-2015.
 */
public class GetLivresByCategTask extends AsyncTask<Void,Void,String>{


        private Context context ;
        private String categ ;
    String httpcode;
        public GetLivresByCategTask(Context context, String categ){
            this.context=context ;
            this.categ=categ ;
        }


        @Override
        protected String doInBackground(Void... params) {
            //Emulateur
            String url ="http://192.168.43.131:8080/getlivrebycateg?categ='"+categ+"'" ;
            HttpClient httpClient =new DefaultHttpClient() ;
            HttpGet httpGet = new HttpGet(url) ;
            String resultat ="aa" ;
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

            if(!httpcode.equals("200")){
                Toast.makeText(context, "Impossible d'établir une connection", Toast.LENGTH_LONG).show();

            }

            else{
                if(!s.equals("{}")){

                    // Toast.makeText(context, "true", Toast.LENGTH_LONG).show();
                }

                else{
                    Toast.makeText(context, "Pas de resultat", Toast.LENGTH_LONG).show();

                }
            }

        }
}
