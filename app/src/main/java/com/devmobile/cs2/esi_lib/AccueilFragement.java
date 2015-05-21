package com.devmobile.cs2.esi_lib;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class AccueilFragement extends Fragment {

    Fragment fragment1 ;
    Fragment fragment2 ;
    Fragment fragment3 ;
    Fragment fragment4 ;
    Fragment fragment5 ;




    public AccueilFragement() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        // chargerFragments(1) ;
        // chargerFragments(2) ;
        // chargerFragments(3) ;
        // chargerFragments(4) ;
        // chargerFragments(5) ;
        Bundle bundle = new Bundle();
        bundle.putString("categ", "0");
        fragment1 = new ListeLivresFragement();
        fragment1.setArguments(bundle);
        FragmentTransaction ft =getFragmentManager().beginTransaction() ;
        ft.replace(R.id.frame1, fragment1).commit() ;

        /*Bundle bundle2 = new Bundle();
        bundle2.putString("categ", "2");
        fragment2 = new ListeLivresFragement();
        fragment2.setArguments(bundle2);
       // fragmentManager= getFragmentManager();
        ft.add(R.id.frame2, fragment2,"2")  ;
        ft.commit() ;*/
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        return inflater.inflate(R.layout.accueil_fragement, container, false);

    }

    /*private void chargerFragments(int i) {
        Bundle bundle = new Bundle();
        FragmentManager fragmentManager ;
        bundle.putString("categ", ((Integer) i).toString());

        switch (i){
            case 1 :
                fragment1 = new ListeLivresFragement();
                fragment1.setArguments(bundle);
                fragmentManager= getFragmentManager() ;
                fragmentManager.beginTransaction()
                        .replace(R.id.frame1, fragment1).commit();
                break ;
            case 2 :
                fragment2 = new ListeLivresFragement();
                fragment2.setArguments(bundle);
                fragmentManager= getFragmentManager() ;
                fragmentManager.beginTransaction()
                        .replace(R.id.frame2, fragment2).commit();
                break ;
            case 3 :
                fragment3 = new ListeLivresFragement();
                fragment3.setArguments(bundle);
                fragmentManager= getFragmentManager() ;
                fragmentManager.beginTransaction()
                        .replace(R.id.frame3, fragment3).commit();
                break ;
            case 4 :
                fragment4 = new ListeLivresFragement();
                fragment4.setArguments(bundle);
                fragmentManager= getFragmentManager() ;
                fragmentManager.beginTransaction()
                        .replace(R.id.frame4, fragment4).commit();
                break ;
            case 5 :
                fragment5 = new ListeLivresFragement();
                fragment5.setArguments(bundle);
                fragmentManager= getFragmentManager() ;
                fragmentManager.beginTransaction()
                        .replace(R.id.frame5, fragment5).commit();
                break ;
        }

    }*/


}
