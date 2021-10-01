package pe.edu.upeu.appsqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import pe.edu.upeu.appsqlite.adaptadores.ListaEmpleadosAdapter;
import pe.edu.upeu.appsqlite.db.DbEmpleado;
import pe.edu.upeu.appsqlite.db.DbHelper;
import pe.edu.upeu.appsqlite.entidades.Empleados;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaEmpleados;
    ArrayList<Empleados> listaArrayEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaEmpleados = findViewById(R.id.listarEmpleado);
        listaEmpleados.setLayoutManager(new LinearLayoutManager(this));

        DbEmpleado dbEmpleado = new DbEmpleado(MainActivity.this);

        listaArrayEmpleados = new ArrayList<>();

        ListaEmpleadosAdapter adapter = new ListaEmpleadosAdapter(dbEmpleado.mostrarEmpleados());
        listaEmpleados.setAdapter(adapter);


    }
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menuprincipal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void nuevoRegistro(){
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }
}