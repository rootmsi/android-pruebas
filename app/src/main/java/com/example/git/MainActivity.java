package com.example.git;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = findViewById(R.id.BotonSiguiente);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);
    }

    public void continuar(View view){
        Intent intent1 = new Intent(this, activity_2.class);
        startActivity(intent1);
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

}
