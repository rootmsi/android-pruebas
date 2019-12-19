package com.example.git;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class activity_7 extends AppCompatActivity implements View.OnClickListener {

    EditText comentario;
    Button boton;

    Boolean conexionCorrecta=false;

    Statement conexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_7);

        boton = findViewById(R.id.btnComentario);
        boton.setOnClickListener(this);
        comentario=findViewById(R.id.etComentario); //Aquí va el id del editText de comentario que aun no habeis creado.


        //Conectar a la base de datos.
        obtenerStatement();

        //Si la conexion fue bien hacer la consulta
        if (conexionCorrecta) {
            obtenerResultsetConsulta("SELECT comentario FROM comentarios;");
        }

        //Si el comentario no está vacío insertarlo
        if (!comentario.getText().toString().equals("")) {
            insertarComentario();
        }

        //Cerrar la conexion.
        cerrarConexion();
    }

    //Ejecutar el select
    private ResultSet obtenerResultsetConsulta(String consulta) {
        try {
            return conexion.executeQuery(consulta);
        } catch (SQLException e) {
            conexionCorrecta=false;
            //e.printStackTrace();
            return null;
        }
    }

    //Conexión a la base de datos.
    private void obtenerStatement() {
        int contadorIntentosDeConexion=0;

        //Si no se consigue conectar vuelve a intentar hasta 10 veces.
        do{
            try {
                conexion = DriverManager.getConnection("jdbc:postgresql://rogdomain.ddns.net:5432/hacialaigualdad", "generico", "generico").createStatement();
            } catch (SQLException e) {
                conexion = null;
            }
            contadorIntentosDeConexion++;
        } while (conexion==null && contadorIntentosDeConexion<10);

        //Si no se ha podido conectar pone "false" en la variable "conexionCorrecta".
        if (conexion==null) {
            conexionCorrecta=false;
        }
    }


    //Insertar el comentario
    private void insertarComentario() { //Te he puesto lo mismo onClick del boton, sino no me dejaba ponerle el evento, cambialo si quieres
        String insertar="INSERT INTO comentarios (comentario) VALUES ("+comentario.getText().toString()+");";



        //No está terminado.



    }


    //Cerrar la conexion a la base de datos.
    private void cerrarConexion() {
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        String insertar="INSERT INTO comentarios (comentario) VALUES ("+comentario.getText().toString()+");";



        //No está terminado.
    }
}
