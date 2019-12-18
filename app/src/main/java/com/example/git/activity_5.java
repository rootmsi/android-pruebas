package com.example.git;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_5 extends AppCompatActivity {
 Button llamada;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
    }
    public  void llamada(View view){
        Intent llamada= new Intent(Intent.ACTION_DIAL);
        llamada.setData(Uri.parse("tel:016"));
        startActivity(llamada);

    }
}
