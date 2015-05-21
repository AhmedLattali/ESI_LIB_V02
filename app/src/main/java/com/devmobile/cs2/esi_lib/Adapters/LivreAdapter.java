package com.devmobile.cs2.esi_lib.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.devmobile.cs2.esi_lib.Models.Livre;
import com.devmobile.cs2.esi_lib.R;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

public class LivreAdapter extends ArrayAdapter<Livre>{

    Context context;
    int layoutResourceId;
    ArrayList<Livre> data ;


    public LivreAdapter(Context context, int layoutResourceId ,ArrayList<Livre>data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        LivreHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new LivreHolder();
            holder.imageView = (ImageView)row.findViewById(R.id.image);
            holder.tvTitre = (TextView)row.findViewById(R.id.titre);
            holder.tvAuteur = (TextView)row.findViewById(R.id.auteur);
            holder.tvCategorie = (TextView)row.findViewById(R.id.categorie);
            holder.tvAnnee = (TextView)row.findViewById(R.id.annee);

            row.setTag(holder);
        }
        else
        {
            holder = (LivreHolder)row.getTag();
        }

        Livre Livre = data.get(position);

        if(Livre.getImage()==null){

        }
        else{
            ByteArrayInputStream imageStream = new ByteArrayInputStream(Livre.getImage());
            Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);
            holder.imageView.setImageBitmap(imageBitmap);
        }




        holder.tvTitre.setText(Livre.getTitre());
        holder.tvAuteur.setText(Livre.getAuteur());
        holder.tvCategorie.setText(Livre.getCategorie());
        holder.tvAnnee.setText(Livre.getAnnee());


        return row;
    }

    static class LivreHolder
    {
        ImageView imageView;
        TextView tvTitre;
        TextView tvAuteur;
        TextView tvCategorie;
        TextView tvAnnee;
    }
}
