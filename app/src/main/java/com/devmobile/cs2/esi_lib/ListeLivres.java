package com.devmobile.cs2.esi_lib;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.SearchManager;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.devmobile.cs2.esi_lib.Adapters.LivreAdapter;
import com.devmobile.cs2.esi_lib.Adapters.MenuItemAdapter;
import com.devmobile.cs2.esi_lib.AsyncTasks.GetLivresByQueryTask;
import com.devmobile.cs2.esi_lib.AsyncTasks.LogOutTask;
import com.devmobile.cs2.esi_lib.DataBasesHandlers.DataBaseSQLiteHandler;
import com.devmobile.cs2.esi_lib.Models.Livre;
import com.devmobile.cs2.esi_lib.Models.NavMenuItem;

import java.util.ArrayList;
import java.util.Stack;


public class ListeLivres extends ActionBarActivity implements SearchView.OnQueryTextListener {




    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    // private ActionBarDrawerToggle mDrawerToggle;
    // nav drawer title
    private CharSequence mDrawerTitle;

    // used to store app title
    private CharSequence mTitle;

    // slide menu items
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<NavMenuItem> navDrawerItems;
    private MenuItemAdapter adapter;
    public static boolean isPhone = false;
    public static boolean isLand = false;
    public static Fragment fragmentListeLivres;
    public static Fragment fragmentDetailLivre;
    FragmentManager fragmentManager = getFragmentManager();

    private SearchView searchView;
    private ArrayList<Livre> livre_affiche ;
    private ListView listView ;
    public static Stack<Fragment> pileFragment ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        init_data();



