package com.example.simpleasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * L'application SimpleAsyncTask contient un bouton qui lance une AsyncTask
 * qui dort dans le thread asynchrone pendant une durée aléatoire.
 */
public class MainActivity extends AppCompatActivity {

    // Clé de sauvegarde de l'état du TextView
    private static final String TEXT_STATE = "currentText";

    // Le TextView où nous afficherons les résultats
    private TextView mTextView;

    private ProgressBar mProgressBar;

    // Initialise l'activité.
    // @param saveInstanceState -> Les données d'état actuelles
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialise mTextView
        mTextView = findViewById(R.id.textView1);
        mProgressBar = findViewById(R.id.progressBar);

        // Restaure TextView s'il y a un saveInstanceState
        if(savedInstanceState!=null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    /**
     * Gère le onCLick pour le bouton "Démarrer la tâche".
     * Lance l'AsyncTask qui effectue un travail hors du thread UI.
     *
     * @param view La vue (bouton) qui a été cliquée.
     */
    public void startTask(View view) {
        // Placer un message dans la vue texte
        mTextView.setText(R.string.napping);

        // Démarre l'AsyncTask.
        // L'AsyncTask a un rappel qui mettra à jour l'affichage du texte.
        new SimpleAsyncTask(mTextView, mProgressBar).execute();
    }

    /**
     * Enregistre le contenu de TextView à restaurer lors d'un changement de configuration.
     * @param outState -> Le bundle dans lequel l'état de l'activité est enregistré
     * lorsqu'elle est détruite spontanément.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Enregistre l'état de TextView
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }
}