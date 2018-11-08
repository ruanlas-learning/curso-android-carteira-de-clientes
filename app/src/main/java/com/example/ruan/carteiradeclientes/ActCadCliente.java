package com.example.ruan.carteiradeclientes;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ruan.carteiradeclientes.database.DadosOpenHelper;
import com.example.ruan.carteiradeclientes.dominio.entidade.Cliente;
import com.example.ruan.carteiradeclientes.dominio.repositorio.ClienteRepositorio;

public class ActCadCliente extends AppCompatActivity {

//    private FloatingActionButton fab;
    private EditText edtNome, edtEndereco, edtTelefone, edtEmail;
    private ConstraintLayout layoutContentActCadCliente;

    private ClienteRepositorio clienteRepositorio;

    private DadosOpenHelper dadosOpenHelper;

    private SQLiteDatabase conexao;

    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edtNome = (EditText)findViewById(R.id.edtNome);
        edtEndereco = (EditText)findViewById(R.id.edtEndereco);
        edtTelefone = (EditText)findViewById(R.id.edtTelefone);
        edtEmail = (EditText)findViewById(R.id.edtEmail);
        layoutContentActCadCliente = (ConstraintLayout)findViewById(R.id.layoutContentActCadCliente);

//        fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        criarConexao();
        clienteRepositorio = new ClienteRepositorio(conexao);
        verificaParametroPassado();
    }

    private void verificaParametroPassado(){

        // Esta classe Bundle que irá conter os parâmetros que foram passados da outra activity
        // para esta activity. O método getIntent().getExtras() recupera os parâmetros
        // (se existirem) que foram passados
        Bundle bundle = getIntent().getExtras();

        // Está verificando se o bundle é nulo, e se contêm o parâmetro chamado cliente
        if ( (bundle != null) && (bundle.containsKey("cliente")) ){
            // Abaixo está sendo recuperado o parâmetro cliente que foi passado pela outra activity.
            // Como a classe Cliente implementa a interface Serializable, podemos utilizar este
            // método getSerializable, e depois faz-se um cast para a classe Cliente
//            Cliente cliente = (Cliente) bundle.getSerializable("cliente");
            cliente = (Cliente) bundle.getSerializable("cliente");

            edtNome.setText(cliente.nome);
            edtTelefone.setText(cliente.telefone);
            edtEndereco.setText(cliente.endereco);
            edtEmail.setText(cliente.email);
        }else {
            // Se não for passado o parâmetro cliente, o objeto cliente é inicializado por padrão
            cliente = new Cliente();
        }
    }

    private void criarConexao(){

        try {

            dadosOpenHelper = new DadosOpenHelper(this);
            conexao = dadosOpenHelper.getWritableDatabase(); //Obtém a conexão com o banco de dados
            // para escrita/leitura

            // O primeiro parâmetro do Snackbar espera-se um objeto do tipo View. Como alternativa,
            // pode-se passar o layout desta Activity localizado no arquivo 'content_act_main.xml'
            Snackbar.make(layoutContentActCadCliente, R.string.message_conexao_criada_com_sucesso, Snackbar.LENGTH_LONG)
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //Configura o menu do aplicativo configurado no arquivo 'menu_act_cad_cliente.xml'
        MenuInflater menuInflater = getMenuInflater(); // o 'MenuInflater' irá inflar na janela
                        // com o menu e tudo que estiver no arquivo
                        // xml será passado para o objeto menu
        menuInflater.inflate(R.menu.menu_act_cad_cliente, menu);

        return super.onCreateOptionsMenu(menu);
    }

    private void confirmar(){

        if (validaCampos()){
//            Cliente cliente = new Cliente();
//            cliente = new Cliente();

            cliente.nome = edtNome.getText().toString();
            cliente.endereco = edtEndereco.getText().toString();
            cliente.email = edtEmail.getText().toString();
            cliente.telefone = edtTelefone.getText().toString();

            try {
                if (cliente.codigo == 0){
                    clienteRepositorio.inserir(cliente);
                }else {
                    clienteRepositorio.alterar(cliente);
                }

                finish();
            }catch (SQLException e){
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle(R.string.title_erro);
                dlg.setMessage(e.getMessage());
                dlg.setNeutralButton(R.string.action_ok, null);
                dlg.show();
            }
        }
    }

    private boolean validaCampos(){
        String nome, endereco, email, telefone;
        nome = edtNome.getText().toString();
        endereco = edtEndereco.getText().toString();
        email = edtEmail.getText().toString();
        telefone = edtTelefone.getText().toString();

        boolean campoInvalido = false;

        if (campoInvalido = isCampoVazio(nome)){
            edtNome.requestFocus();
        }else if (campoInvalido = isCampoVazio(endereco)){
            edtEndereco.requestFocus();
        }else if (campoInvalido = !isEmailValido(email)){
            edtEmail.requestFocus();
        }else if (campoInvalido = isCampoVazio(telefone)){
            edtTelefone.requestFocus();
        }

        if (campoInvalido){
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.title_aviso);
            dlg.setMessage(R.string.message_campos_invalidos_brancos);
            dlg.setNeutralButton(R.string.action_ok, null);
            dlg.show();
        }

        return !campoInvalido;
    }

    private boolean isCampoVazio(String valor){
        boolean resultado = ( TextUtils.isEmpty(valor) || valor.trim().isEmpty() );
        // a função trim remove os espaços da string se houver
        return resultado;
    }

    private boolean isEmailValido(String email){
        boolean resultado = ( !isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        // a Classe Patterns possui alguns regex para validar alguns dados padrões
        // a constante EMAIL_ADDRESS possui um regex que valida um email passado através da função
        //      'matcher()'. Para validar efetivamente um email, utiliza-se o método 'matches()'
        return resultado;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Este método é responsável por tratar as ações do menu

        int itemId = item.getItemId(); //recupera o id do item do menu clicado

        switch (itemId){
            case R.id.action_ok:
                confirmar();
//                validaCampos();
//                Toast.makeText(this, "Botão OK selecionado 'id:" + itemId, Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_cancelar:
//                Toast.makeText(this, "Botão Cancelar selecionado 'id:" + itemId, Toast.LENGTH_SHORT).show();
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
