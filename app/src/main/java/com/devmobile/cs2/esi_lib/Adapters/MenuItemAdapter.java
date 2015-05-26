package com.devmobile.cs2.esi_lib.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.devmobile.cs2.esi_lib.Models.NavMenuItem;
import com.devmobile.cs2.esi_lib.R;

import java.util.ArrayList;


public class MenuItemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<NavMenuItem> NavMenuItems;

    //  public static int index =0 ;

    public MenuItemAdapter(Context context, ArrayList<NavMenuItem> NavMenuItems) {
        this.context = context;
        this.NavMenuItems = NavMenuItems;

    }

    @Override
    public int getCount() {
        return NavMenuItems.size();
    }

    @Override
    public Object getItem(int position) {
        return NavMenuItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void saveCategory(String category,boolean valeur) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("CategoryLivres", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putBoolean(category, valeur);

        editor.commit();

    }

    public boolean getSavedCategory(String category) {

        return context.getSharedPreferences("CategoryLivres", Context.MODE_PRIVATE).getBoolean(category, false);
    }

    public void removeCategory(String category) {

        SharedPreferences sharedPreferences = context.getSharedPreferences("CategoryLivres", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putBoolean(category, false);


    }

/*
    public Set<String> load() {
        HashSet<String> chaine = new HashSet<String>();
        Set<String> chaineSaved = getSavedCategory();

        if (chaineSaved != null) {
            chaine.addAll(getSavedCategory());
            Toast.makeText(context, chaine.toString().replace("[", "").replace("]", ""), Toast.LENGTH_LONG).show();
            return chaineSaved;

        } else {
            Toast.makeText(context, "Aucun texte sauvgarder ", Toast.LENGTH_SHORT).show();
            return new HashSet<String>();
        }

    }
*/
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            //       index++;
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.liste_menu_range, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        final ImageView favoris = (ImageView) convertView.findViewById(R.id.favoris);
        final int positionTxt = position;
        if (position == 0 || position == 6) {
            favoris.setVisibility(View.INVISIBLE);

        } else {

          boolean categorySelected = getSavedCategory(NavMenuItems.get(positionTxt).getTitre());

            if (categorySelected) {

                favoris.setImageResource(R.drawable.ic_favorite_full_pressed);

            } else {

                favoris.setImageResource(R.drawable.ic_favorite_empty_pressed);
            }

            favoris.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    boolean categorySelected = getSavedCategory(NavMenuItems.get(positionTxt).getTitre());

                    if (!categorySelected) {

                        Log.e("categAdd",NavMenuItems.get(positionTxt).getTitre());
                        favoris.setImageResource(R.drawable.ic_favorite_full_pressed);

                        saveCategory(NavMenuItems.get(positionTxt).getTitre(),true);

                        //                 v.setTag(R.drawable.ic_favorite_full_pressed);

                    } else {
                        //    v.invalidate();
                        favoris.setImageResource(R.drawable.ic_favorite_empty_pressed);
                        Log.e("categRem", NavMenuItems.get(positionTxt).getTitre());

                        removeCategory(NavMenuItems.get(positionTxt).getTitre());

                        //               v.setTag(R.drawable.ic_favorite_empty_pressed);
                    }
                }
            });

        }


        imgIcon.setImageResource(NavMenuItems.get(position).getIcon());
        txtTitle.setText(NavMenuItems.get(position).getTitre());
        return convertView;
    }


}
