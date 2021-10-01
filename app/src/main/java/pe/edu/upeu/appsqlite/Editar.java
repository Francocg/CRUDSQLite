package pe.edu.upeu.appsqlite;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pe.edu.upeu.appsqlite.db.DbEmpleado;
import pe.edu.upeu.appsqlite.entidades.Empleados;

public class Editar extends AppCompatActivity {
    EditText txtNombre, txtApellido, txtDni, txtCorreo, txtPuesto;
    Button btnRegistrar;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Empleados empleados;
    int id = 0;

    @SuppressLint("RestrictedApi")
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
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

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
        DbEmpleado dbEmpleado = new DbEmpleado(Editar.this);
        empleados = dbEmpleado.verEmpleados(id);

        if(empleados !=null){
            txtNombre.setText(empleados.getNombre());
            txtApellido.setText(empleados.getApellidos());
            txtDni.setText(empleados.getDni());
            txtCorreo.setText(empleados.getCorreo());
            txtPuesto.setText(empleados.getPuesto());

        }
        btnRegistrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(!txtNombre.getText().toString().equals("") && !txtApellido.getText().toString().equals("") && !txtDni.getText().toString().equals("") && !txtCorreo.getText().toString().equals("") && !txtPuesto.getText().toString().equals(""));{

                    correcto = dbEmpleado.editarEmpleado(id, txtNombre.getText().toString(), txtApellido.getText().toString(),txtDni.getText().toString(), txtCorreo.getText().toString(),txtPuesto.getText().toString());

                    if(correcto){
                        Toast.makeText(Editar.this, "REGISTRO MODIFICADO", Toast.LENGTH_SHORT).show();
                        verRegistro();

                    }else{
                        Toast.makeText(Editar.this, "ERROR AL MODIFICAR REGISTRO ", Toast.LENGTH_SHORT).show();
                    }
                }

                }

        });
    }
    private void verRegistro(){
        Intent intent = new Intent(this, vista.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}
