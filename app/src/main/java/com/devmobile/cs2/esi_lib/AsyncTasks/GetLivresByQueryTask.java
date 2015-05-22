package com.devmobile.cs2.esi_lib.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by Ahmed-PC on 22-05-2015.
 */
public class GetLivresByQueryTask extends AsyncTask<Void,Void,String> {

    private Context context ;
    private String query ;
    ProgressDialog dialog ;

    public GetLivresByQueryTask(Context context, String query){
        this.context=context ;
        this.query=query ;
    }


    @Override
    protected String doInBackground(Void... params) {
        //Emulateur
      /*  String url ="http://192.168.131.43:8080/getlivrebyquery?query='"+query+"'" ;
        HttpClient httpClient =new DefaultHttpClient() ;
        HttpGet httpGet = new HttpGet(url) ;*/
        String resultat ="aa" ;
       /* try {
            HttpResponse httpResponse = httpClient.execute(httpGet) ;
            resultat= EntityUtils.toString(httpResponse.getEntity()) ;
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        return resultat ;
    }

    @Override
    protected void onPostExecute(String s) {
        //  Log.d("tagg", s) ;
      //  Toast.makeText(context, s, Toast.LENGTH_LONG).show();
        dialog.dismiss();

    }
    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setMessage(" Recherche en cours ...");
        dialog.show();
    }

}
