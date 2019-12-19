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

    Boolean conexionCorrecta=true;

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

        ResultSet resultadoConsulta = obtenerResultset("SELECT respuesta1, respuesta2 FROM cuestionario;");

        if (resultadoConsulta!=null) {
            if ((respuesta11.isChecked() || respuesta12.isChecked()) && (respuesta21.isChecked() || respuesta22.isChecked()) && (respuesta31.isChecked() || respuesta32.isChecked())) {
                actualizarBaseDeDatos(resultadoConsulta);
            } else {
                Toast.makeText(this, "Por favor, responda todas las preguntas antes de continuar", Toast.LENGTH_SHORT).show();
            }
        }

        if (conexionCorrecta) {
            cambiarActivity();
        } else {
            Toast.makeText(this, "Error al subir a la base de datos!", Toast.LENGTH_SHORT).show();
        }
    }

    private ResultSet obtenerResultset(String consulta) {
        try {
            return obtenerStatement().executeQuery(consulta);
        } catch (SQLException e) {
            conexionCorrecta=false;
            //e.printStackTrace();
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
                }
            }

        } catch (SQLException e) {
            conexionCorrecta=false;
            //e.printStackTrace();
        }
    }

    private Statement obtenerStatement() {
        try {
            return DriverManager.getConnection("jdbc:postgresql://rogdomain.ddns.net:5432", "generico", "generico").createStatement();
        } catch (SQLException e) {
            conexionCorrecta=false;
            return null;    //Cuando podamos hacer pruebas hay que comprobar que ocurre cuando este método retorna "null" (cuando no encuentre el servidor).
        }
    }

    private void cambiarActivity() {
        Intent intent=new Intent(this, activity_7.class);
        startActivity(intent);
    }

}
