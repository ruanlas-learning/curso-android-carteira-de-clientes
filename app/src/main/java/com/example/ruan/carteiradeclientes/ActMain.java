package com.example.ruan.carteiradeclientes;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.ruan.carteiradeclientes.database.DadosOpenHelper;

public class ActMain extends AppCompatActivity { // O 'AppCompatActivity' é uma classe utilizada
    // para dar suporte a recursos que somente tem em aplicações mais novas

    private RecyclerView lstDados;
    private FloatingActionButton fab;
    private ConstraintLayout layoutContentMain;

    private SQLiteDatabase conexao;
    private DadosOpenHelper dadosOpenHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main); //Relaciona a Activity com o Layout

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //É um componente (toolbar) que
        // permite adicionar um menu/botão na parte superior da aplicação
        setSupportActionBar(toolbar);



        fab = (FloatingActionButton) findViewById(R.id.fab); // É um componente
        // (botão redondinho que fica na parte inferior direita da tela com ícone de email padrão) que tem
        // sido muito utilizado nas aplicações, se tornando um padrão de layout. Sua idéia de uso é
        // deixar nos padrões da Google
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
////                        .setAction("Action", null).show();
//                Intent intent = new Intent(view.getContext(), ActCadCliente.class); //ou
////                Intent intent = new Intent(ActMain.this, ActCadCliente.class);
//                startActivity(intent);
//            }
//        });

        lstDados = (RecyclerView)findViewById(R.id.lstDados);
        layoutContentMain = (ConstraintLayout)findViewById(R.id.layoutContentMain);

        criarConexao();
    }

    private void criarConexao(){

        try {

            dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase(); //Obtém a conexão com o banco de dados
                                                            // para escrita/leitura

            // O primeiro parâmetro do Snackbar espera-se um objeto do tipo View. Como alternativa,
            // pode-se passar o layout desta Activity localizado no arquivo 'content_act_main.xml'
            Snackbar.make(layoutContentMain, R.string.message_conexao_criada_com_sucesso, Snackbar.LENGTH_LONG)
                        .setAction(R.string.lbl_ok, null).show(); // o primeiro parâmetro do
                    // setAction define o nome do botão que aparecerá para ser clicado, e o segundo
                    // parâmetro define a ação que este botão terá

        }catch (SQLException e){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_erro);
            dlg.setMessage(e.getMessage());
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();
        }

    }

    public void cadastrar(View view){
        Intent intent = new Intent(ActMain.this, ActCadCliente.class);
        startActivity(intent);
    }

}
