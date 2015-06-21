package com.devmobile.cs2.esi_lib;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.devmobile.cs2.esi_lib.AsyncTasks.GetNewLivresTask;

import java.util.concurrent.ExecutionException;

/**
 * Created by Oo on 20/05/2015.
 */

public class ServiceTest extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent){

        String resultat="" ;
        try {

            // verfier s'il ya du nouveau livres ;
            resultat = new GetNewLivresTask(context, "admin").execute().get() ;


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(!resultat.equals("") && !resultat.equals("[]")){

            notification(context,resultat );
        }


    }
    private void notification(Context context, String gson){
        Intent intent = new Intent(context, ListeLivres.class);
        intent.putExtra("gson",gson) ;
        PendingIntent pendingIntent= PendingIntent.getActivity(context,0,intent,0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        //long[] pattern = {500,500,500,1000};
        //builder.setVibrate(pattern);
        Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarm);

        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle("  Nouveaux livres   ");
        builder.setContentText("Cliquer pour les voir") ;
        //builder.setContentText("aaaa") ;
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(1, builder.build());

    }
}
