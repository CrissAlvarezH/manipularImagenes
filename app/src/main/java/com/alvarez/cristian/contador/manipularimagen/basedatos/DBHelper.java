package com.alvarez.cristian.contador.manipularimagen.basedatos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by CristianAlvarez on 21/10/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private final static String NOMBRE_BASE_DATOS = "basededatos.sqlite";
    private final static int VERSION_ESQUEMA = 1;

    public DBHelper(Context context) {
        super(context, NOMBRE_BASE_DATOS, null, VERSION_ESQUEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String CREAR_TABLA_IMAGENES = "CREATE TABLE imagenes (" +
                "direccion_img TEXT PRIMARY KEY," +// Ruta de la imagen (tiene que se unica)
                "estado_img TEXT);";// enviada, no_enviada

        database.execSQL(CREAR_TABLA_IMAGENES);
    }

    public void insertarImagen(String ruta, String estado){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("direccion_img", ruta);
        contentValues.put("estado_img", estado);

        if(database.insert("imagenes", null, contentValues) == -1)
            Log.e("insertarImagen", "Error al insertar un imagen en la base de datos");
        else
            Log.e("insertarImagen", "Imagen insertada correctamente");
    }

    public void actualizarImagen(String ruta, String estado){
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("direccion_img", ruta);
        contentValues.put("estado_img", estado);

        int filasAfectadas = database.update("imagenes", contentValues, "direccion_img = ?",
                new String[] {ruta});

        Log.e("actualizarImagen", filasAfectadas+" registros afectados de la tabla imagenes");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
