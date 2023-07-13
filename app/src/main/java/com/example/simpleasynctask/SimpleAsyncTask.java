package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Void, String> {

    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    SimpleAsyncTask(TextView tv, ProgressBar pg) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(pg);
    }


    @Override
    protected String doInBackground(Void... voids) {

        // Génère un nombre aléatoire entre 0 et 10
        Random r = new Random();
        int n = r.nextInt(11);

        // Faites en sorte que la tâche prenne suffisamment de temps pour que nous ayons
        // le temps de faire pivoter le téléphone pendant qu'il est en cours d'exécution
        int s = n * 200;

        // Sommeil pendant un laps de temps aléatoire
        for (int i = 0; i <= 10; i++) {
        try {
            Thread.sleep(s/10);
            mProgressBar.get().setProgress(10*i);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }

        // Retourne un résultat String
        return "Enfin réveillé après avoir dormi pendant " + s + " millisecondes !";
    }

    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
    }
}