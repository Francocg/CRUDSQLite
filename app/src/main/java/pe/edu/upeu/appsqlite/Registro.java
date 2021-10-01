package pe.edu.upeu.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pe.edu.upeu.appsqlite.db.DbEmpleado;

public class Registro extends AppCompatActivity {

    EditText txtNombre, txtApellido, txtDni, txtCorreo, txtPuesto;
    Button btnRegistrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtNombre = findViewById(R.id.textNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtDni = findViewById(R.id.txtDni);
        txtCorreo = findViewById(R.id.txtCorreo);
        txtPuesto = findViewById(R.id.txtPuesto);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                DbEmpleado dbEmpleado = new DbEmpleado(Registro.this);
                long id = dbEmpleado.insertarEmpleado(txtNombre.getText().toString(),txtApellido.getText().toString(), txtDni.getText().toString(), txtCorreo.getText().toString(), txtPuesto.getText().toString() );
                if(id > 0){
                    Toast.makeText(Registro.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                    limpiar();
                }else{
                    Toast.makeText(Registro.this, "ERROR EN EL REGISTRO", Toast.LENGTH_LONG).show();
                }


            }
        });


    }
    //Para limpiar el registro
    private void limpiar(){
        txtNombre.setText("");
        txtApellido.setText("");
        txtDni.setText("");
        txtCorreo.setText("");
        txtPuesto.setText("");
    }
}