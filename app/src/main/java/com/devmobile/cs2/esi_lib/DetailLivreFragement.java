package com.devmobile.cs2.esi_lib;

import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.util.Date;

public class DetailLivreFragement extends Fragment {

    private byte[] image;
    private String titre;
    private String auteur;
    private String categorie;
    private String annee;
    private String description;
    ImageView iconLivre;
    TextView titreLivre;
    TextView auteurLivre;
    TextView categorieLivre;
    TextView anneLivre;
    TextView descriptionLivre;
    EditText commentaire;
    private LinearLayout listCommentaire, infoLivre;
    ScrollView scrollView;
    ImageButton ajouterCommentaire;

    public DetailLivreFragement() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail_livre_fragement, container, false);

        /// Recuperation du data passé par le précedent fragment
        if (getArguments() != null) {
            image = getArguments().getByteArray("image");
            titre = getArguments().getString("titre");
            auteur = getArguments().getString("auteur");
            categorie = getArguments().getString("categorie");
            annee = getArguments().getString("annee");
            description = getArguments().getString("description");
            commentaire = (EditText) v.findViewById(R.id.commentaire);
            scrollView = (ScrollView) v.findViewById(R.id.scrollView);
            /// Affichage du data sur l'interface
            iconLivre = (ImageView) v.findViewById(R.id.iconLivreDetail);
            titreLivre = (TextView) v.findViewById(R.id.titreDetail);
            auteurLivre = (TextView) v.findViewById(R.id.auteurDetail);
            categorieLivre = (TextView) v.findViewById(R.id.categorieDetail);
            anneLivre = (TextView) v.findViewById(R.id.anneeDetail);
            descriptionLivre = (TextView) v.findViewById(R.id.description);
            ajouterCommentaire = (ImageButton) v.findViewById(R.id.ajouterCommentaire);
            infoLivre = (LinearLayout) v.findViewById(R.id.infoLivre);

            ByteArrayInputStream imageStream = new ByteArrayInputStream(image);
            Bitmap imageBitmap = BitmapFactory.decodeStream(imageStream);
            iconLivre.setImageBitmap(imageBitmap);

            Typeface type = Typeface.createFromAsset(v.getContext().getAssets(),
                    "fonts/roboto_light.ttf");
            titreLivre.setTypeface(type);
            overrideFonts(infoLivre, type);
            titreLivre.setText(titre);
            auteurLivre.setText("Auteur  " + auteur);
            auteurLivre.setTypeface(type);
            categorieLivre.setText(categorie);
            anneLivre.setText(annee);
            descriptionLivre.setText(description);

            listCommentaire = (LinearLayout) v.findViewById(R.id.listCommentaire);

            ajouterCommentaire.setOnClickListener(new View.OnClickListener() {

                Typeface type = Typeface.createFromAsset(getActivity().getAssets(),
                        "fonts/roboto_light.ttf");
                Typeface type2 = Typeface.createFromAsset(getActivity().getAssets(),
                        "fonts/neuropol.ttf");

                @Override
                public void onClick(View v) {

                    String commentToAdd = commentaire.getText().toString();
                    if (commentToAdd.equals("")) {
                        Toast.makeText(getActivity().getBaseContext(), "Aucun commentaire à ajouter", Toast.LENGTH_SHORT);
                    } else {
                        TextView tx, date, user;

                        View view = v.inflate(v.getContext(), R.layout.commentaire, null);

                        tx = (TextView) view.findViewById(R.id.description);
                        date = (TextView) view.findViewById(R.id.date);
                        user = (TextView) view.findViewById(R.id.userName);
                        tx.setTypeface(type);
                        date.setTypeface(type);
                        user.setTypeface(type2);
                        user.setText("iheb chorfi");
                        tx.setText(commentToAdd);
                        date.setText(DateFormat.getDateInstance().format(new Date()));

                        listCommentaire.addView(view);

                        commentaire.setText("");
                        commentaire.setHint("Entrer un commentaire ");

                        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(commentaire.getWindowToken(), 0);

                        scrollView.post(new Runnable() {
                            public void run() {
                                scrollView.fullScroll(View.FOCUS_DOWN);
                            }
                        });
                    }
                }

            });
        }
        return v;
    }

    private void overrideFonts(final View v, Typeface tf) {
        try {
            if (v instanceof ViewGroup) {
                ViewGroup vg = (ViewGroup) v;
                for (int i = 0; i < vg.getChildCount(); i++) {
                    View child = vg.getChildAt(i);
                    overrideFonts(child, tf);
                }
            } else if (v instanceof TextView) {
                ((TextView) v).setTypeface(tf);
            }
        } catch (Exception e) {
        }
    }
}