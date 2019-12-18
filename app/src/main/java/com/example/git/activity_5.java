package com.example.git;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.net.URI;

public class activity_5 extends AppCompatActivity {
 Button llamada, web;



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
    public  void  web(View view){
        String direccion="http://www.guardiacivil.es/es/servicios/violenciadegeneroabusodemenores/abusosexualmenores/index.html";
        Uri webpage = Uri.parse(direccion);
        Intent web= new Intent(Intent.ACTION_VIEW,webpage);
        startActivity(web);
    }
}
