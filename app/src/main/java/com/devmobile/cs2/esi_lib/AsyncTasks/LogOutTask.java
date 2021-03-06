package com.devmobile.cs2.esi_lib.AsyncTasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import com.devmobile.cs2.esi_lib.LoginActivity;

/**
 * Created by Ahmed-PC on 22-05-2015.
 */
public class LogOutTask extends AsyncTask<Void,Void,Void> {


    private ProgressDialog dialog ;
    private  Context context ;

    public LogOutTask(Context context ){
        this.context=context ;

    }
    @Override
    protected Void doInBackground(Void... params) {
        return null;
    }




    @Override
    protected void onPreExecute() {

     //   super.onPreExecute();
        dialog = new ProgressDialog(context);
        dialog.setMessage("  Déconnection en cours ...");
        dialog.show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
       // super.onPostExecute(aVoid);

        saveCategory("logedIn",false);
       // context.getSharedPreferences("User", Context.MODE_PRIVATE).getBoolean("logedIn", false);
       context.startActivity(new Intent(context, LoginActivity.class));
        dialog.dismiss();
    }


    public void saveCategory(String category, boolean valeur) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("User", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(category, valeur);

        editor.commit();

    }
}
