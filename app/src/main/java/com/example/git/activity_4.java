package com.example.git;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_4 extends AppCompatActivity implements View.OnClickListener {

    EditText texto;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        texto=findViewById(R.id.editTextDenuncia);
        boton=findViewById(R.id.buttonSMS);

        boton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("Se enviará un SMS con coste 1,20€");
        builder.setMessage("¿Quieres continuar?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String numero="28099";
                String mensaje="IGUALDAD";
                try {
                    SmsManager smgr = SmsManager.getDefault();
                    smgr.sendTextMessage(numero, null, mensaje, null, null);
                    Toast.makeText(activity_4.this, "Enviado correctamente", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(activity_4.this, "No se ha podiod enviar", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }


        //        Toast.makeText( "Seguimos", Toast.LENGTH_LONG).show();
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(activity_4.this, "Denegado", Toast.LENGTH_LONG).show();
            }
        });

        Dialog dialog = builder.create();
        dialog.show();


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_superior, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
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
