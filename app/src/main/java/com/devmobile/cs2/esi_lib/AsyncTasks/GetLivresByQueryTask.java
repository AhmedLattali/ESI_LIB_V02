package com.devmobile.cs2.esi_lib.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.devmobile.cs2.esi_lib.Adapters.LivreAdapter;
import com.devmobile.cs2.esi_lib.ListeLivresFragement;
import com.devmobile.cs2.esi_lib.Models.Livre;
import com.devmobile.cs2.esi_lib.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ahmed-PC on 22-05-2015.
 */
public class GetLivresByQueryTask extends AsyncTask<Void,Void,String> {

    private Context context ;
    private String query ;
    private ListView listView ;
    ProgressDialog dialog ;

    public GetLivresByQueryTask(Context context,ListView listView, String query){
        this.context=context ;
        this.query=query ;
        this.listView = listView ;
    }


    @Override
    protected String doInBackground(Void... params) {
        //Emulateur
       String url ="http://192.168.43.131:8080/getlivresbyquery?query='"+query+"'" ;
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
    protected void onPostExecute(String s) {

        Type type = new TypeToken<List<Livre>>(){}.getType();
        ArrayList<Livre> l = new Gson().fromJson(s, type);
        LivreAdapter monAdapteteur = new LivreAdapter(context, R.layout.liste_livres_range,l);
        dialog.dismiss();
        listView = ListeLivresFragement.listLivreView ;
        listView.setAdapter(monAdapteteur);


    }
    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setMessage(" Recherche en cours ...");
        dialog.show();
    }

}
