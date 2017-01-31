package com.ylanarchi.curso3semana2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ConfirmarDatos extends AppCompatActivity {

    private String nombre;
    private Date fecha;
    private String mail;
    private String telefono;
    private String descripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmar_datos);
        Bundle parametros = getIntent().getExtras();
        nombre = parametros.getString("nombre");
        mail = parametros.getString("mail");
        telefono = parametros.getString("telefono");
        descripcion = parametros.getString("descripcion");
        DateFormat DF = new SimpleDateFormat("dd/MM/yyyy");
        try {
            fecha = DF.parse(parametros.getString("fecha"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        ((TextView) findViewById(R.id.editTextUserName)).setText(nombre);
        ((TextView) findViewById(R.id.editTextDate)).setText("Fecha Nacimiento: " + DF.format(fecha));
        ((TextView) findViewById(R.id.editTextMail)).setText("Email: " + mail);
        ((TextView) findViewById(R.id.editTextPhone)).setText("Tel. " + telefono);
        ((TextView) findViewById(R.id.editTextDescription)).setText("Descripción: " + descripcion);

        Button buttonEditarDatos = (Button) findViewById(R.id.buttonEditar);
        buttonEditarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConfirmarDatos.this, MainActivity.class);
                intent.putExtra("nombre", nombre);
                intent.putExtra("mail", mail);
                intent.putExtra("telefono", telefono);
                intent.putExtra("descripcion", descripcion);
                DateFormat DF = new SimpleDateFormat("dd/MM/yyyy");
                intent.putExtra("fecha", DF.format(fecha));
                startActivity(intent);
            }
        });
    }
}
