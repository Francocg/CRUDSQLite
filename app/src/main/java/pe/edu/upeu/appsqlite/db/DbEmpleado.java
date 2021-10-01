package pe.edu.upeu.appsqlite.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import pe.edu.upeu.appsqlite.entidades.Empleados;

public class DbEmpleado extends DbHelper{
    Context context;
    public DbEmpleado(@Nullable Context context) {
        super(context);
        this.context = context;

    }
    public long insertarEmpleado(String nombre, String apellido, String dni, String correo, String puesto){
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("apellido", apellido);
            values.put("dni", dni);
            values.put("correo", correo);
            values.put("puesto", puesto);
             id = db.insert(TABLE_EMPLEADO, null, values);
        }catch (Exception ex){
            ex.toString();
        }
        return  id;
    }

    public ArrayList<Empleados> mostrarEmpleados(){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Empleados> listaEmpleados = new ArrayList<>();
        Empleados empleados = null;
        Cursor cursorEmpleados = null;

        cursorEmpleados = db.rawQuery("SELECT * FROM "+ TABLE_EMPLEADO, null);

        if(cursorEmpleados.moveToFirst()){
            do{
                empleados = new Empleados();
                empleados.setIdempleado(cursorEmpleados.getInt(0));
                empleados.setNombre(cursorEmpleados.getString(1));
                empleados.setApellidos(cursorEmpleados.getString(2));
                empleados.setDni(cursorEmpleados.getString(3));
                empleados.setCorreo(cursorEmpleados.getString(4));
                empleados.setPuesto(cursorEmpleados.getString(5));
                listaEmpleados.add(empleados);
            }while(cursorEmpleados.moveToNext());
        }
        cursorEmpleados.close();
        return listaEmpleados;

    }
    public Empleados verEmpleados(int id){
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Empleados empleados = null;
        Cursor cursorEmpleados = null;

        cursorEmpleados = db.rawQuery(" SELECT * FROM " + TABLE_EMPLEADO + " WHERE idempleado = " +  id, null);

        if(cursorEmpleados.moveToFirst()){

                empleados = new Empleados();
                empleados.setIdempleado(cursorEmpleados.getInt(0));
                empleados.setNombre(cursorEmpleados.getString(1));
                empleados.setApellidos(cursorEmpleados.getString(2));
                empleados.setDni(cursorEmpleados.getString(3));
                empleados.setCorreo(cursorEmpleados.getString(4));
                empleados.setPuesto(cursorEmpleados.getString(5));

        }
        cursorEmpleados.close();
        return empleados;

    }
    public boolean editarEmpleado(int idempleado,  String nombre, String apellido, String dni, String correo, String puesto){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("UPDATE "+ TABLE_EMPLEADO + " SET nombre = '"+nombre+"', apellido = '"+apellido+"', dni = '"+dni+"', correo = '"+correo+"', puesto = '"+puesto+"'WHERE idempleado='"+ idempleado +"'" );
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally{
            db.close();
        }
        return  correcto;
    }

    public boolean eliminarEmpleado(int idempleado){
        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {
            db.execSQL("DELETE FROM " + TABLE_EMPLEADO +" WHERE idempleado = '" + idempleado + "' ");
            correcto = true;
        }catch (Exception ex){
            ex.toString();
            correcto = false;
        }finally{
            db.close();
        }
        return  correcto;
    }
}