        pileFragment =new Stack<Fragment>() ;
        super.onCreate(savedInstanceState);
        isPhone(this);
        isLand();
        if (fragmentListeLivres != null && !isPhone) {
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragmentListeLivres).commit();
        }

        setContentView(R.layout.activity_liste_livres);



        mTitle = mDrawerTitle = getTitle();

        // load slide menu items
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        navMenuIcons = getResources()
                .obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<>();
        // adding nav drawer items to array
        // Catégorie
        navDrawerItems.add(new NavMenuItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1), "10"));
        // Math
        navDrawerItems.add(new NavMenuItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1), "10"));
        // Elec
        navDrawerItems.add(new NavMenuItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1), "10"));
        // algo
        navDrawerItems.add(new NavMenuItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), "10"));
        // base
        navDrawerItems.add(new NavMenuItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1), "10"));
        // si
        navDrawerItems.add(new NavMenuItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), "10"));
        //
        navDrawerItems.add(new NavMenuItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1), "10"));


        // Recycle the typed array
        navMenuIcons.recycle();
        // setting the nav drawer list adapter
        adapter = new MenuItemAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        // enabling action bar app icon and behaving it as toggle button
        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            getSupportActionBar().setHomeButtonEnabled(true);
            //getActionBar().setDisplayHomeAsUpEnabled(true);
            // getActionBar().setHomeButtonEnabled(true);
        } catch (NullPointerException e) {
            Toast.makeText(this, "null", Toast.LENGTH_LONG).show();
        }


        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer, //nav menu toggle icon
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ) {
            public void onDrawerClosed(View view) {
                try {
                    getSupportActionBar().setTitle(mTitle);
                    //getActionBar().setTitle(mTitle);
                } catch (NullPointerException e) {

                }

                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                try {
                    getSupportActionBar().setTitle(mDrawerTitle);
                    // getActionBar().setTitle(mDrawerTitle);
                } catch (NullPointerException e) {

                }

                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            // on first time display view for first nav item
            try {
                displayView(0);
            } catch (NullPointerException e) {

            }

        }

        // gérer les évenements
        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

//      searchView = (SearchView) monMenu.findItem(R.id.action_search) ;
/*        if(searchView==null){
            Log.e("null","search null") ;
        }
        else{
            Log.e("pas ","search pas null") ;
        }*/
       //searchView.setOnQueryTextListener(this);
    }

    public ArrayList<Livre> filtredListBooks(String query){
        ArrayList<Livre> filtredList = new ArrayList<Livre>();
        livre_affiche = ListeLivresFragement.livre_affiche ;
        if(livre_affiche==null){
            Log.e("null","livreaffiche null") ;
        }
        for (int i=0;i<livre_affiche.size();i++){
            if (livre_affiche.get(i).rechercheMotsClés(query)) filtredList.add(livre_affiche.get(i));
        }
        return filtredList;
    }
    @Override
    public boolean onQueryTextSubmit(String query) {

        ArrayList<Livre> list = filtredListBooks(query);
        if(list.isEmpty()){
            new GetLivresByQueryTask(this,query).execute() ;
        }
        LivreAdapter monAdapteteur = new LivreAdapter(this,R.layout.liste_livres_range,list);
        listView = ListeLivresFragement.listLivreView ;
        listView.setAdapter(monAdapteteur);
       // listView.setOnItemClickListener(this);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        ArrayList<Livre> list = filtredListBooks(newText);
        LivreAdapter monAdapteteur = new LivreAdapter(this,R.layout.liste_livres_range,list);
        listView = ListeLivresFragement.listLivreView ;
        listView.setAdapter(monAdapteteur);
        return false;
    }

    /**
     * Slide menu item click listener
     */
    private class SlideMenuClickListener implements
            ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {
            // display view for selected nav drawer item
            displayView(position);
        }
    }

    /**
     * Diplaying fragment view for selected nav drawer list item
     */
    private void displayView(int position) {
        // update the main content by replacing fragments
        fragmentListeLivres = null;
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
                bundle.putString("categ", "0");
                fragmentListeLivres = new ListeLivresFragement();
                fragmentListeLivres.setArguments(bundle);

                //fragmentListeLivres = new AccueilFragement();
                break;
            case 1:
                bundle.putString("categ", "1");
                fragmentListeLivres = new ListeLivresFragement();
                fragmentListeLivres.setArguments(bundle);
                break;
            case 2:
                bundle.putString("categ", "2");
                fragmentListeLivres = new ListeLivresFragement();
                fragmentListeLivres.setArguments(bundle);
                break;
            case 3:
                bundle.putString("categ", "3");
                fragmentListeLivres = new ListeLivresFragement();
                fragmentListeLivres.setArguments(bundle);
                break;
            case 4:
                bundle.putString("categ", "4");
                fragmentListeLivres = new ListeLivresFragement();
                fragmentListeLivres.setArguments(bundle);
                break;
            case 5:
                bundle.putString("categ", "5");
                fragmentListeLivres = new ListeLivresFragement();
                fragmentListeLivres.setArguments(bundle);
                break;
            case 6:
              new LogOutTask(this).execute() ;
                break;

            default:
                break;
        }

        if (fragmentListeLivres != null) {
            // FragmentManager fragmentManager = getFragmentManager() ;
            //  FragmentManager fragmentManager = getFragmentManager();
            //  FragmentManager fragmentManager = getFragmentManager();
            livre_affiche = ListeLivresFragement.livre_affiche ;
          listView = ListeLivresFragement.listLivreView ;
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragmentListeLivres).commit();

            // update selected item and title, then close the drawer
            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            try {
                setTitle(navMenuTitles[position]);
            } catch (NullPointerException e) {

            }

            // if phone
            if (isPhone) {
                mDrawerLayout.closeDrawer(mDrawerList);
            }

        } else {
            // error in creating fragment
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_liste_livres, menu);
        SearchManager searchManager = (SearchManager) getSystemService( Context.SEARCH_SERVICE );
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(this);
        searchView.setQueryHint("    Titre, Tages");

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (isPhone) {
            if (mDrawerToggle.onOptionsItemSelected(item)) {
                return true;
            }
        }

        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Called when invalidateOptionsMenu() is triggered
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
/* phone        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen); */
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        try {
            getSupportActionBar().setTitle(mTitle);
            // getActionBar().setTitle(mTitle);
        } catch (NullPointerException e) {

        }

    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    public void isPhone(Context context) {
        isPhone = (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                < Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public void isLand() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            isLand = true;
        } else {
            isLand = false;
        }
    }

    @Override
    public void onBackPressed() {
        if ( !pileFragment.isEmpty() && pileFragment.pop()!=null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragmentListeLivres).commit();

        } else {
            super.onBackPressed();
        }
    }
    public  void init_data(){
        DataBaseSQLiteHandler dbh=new DataBaseSQLiteHandler(this) ;
        ListeLivresFragement.livre_math =dbh.getLivresByCateg("Mathématique") ;
        ListeLivresFragement.livre_elec =dbh.getLivresByCateg("Electronique") ;
        ListeLivresFragement.livre_algo =dbh.getLivresByCateg("Algorithmique") ;
        ListeLivresFragement.livre_base =dbh.getLivresByCateg("Base de donnée") ;
        ListeLivresFragement.livre_si =dbh.getLivresByCateg("Système d'information") ;
    }

}
