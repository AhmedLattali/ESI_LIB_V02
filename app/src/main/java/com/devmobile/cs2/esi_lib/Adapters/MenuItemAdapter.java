package com.devmobile.cs2.esi_lib.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.devmobile.cs2.esi_lib.Models.NavMenuItem;
import com.devmobile.cs2.esi_lib.R;

import java.util.ArrayList;


public class MenuItemAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<NavMenuItem> NavMenuItems;
    public static int index =0 ;

    public MenuItemAdapter(Context context, ArrayList<NavMenuItem> NavMenuItems){
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
        return position ;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            index++;
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.liste_menu_range, null);
        }

        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.title);
        final ImageView favoris = (ImageView) convertView.findViewById(R.id.favoris);

        if(index==7){
            favoris.setVisibility(View.INVISIBLE);

        }else{
            favoris.setTag(R.drawable.ic_favorite_empty_pressed);


            favoris.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if ((Integer)v.getTag() == R.drawable.ic_favorite_empty_pressed) {
                        //   v.invalidate();
                        favoris.setImageResource(R.drawable.ic_favorite_full_pressed);

                        v.setTag(R.drawable.ic_favorite_full_pressed);
                    }else {
                        //    v.invalidate();
                        favoris.setImageResource(R.drawable.ic_favorite_empty_pressed);
                        v.setTag(R.drawable.ic_favorite_empty_pressed);
                    }
                }
            });

        }


        imgIcon.setImageResource(NavMenuItems.get(position).getIcon());
        txtTitle.setText(NavMenuItems.get(position).getTitre());
        return convertView;
    }



}
