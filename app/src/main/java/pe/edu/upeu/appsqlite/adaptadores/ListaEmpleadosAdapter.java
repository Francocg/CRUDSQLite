package pe.edu.upeu.appsqlite.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import pe.edu.upeu.appsqlite.R;
import pe.edu.upeu.appsqlite.entidades.Empleados;
import pe.edu.upeu.appsqlite.vista;

public class ListaEmpleadosAdapter extends RecyclerView.Adapter<ListaEmpleadosAdapter.EmpleadoViewHolder>{

    ArrayList<Empleados> listaEmpleados;




    public ListaEmpleadosAdapter(ArrayList<Empleados> listaEmpleados){
        this.listaEmpleados = listaEmpleados;

    }

    @NonNull
    @Override
    public EmpleadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_empleado, null, false);
        return new EmpleadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpleadoViewHolder holder, int position) {
        holder.viewNombre.setText(listaEmpleados.get(position).getNombre());
        holder.viewApellido.setText(listaEmpleados.get(position).getApellidos());
        holder.viewDni.setText(listaEmpleados.get(position).getDni());
        holder.viewCorreo.setText(listaEmpleados.get(position).getCorreo());
        holder.viewPuesto.setText(listaEmpleados.get(position).getPuesto());

    }

    @Override
    public int getItemCount() {
        return listaEmpleados.size();
    }

    public class EmpleadoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewApellido, viewDni, viewCorreo, viewPuesto;

        public EmpleadoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewApellido = itemView.findViewById(R.id.viewApellido);
            viewDni = itemView.findViewById(R.id.viewDni);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);
            viewPuesto = itemView.findViewById(R.id.viewPuesto);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, vista.class);
                    intent.putExtra("ID", listaEmpleados.get(getAdapterPosition()).getIdempleado());
                    context.startActivity(intent);
                }
            });
        }
    }
}
