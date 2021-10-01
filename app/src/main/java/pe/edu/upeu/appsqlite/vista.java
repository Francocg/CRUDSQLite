package pe.edu.upeu.appsqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pe.edu.upeu.appsqlite.db.DbEmpleado;
import pe.edu.upeu.appsqlite.entidades.Empleados;

public class vista extends AppCompatActivity {
    EditText txtNombre, txtApellido, txtDni, txtCorreo, txtPuesto;
    Button btnRegistrar;
    FloatingActionButton fabEditar, fabEliminar;
    Empleados empleados;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista);

        txtNombre = findViewById(R.id.textNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtDni = findViewById(R.id.txtDni);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtPuesto = findViewById(R.id.txtPuesto);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        fabEditar = findViewById(R.id.fabEditar);
        fabEliminar = findViewById(R.id.fabEliminar);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            }else{
                id = extras.getInt("ID");
            }
        }else{
            id = (int) savedInstanceState.getSerializable("ID");
        }
        DbEmpleado dbEmpleado = new DbEmpleado(vista.this);
        empleados = dbEmpleado.verEmpleados(id);

        if(empleados !=null){
            txtNombre.setText(empleados.getNombre());
            txtApellido.setText(empleados.getApellidos());
            txtDni.setText(empleados.getDni());
            txtCorreo.setText(empleados.getCorreo());
            txtPuesto.setText(empleados.getPuesto());
            btnRegistrar.setVisibility(View.INVISIBLE);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtApellido.setInputType(InputType.TYPE_NULL);
            txtDni.setInputType(InputType.TYPE_NULL);
            txtCorreo.setInputType(InputType.TYPE_NULL);
            txtPuesto.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(vista.this, Editar.class);
                intent.putExtra("ID", id);
                startActivity(intent);

            }



        });

        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder bulder = new AlertDialog.Builder(vista.this);
                bulder.setMessage("¿Desea eliminar este empleado?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if(dbEmpleado.eliminarEmpleado(id)){
                            lista();

                        }

                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).show();
            }
        });
    }
    private  void lista(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}