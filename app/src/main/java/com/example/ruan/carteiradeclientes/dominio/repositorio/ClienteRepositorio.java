package com.example.ruan.carteiradeclientes.dominio.repositorio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ruan.carteiradeclientes.dominio.entidade.Cliente;

import java.util.ArrayList;
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

        List<Cliente> clienteList = new ArrayList<Cliente>();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT codigo, nome, endereco, email, telefone ");
        sql.append("    FROM cliente");

        /*
        * o método 'rawQuery' permite fazer uma consulta utilizando um script SQL. Este método
        * espera dois parâmetros:
        * 1º parâmetro => uma string contendo um script SQL
        * 2º parâmetro => um array de string contendo parâmetros de query [ caso seja criado
        * parâmetros de query no script SQL ]
        * */

        Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if (resultado.getCount() > 0){
            resultado.moveToFirst();

            do {
                Cliente cliente = new Cliente();
                cliente.codigo = resultado.getInt( //este método obtém o resultado da coluna
                                            // através de seu índice. O índice da coluna é a
                                            // sequência dos campos passada na query
                        resultado.getColumnIndexOrThrow("codigo") //este método recupera
                                    // o índice da coluna através do nome da coluna
                );
                cliente.email = resultado.getString(
                        resultado.getColumnIndexOrThrow("email")
                );
                cliente.telefone = resultado.getString(
                        resultado.getColumnIndexOrThrow("telefone")
                );
                cliente.endereco = resultado.getString(
                        resultado.getColumnIndexOrThrow("endereco")
                );
                cliente.nome = resultado.getString(
                        resultado.getColumnIndexOrThrow("nome")
                );

                clienteList.add(cliente);

            }while (resultado.moveToNext());
        }
        if (resultado != null && !resultado.isClosed()){
            resultado.close();
        }

        return clienteList;
    }

    public Cliente buscarCliente(int codigo){

        Cliente cliente = null;

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT codigo, nome, endereco, email, telefone ");
        sql.append("    FROM cliente");
        sql.append(" WHERE codigo = ? ");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(codigo);

        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);

        if (resultado.getCount() > 0 ){
            resultado.moveToFirst();

            cliente = new Cliente();
            cliente.codigo = resultado.getInt(
                    resultado.getColumnIndexOrThrow("codigo")
            );
            cliente.email = resultado.getString(
                    resultado.getColumnIndexOrThrow("email")
            );
            cliente.telefone = resultado.getString(
                    resultado.getColumnIndexOrThrow("telefone")
            );
            cliente.endereco = resultado.getString(
                    resultado.getColumnIndexOrThrow("endereco")
            );
            cliente.nome = resultado.getString(
                    resultado.getColumnIndexOrThrow("nome")
            );
        }

        if (resultado != null && !resultado.isClosed()){
            resultado.close();
        }

        return cliente;
    }
}
