package com.irena.irena.restapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
   // ProgressBar pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  pb = (ProgressBar) findViewById(R.id.progresBar);
      //  pb.setVisibility(View.INVISIBLE);
        final TextView tv = findViewById(R.id.rest);

      Button bt = (Button) findViewById(R.id.start_button);
      bt.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

            //  pb.setVisibility(View.VISIBLE);
              new WebServiceHandler(tv)
                      .execute("http://data.fixer.io/api/latest?access_key=154a08fd9e7b04f7c3023d629a935895&symbols=USD,PLN,GBP,CDF,CUC");
          }
      });
    //  pb.setVisibility(View.INVISIBLE);

    }
}
