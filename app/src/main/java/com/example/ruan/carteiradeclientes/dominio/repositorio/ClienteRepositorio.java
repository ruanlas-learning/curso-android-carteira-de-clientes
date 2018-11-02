package com.example.ruan.carteiradeclientes.dominio.repositorio;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.ruan.carteiradeclientes.dominio.entidade.Cliente;

import java.util.List;

public class ClienteRepositorio {

    private SQLiteDatabase conexao;

    public ClienteRepositorio(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    public void inserir(Cliente cliente){
        // a classe ContentValues receberá os valores que serão enviados para o banco de dados
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", cliente.nome);
        contentValues.put("endereco", cliente.endereco);
        contentValues.put("email", cliente.email);
        contentValues.put("telefone", cliente.telefone);

        conexao.insertOrThrow("cliente", null, contentValues); // Este método realiza a
                        // inserção dos dados na base, e em caso de falha, gera uma Exception.
                        // Ele também retorna um id do registro inserido
        /*
        * O método 'insertOrThrow' espera três parâmetros:
        * 1º parâmetro => nome da tabela
        * 2º parâmetro => nome das colunas que devem receber o valor null caso não sejam passados
        * como parâmetro, ou caso venham com valores vazios
        * 3º parâmetro => o objeto contentValues
        * */
    }

    public void excluir(Cliente cliente){
        String[] parametros = new String[1];
        parametros[0] = String.valueOf(cliente.codigo);

        conexao.delete("cliente", "codigo = ? ", parametros);
    }

    public void alterar(Cliente cliente){
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", cliente.nome);
        contentValues.put("endereco", cliente.endereco);
        contentValues.put("email", cliente.email);
        contentValues.put("telefone", cliente.telefone);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(cliente.codigo);

        conexao.update("cliente", contentValues, "codigo = ? ", parametros);
        /*
        * O método 'update' espera quatro parâmetros:
        * 1º parâmetro => nome da tabela
        * 2º parâmetro => o objeto contentValues
        * 3º parâmetro => uma condição (cláusula WHERE) para atualizar [ o símbulo '?' significa
        *       que está sendo criado um parâmetro de query que será passado pelo
        *       último parâmetro do método ]
        * 4º parâmetro => array de String contendo os parâmetros de query criados na cláusula WHERE
        * */
    }

    public List<Cliente> buscarTodos(){

        return null;
    }

    public Cliente buscarCliente(int codigo){
        return null;
    }
}
