package com.example.ruan.carteiradeclientes;

import android.os.Bundle;
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

public class ActCadCliente extends AppCompatActivity {

//    private FloatingActionButton fab;
    private EditText edtNome, edtEndereco, edtTelefone, edtEmail;

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

//        fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
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

    private void validaCampos(){
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
            dlg.setNeutralButton(R.string.lbl_ok, null);
            dlg.show();
        }
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
                validaCampos();
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
