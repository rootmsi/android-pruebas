package com.example.git;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class activity_7 extends AppCompatActivity implements View.OnClickListener {

    EditText comentario;
    Button boton;

    Boolean conexionCorrecta=false;

    Connection conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);

        boton = findViewById(R.id.btnComentario);

        comentario = findViewById(R.id.etComentario); //Aquí va el id del editText de comentario que aun no habeis creado.

        //oton.setOnClickListener(this);

        try {


            Toast.makeText(this, "Entre guei", Toast.LENGTH_LONG).show();
            Class.forName("org.postgresql.Driver");

            Toast.makeText(this, "Driver OK", Toast.LENGTH_LONG).show();

        } catch (ClassNotFoundException e) {
            //e.printStackTrace();

            Toast.makeText(this, "Driver not found", Toast.LENGTH_LONG).show();

        }

        String url;

        url = "jdbc:postgresql://192.168.1.243:5432/hacialaigualdad";     //sslfactory=org.postgresql.ssl.NonValidatingFactory&ssl=true;

        try {

            conexion = DriverManager.getConnection(url, "generico", "generico");

            Toast.makeText(this, "Conexion creada", Toast.LENGTH_LONG).show();

        } catch (SQLException e) {
            e.printStackTrace();

            Toast.makeText(this, "F en el chat", Toast.LENGTH_LONG).show();
        }



    }

    @Override
    public void onClick(View v) {



    }
}
