package com.example.git;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;

public class activity_5 extends AppCompatActivity {
 ImageView llamada,web;
 TextView ya,info016, guardiaCivil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);
        llamada=findViewById(R.id.imageView016);
        web=findViewById(R.id.imageViewGuardiaCivil);
        ya=findViewById(R.id.textViewYA);
        guardiaCivil=findViewById(R.id.textViewGC);
        info016=findViewById(R.id.textView016);
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
