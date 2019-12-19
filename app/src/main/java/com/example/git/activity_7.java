package com.example.git;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class activity_7 extends AppCompatActivity {

    Boolean conexionCorrecta=false;

    Statement conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);

        obtenerStatement();

        if (conexionCorrecta) {
            obtenerResultsetConsulta("SELECT comentario FROM comentarios;");
        }


    }

    private ResultSet obtenerResultsetConsulta(String consulta) {
        try {
            return conexion.executeQuery(consulta);
        } catch (SQLException e) {
            conexionCorrecta=false;
            //e.printStackTrace();
            return null;
        }
    }

    private void obtenerStatement() {
        int contadorIntentosDeConexion=0;

        do{
            try {
                conexion = DriverManager.getConnection("jdbc:postgresql://rogdomain.ddns.net:5432/hacialaigualdad", "generico", "generico").createStatement();
            } catch (SQLException e) {
                conexion = null;
            }
            contadorIntentosDeConexion++;
        } while (conexion==null && contadorIntentosDeConexion<10);

        if (conexion==null) {
            conexionCorrecta=false;
        }
    }
}
