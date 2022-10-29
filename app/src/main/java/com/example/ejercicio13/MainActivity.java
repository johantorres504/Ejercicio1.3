package com.example.ejercicio13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import com.example.ejercicio13.Configuracion.SQLiteConexion;
import com.example.ejercicio13.Tablas.Transacciones;

public class MainActivity extends AppCompatActivity {
    Button add;
    EditText nombre, apellido,correo;
    EditText edad, direccion;

    private void config() {
        add = (Button) findViewById(R.id.add);
        nombre = (EditText) findViewById(R.id.nombre);
        apellido = (EditText) findViewById(R.id.apellido);
        edad = (EditText) findViewById(R.id.edad);
        correo = (EditText) findViewById(R.id.correo);
        direccion = (EditText) findViewById(R.id.direccion);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        config();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgregarPersona();
                Intent intent = new Intent(getApplicationContext(), ActivityLista.class);
                startActivity(intent);
            }
        });
    }

    private void AgregarPersona() {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombre, nombre.getText().toString());
        valores.put(Transacciones.apellido, apellido.getText().toString());
        valores.put(Transacciones.edad, edad.getText().toString());
        valores.put(Transacciones.correo, correo.getText().toString());
        valores.put(Transacciones.direccion, direccion.getText().toString());

        Long resultado  = db.insert(Transacciones.TbPersonas, Transacciones.id, valores);

        Toast.makeText(getApplicationContext(), "Registro Ingresado " + resultado.toString()
                , Toast.LENGTH_SHORT).show();

        db.close();

        ClearScreen();

    }

    private void ClearScreen() {
        nombre.setText("");
        apellido.setText("");
        edad.setText("");
        correo.setText("");
        direccion.setText("");
    }

}