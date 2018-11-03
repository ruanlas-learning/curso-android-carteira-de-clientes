package com.example.ruan.carteiradeclientes;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ruan.carteiradeclientes.dominio.entidade.Cliente;

import java.util.List;

// Esta classe armazenará os dados do RecycleView
// [ https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=pt-br ]
public class ClienteAdapter extends RecyclerView.Adapter<ClienteAdapter.ViewHolderCliente> {
                                // Estamos definindo como padrão para o adapter o
                                // ViewHolderCliente que criamos no final desta classe

    private List<Cliente> dados;

    public ClienteAdapter(List<Cliente> dados){
        this.dados = dados;
    }

//    @NonNull
//    @Override
//    public ClienteAdapter.ViewHolderCliente onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ClienteAdapter.ViewHolderCliente viewHolder, int i) {
//
//    }

    @NonNull
    @Override
    public ViewHolderCliente onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCliente viewHolderCliente, int i) {

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderCliente extends RecyclerView.ViewHolder{
        // Esta classe ViewHolderCliente será responsável por recuperar as referências do layout
        // criado no arquivo 'linha_clientes.xml', ou seja, buscará as referências da interface.

        public TextView txtLinhaNomeCliente, txtLinhaTelefoneCliente;

        public ViewHolderCliente(@NonNull View itemView) {
            super(itemView);

            txtLinhaNomeCliente = (TextView) itemView.findViewById(R.id.txtLinhaNomeCliente);
            txtLinhaTelefoneCliente = (TextView) itemView.findViewById(R.id.txtLinhaTelefoneCliente);

        }
    }
}
