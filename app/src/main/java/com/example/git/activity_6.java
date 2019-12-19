package com.example.git;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class activity_6 extends AppCompatActivity implements View.OnClickListener {

    Button boton;

    RadioButton respuesta11, respuesta12, respuesta21, respuesta22, respuesta31, respuesta32;

    EditText comentario;    //Hacer que el contenido del editText ejecute un INSERT en la columna comentario de la tabla comentarios del servidor.

    Boolean conexionCorrecta=true;

    Statement conexion;

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

        boton=findViewById(R.id.btnEnviar);

        boton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        //Si el botón tiene como texto "ENVIAR" crea la conexión y hace los SELECT y UPDATE. Si no lo es cambia de activity (esto se debe a que si ya has enviado tus datos el texto del botón cambia a "VER COMENTARIOS")
        if (boton.getText().equals("ENVIAR")) {

            //Si todas las respuestas están respondidas se ejecuta este if.
            if ((respuesta11.isChecked() || respuesta12.isChecked()) && (respuesta21.isChecked() || respuesta22.isChecked()) && (respuesta31.isChecked() || respuesta32.isChecked())) {

                //Se conecta al servidor.
                obtenerStatement();

                //Si ocurrió un error al conectarse al servidor se ejecuta el "else". Si funcionó la conexion al servidor continúa el "if".
                if (conexionCorrecta) {

                    //Ejecuta el SELECT. Si ocurre una excepción "resultadoConsulta" será "NULL".
                    ResultSet resultadoConsulta = obtenerResultsetConsulta("SELECT respuesta1, respuesta2 FROM cuestionario;");

                    //Nos aseguramos de que no ocurrieran errores al recuperar los datos de la base de datos.
                    if (resultadoConsulta != null) {
                        actualizarBaseDeDatos(resultadoConsulta);   //Ejecutamos el UPDATE.
                    }

                    //Si el SELECT y el UPDATE se ejecutaron correctamente "conexionCorrecta" será true y se mostrarán los resultados. En caso contrario imprime una alerta y no pasa al nuevo activity.
                    if (conexionCorrecta && resultadoConsulta != null) {

                        mostrarResultados(resultadoConsulta);   //Mostrar resultados.

                    } else {
                        errorAlConectarAlServidor();    //Imprime alerta de que falló algo durante la conexión (SELECT o UPDATE).
                    }
                } else {
                    errorAlConectarAlServidor();    //Imprime alerta de que falló algo durante la conexión (STATEMENT).
                }

            } else {
                //Esto se ejecuta cunado no se han respondido todas las preguntas.
                Toast.makeText(this, "Por favor, responda todas las preguntas antes de continuar", Toast.LENGTH_SHORT).show();
            }

        } else {
            //Esto se ejecuta cuando el texto del botón en "VER COMENTARIOS" y no "ENVIAR" (es decir, cuando ya se han enviado los datos del formulario al servidor).
            cambiarActivity();
        }
    }

    private void mostrarResultados(ResultSet resultadoConsulta) {
        try {

            RadioButton primeraRespuesta, segundaRespuesta;

            boton.setText("VER COMENTARIOS");

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
                    primeraRespuesta.setText(resultadoConsulta.getInt(1));  //Index 1 porque la consulta SELECT no recupera el ID. Solo dos columnas en total
                    //continue;
                } else if (segundaRespuesta.isChecked()) {
                    segundaRespuesta.setText(resultadoConsulta.getInt(2));  //Index 2 porque la consulta SELECT no recupera el ID. Solo dos columnas en total
                    //continue;
                }
            }

        } catch (SQLException e) {
            //Convertir en en una alerta:
            Toast.makeText(this, "Se han cargado sus datos al servidor, pero ha ocurrido un error al mostrarlos en su dispositivo", Toast.LENGTH_SHORT).show();
            //e.printStackTrace();
        }
    }

    private void errorAlConectarAlServidor() {
        //Imprimir alert con error de conexion al servidor.
        Toast.makeText(this, "Ha ocurrido un error al conectar con el servidor. Inténtelo de nuevo más tarde", Toast.LENGTH_SHORT).show();
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
                    conexion.executeUpdate("UPDATE cuestionario SET respuesta1=\""+(resultadoConsulta.getInt(1) + 1)+"\" WHERE id=\""+i+"\";");
                    //continue;
                } else if (segundaRespuesta.isChecked()) {
                    conexion.executeUpdate("UPDATE cuestionario SET respuesta2=\""+(resultadoConsulta.getInt(2) + 1)+"\" WHERE id=\""+i+"\";");
                    //continue;
                }
            }

        } catch (SQLException e) {
            conexionCorrecta=false;
            //e.printStackTrace();
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

    private void cambiarActivity() {
        Intent intent=new Intent(this, activity_7.class);
        startActivity(intent);
    }

}
