package com.example.git;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class activity_6 extends AppCompatActivity implements View.OnClickListener {

    Button enviar;

    RadioButton respuesta11, respuesta12, respuesta21, respuesta22, respuesta31, respuesta32;

    Boolean conexionCorrecta=true;  //Terminar esto

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_6);

        respuesta11=findViewById(R.id.rbPreg1Si);
        respuesta12=findViewById(R.id.rbPreg1No);
        respuesta21=findViewById(R.id.rbPreg2Si);
        respuesta22=findViewById(R.id.rbPreg2No);
        respuesta31=findViewById(R.id.rbPreg3Si);
        respuesta32=findViewById(R.id.rbPreg3No);

        enviar=findViewById(R.id.btnEnviar);

        enviar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        ResultSet resultadoConsulta = obtenerResultset();

        actualizarBaseDeDatos(resultadoConsulta);

        if (conexionCorrecta) {
            cambiarActivity();
        } else {
            Toast.makeText(this, "Error al subir a la base de datos!", Toast.LENGTH_SHORT).show();
        }
    }

    private ResultSet obtenerResultset() {
        try {
            return obtenerStatement().executeQuery("SELECT respuesta1, respuesta2 FROM cuestionario;");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void actualizarBaseDeDatos(ResultSet resultadoConsulta) {
        try {

            RadioButton primeraRespuesta, segundaRespuesta;

            for (int i=1; i<=3; i++) {
                resultadoConsulta.absolute(i);

                if (i==1) {
                    primeraRespuesta=respuesta11;
                    segundaRespuesta=respuesta12;
                } else if (i==2) {
                    primeraRespuesta=respuesta21;
                    segundaRespuesta=respuesta22;
                } else {
                    primeraRespuesta=respuesta31;
                    segundaRespuesta=respuesta32;
                }

                if (primeraRespuesta.isChecked()) {
                    obtenerStatement().executeUpdate("UPDATE cuestionario SET respuesta1=\""+(resultadoConsulta.getInt(2) + 1)+"\" WHERE id=\""+i+"\";");
                    continue;
                } else if (segundaRespuesta.isChecked()) {
                    obtenerStatement().executeUpdate("UPDATE cuestionario SET respuesta2=\""+(resultadoConsulta.getInt(3) + 1)+"\" WHERE id=\""+i+"\";");
                    continue;
                } else {
                    Toast.makeText(this, "respuesta "+i+" sin contestar", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (SQLException e) {
            Toast.makeText(this, "Error comprobar las preguntas elegidas!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private Statement obtenerStatement() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://rogdomain.ddns.net:5432", "generico", "generico").createStatement();
        } catch (SQLException e) {
            return null;
        }
    }

    private void cambiarActivity() {
        Intent intent=new Intent(this, activity_7.class);
        startActivity(intent);
    }

}
