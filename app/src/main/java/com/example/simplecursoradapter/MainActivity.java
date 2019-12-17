package com.example.simplecursoradapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabase;
    OHCategoria ohCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] from = new String[]{"nombre", "cate"};
        int[] to = new int[]{R.id.ciclo, R.id.cate};
        setContentView(R.layout.activity_main);
        ohCategoria = new OHCategoria(this, "BBDCategorias", null, 1);
        sqLiteDatabase = ohCategoria.getWritableDatabase();
        insertarDatosCodigo();

        sqLiteDatabase = ohCategoria.getReadableDatabase();
        if (sqLiteDatabase !=null)
        {
            Spinner desplegable = this.findViewById(R.id.spinner);
            Cursor cur = sqLiteDatabase.rawQuery("select idcategoria as _id, nombre , cate from categoria",null);
            SimpleCursorAdapter mAdapter = new SimpleCursorAdapter(this, R.layout.spinner_layout, cur, from, to, 0x0);
            desplegable.setAdapter(mAdapter);
            sqLiteDatabase.close();
        }
    }

    public void insertarDatosCodigo()
    {
        if (sqLiteDatabase != null)
        {
            ContentValues valores = new ContentValues();
            valores.put("nombre", "ASIR");
            valores.put("cate", "Superior");
            valores.put("idcategoria", 1);
            sqLiteDatabase.insert("categoria",null, valores);
            valores.put("nombre", "DAM");
            valores.put("cate", "Superior");
            valores.put("idcategoria", 2);
            sqLiteDatabase.insert("categoria",null, valores);
            valores.put("nombre", "SMR");
            valores.put("cate", "Medio");
            valores.put("idcategoria", 3);
            sqLiteDatabase.insert("categoria",null, valores);
            sqLiteDatabase.close();
        }
    }
}
