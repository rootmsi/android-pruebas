package com.example.git;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class activity_4 extends AppCompatActivity {

    EditText texto;
    Button boton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_4);

        texto=findViewById(R.id.editTextDenuncia);
        boton=findViewById(R.id.buttonSMS);

    }
}
