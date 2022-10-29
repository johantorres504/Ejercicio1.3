package com.example.ejercicio13;

import static com.example.ejercicio13.R.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ejercicio13.Configuracion.SQLiteConexion;
import com.example.ejercicio13.Tablas.Personas;
import com.example.ejercicio13.Tablas.Transacciones;

import java.util.ArrayList;

public class ActivityLista extends AppCompatActivity {
    SQLiteConexion conexion;
    ListView listaP;
  //  AbsListView.RecyclerListener listaP;
    ArrayList<Personas> lista;
    ArrayList<String> listaconcatenada;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_lista);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);

        listaP = (ListView) findViewById(R.id.listaP);
       // listaP = (RecyclerView.RecyclerListener) findViewById(R.id.listaP);

        GetListPerson();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaconcatenada );
        listaP.setAdapter(adp);


        listaP.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                Toast.makeText(getApplicationContext(), listaconcatenada.get(i).toString(), Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), listaconcatenada.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void GetListPerson() {
        SQLiteDatabase db = conexion.getReadableDatabase(); // Base de datos en modo de lectura
        Personas listaP = null;

        lista = new ArrayList<Personas>();  // Lista de Objetos del tipo personas

        Cursor cursor = db.rawQuery(Transacciones.GetPersonas,null);

        while(cursor.moveToNext())
        {
            listaP = new Personas();
            listaP.setNombre(cursor.getString(1));
            listaP.setApellido(cursor.getString(2));
            listaP.setEdad(cursor.getInt(3));
            listaP.setCorreo(cursor.getString(4));
            listaP.setdireccion(cursor.getString(5));
            lista.add(listaP);
        }

        cursor.close();

        LLenarLista();
    }

    private void LLenarLista() {
        listaconcatenada = new ArrayList<String>();

        for(int i =0;  i < lista.size(); i++)
        {
            listaconcatenada.add(lista.get(i).getNombre() + " " +
                    lista.get(i).getApellido() + " - " +
                    lista.get(i).getEdad() + " - " +
                    lista.get(i).getdireccion() + " - " +
                    lista.get(i).getCorreo());
        }
    }
}