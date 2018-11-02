package com.example.ruan.carteiradeclientes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
*       A classe 'SQLiteOpenHelper' é responsável por criar o banco de dados e gerenciá-lo,
*   criando conexão com o banco para ser possível fazer as operações de insert, update e delete
*   dentro do banco de dados.
*       Desta forma, é preciso criar uma classe que seja filha da 'SQLiteOpenHelper'
*
* */

public class DadosOpenHelper extends SQLiteOpenHelper {

    // é necessário implementar o construtor default da classe
    public DadosOpenHelper(Context context) {



        super(context, "dados", null, 1);

        /* Parâmetros do construtor da classe pai 'SQLiteOpenHelper'
        *  """     super(context, name, factory, version);   """
        * name => nome do banco de dados
        * factory => cursor factory customizado (uma classe CursorFactory customizada permite
        *   manipular o retorno dos dados, etc). Caso não haja um CursorFactory, pode-se passar null
        * version => versão do banco de dados (caso haja alguma alteração na estrutura do banco,
        *   deve-se incrementar a versão - NUNCA DECREMENTE A VERSÃO!!)
        * */
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // este método é responsável por criar a estrutura de tabelas do banco de dados

        db.execSQL(ScriptDDL.getCreateTableCliente());// Ao ser criado uma instância desta classe,
                                            // este método é chamado e será executado este script
                                            // que será responsável por criar a estrutura da tabela
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // este método é responsável por atualizar a estrutura de tabelas do banco de dados

    }
}
