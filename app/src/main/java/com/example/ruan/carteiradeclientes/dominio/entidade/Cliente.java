package com.example.ruan.carteiradeclientes.dominio.entidade;

import java.io.Serializable;

// A interface Serializable permite que os objetos deste tipo possam ser passados como parâmetros
// entre as Activitys
public class Cliente implements Serializable {

    public int codigo;
    public String nome;
    public String endereco;
    public String email;
    public String telefone;

    public Cliente(){
        // Ao criar um objeto novo, o código padrão é 0.
        // Quando um objeto já existir, o código assumirá outro valor diferente de 0
        codigo = 0;
    }
}
