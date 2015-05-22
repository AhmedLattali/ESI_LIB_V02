package com.devmobile.cs2.esi_lib.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
public class GetUserByNomEtMPasseTask extends AsyncTask<Void,Void,String> {

    private Context context ;
    private String nom ;
    private String mot_de_passe ;
    private Intent intent ;
    ProgressDialog dialog ;

    public GetUserByNomEtMPasseTask(Context context, Intent intent , String nom, String mot_de_passe){
        this.context=context ;
        this.nom=nom ;
        this.mot_de_passe=mot_de_passe ;
        this.intent=intent ;
    }


    @Override
    protected String doInBackground(Void... params) {
        //Emulateur

        String url ="http://192.168.43.131:8080/getuserbynometmpasse?nom='"+nom+"'"+"&passe='"+mot_de_passe+"'";
        HttpClient httpClient =new DefaultHttpClient() ;
        HttpGet httpGet = new HttpGet(url) ;
        String resultat ="aa" ;
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet) ;
            resultat= EntityUtils.toString(httpResponse.getEntity()) ;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultat ;
    }


    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setMessage(" Connection en cours ...");
        dialog.show();
    }

    @Override
    protected void onPostExecute(String s) {
        if(!s.equals("{}")){
            context.startActivity(intent);
            dialog.dismiss();
           // Toast.makeText(context, "true", Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(context, "Pseudo ou mot de passe incorrect", Toast.LENGTH_LONG).show();
        }
    }


}