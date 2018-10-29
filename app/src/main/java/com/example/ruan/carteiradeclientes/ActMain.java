package com.example.ruan.carteiradeclientes;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ActMain extends AppCompatActivity { // O 'AppCompatActivity' é uma classe utilizada
    // para dar suporte a recursos que somente tem em aplicações mais novas

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main); //Relaciona a Activity com o Layout

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //É um componente (toolbar) que
        // permite adicionar um menu/botão na parte superior da aplicação
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab); // É um componente
        // (botão redondinho que fica na parte inferior direita da tela com ícone de email padrão) que tem
        // sido muito utilizado nas aplicações, se tornando um padrão de layout. Sua idéia de uso é
        // deixar nos padrões da Google
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
