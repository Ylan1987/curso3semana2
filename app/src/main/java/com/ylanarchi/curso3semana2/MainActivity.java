package com.ylanarchi.curso3semana2;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private String nombre;
    private Date fecha;
    private String mail;
    private String telefono;
    private String descripcion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle parametros = getIntent().getExtras();
        if (parametros!= null) {
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

            if (nombre.length() > 0) {
                ((TextInputEditText) findViewById(R.id.textInputUserName)).setText(nombre);
            }
            if (mail.length() > 0) {
                ((TextInputEditText) findViewById(R.id.textInputMail)).setText(mail);
            }
            if (telefono.length() > 0) {
                ((TextInputEditText) findViewById(R.id.textInputPhone)).setText(telefono);
            }
            if (descripcion.length() > 0) {
                ((TextInputEditText) findViewById(R.id.textInputDescription)).setText(descripcion);
            }
            DatePicker datePicker = ((DatePicker) findViewById(R.id.datePicker));
            Calendar cal = Calendar.getInstance();
            cal.setTime(fecha);
            datePicker.updateDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        }
        Button buttonSiguiente = (Button) findViewById(R.id.siguienteButton);
        buttonSiguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ConfirmarDatos.class);
                nombre = ((TextInputEditText) findViewById(R.id.textInputUserName)).getText().toString();
                mail = ((TextInputEditText) findViewById(R.id.textInputMail)).getText().toString();
                telefono = ((TextInputEditText) findViewById(R.id.textInputPhone)).getText().toString();
                descripcion = ((TextInputEditText) findViewById(R.id.textInputDescription)).getText().toString();
                Calendar date = Calendar.getInstance();
                date.set(Calendar.YEAR, ((DatePicker) findViewById(R.id.datePicker)).getYear());
                date.set(Calendar.MONTH, ((DatePicker) findViewById(R.id.datePicker)).getMonth());
                date.set(Calendar.DAY_OF_MONTH, ((DatePicker) findViewById(R.id.datePicker)).getDayOfMonth());
                fecha = date.getTime();
                DateFormat DF = new SimpleDateFormat("dd/MM/yyyy");
                intent.putExtra("nombre", nombre);
                intent.putExtra("mail", mail);
                intent.putExtra("telefono", telefono);
                intent.putExtra("descripcion", descripcion);
                intent.putExtra("fecha", DF.format(fecha));
                startActivity(intent);
            }
        });
    }
}
