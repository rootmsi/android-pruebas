package com.example.git;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URI;

public class activity_5 extends AppCompatActivity {
 ImageView llamada,web;
 TextView info016, guardiaCivil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_5);

        llamada=findViewById(R.id.imageView016);
        web=findViewById(R.id.imageViewGuardiaCivil);
        
        guardiaCivil=findViewById(R.id.textViewGC);
        info016=findViewById(R.id.textView016);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_superior, menu);
        return true;
    }

    public  boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        if (id == R.id.item_cues) {
            intent = new Intent(this, activity_6.class);
            startActivity(intent);
        }

        if(id == R.id.item_acerca){
            intent = new Intent(this, activity_8.class);
            startActivity(intent);
        }


        return super.onOptionsItemSelected(item);
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
