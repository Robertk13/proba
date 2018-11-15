package com.irena.irena.restapi;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class WebServiceHandler extends AsyncTask<String, Void, String> {
        TextView ttt;

        public WebServiceHandler(TextView tv){
            ttt = tv;
        }

    // metoda wykonywana jest zaraz przed główną operacją (doInBackground())
    // mamy w niej dostęp do elementów UI
    @Override
    protected void onPreExecute() {
        // wyświetlamy okienko dialogowe każące czekać

    }

    // główna operacja, która wykona się w osobnym wątku
    // nie ma w niej dostępu do elementów UI
    @Override
    protected String doInBackground(String... urls) {

        try {
            // zakładamy, że jest tylko jeden URL
            URL url = new URL(urls[0]);
            URLConnection connection = url.openConnection();

            // pobranie danych do InputStream
            InputStream in = new BufferedInputStream(
                    connection.getInputStream());

            // konwersja InputStream na String
            // wynik będzie przekazany do metody onPostExecute()
            return streamToString(in);

        } catch (Exception e) {
            // obsłuż wyjątek
            Log.d(MainActivity.class.getSimpleName(), e.toString());
            return null;
        }

    }

    // metoda wykonuje się po zakończeniu metody głównej,
    // której wynik będzie przekazany;
    // w tej metodzie mamy dostęp do UI
    @Override
    protected void onPostExecute(String result) {

        // chowamy okno dialogowe


        try {
            // reprezentacja obiektu JSON w Javie
            JSONObject json = new JSONObject(result);
            String dd = json.optString("rates").toString();
            ttt.setText(dd);
            // pobranie pól obiektu JSON i wyświetlenie ich na ekranie
         //   ((TextView) findViewById(R.id.rest)).setText("id: "
        //            + json.optString("id"));
        //    ((TextView) findViewById(R.id.rest)).setText("name: "
         //           + json.optString("name"));

        } catch (Exception e) {
            // obsłuż wyjątek
            Log.d(MainActivity.class.getSimpleName(), e.toString());
        }
    }


    // konwersja z InputStream do String
    public static String streamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        try {

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }


            reader.close();

        } catch (IOException e) {
            // obsłuż wyjątek
            Log.d(MainActivity.class.getSimpleName(), e.toString());
        }

        return stringBuilder.toString();
    }

}
