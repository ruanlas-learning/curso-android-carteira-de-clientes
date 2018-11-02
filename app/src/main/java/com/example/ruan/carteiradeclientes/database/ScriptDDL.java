package com.example.ruan.carteiradeclientes.database;

public class ScriptDDL {

    public static String getCreateTableCliente(){
        StringBuilder sql = new StringBuilder();

        sql.append("        CREATE TABLE IF NOT EXISTS cliente ( ");
        sql.append("            codigo   INTEGER      PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("            nome     VARCHAR(250) NOT NULL DEFAULT(''), ");
                // a assinatura DEFAULT('') indica que se caso não for passado este parâmetro,
                        // ele receberá um valor vazio
        sql.append("            endereco VARCHAR(255) NOT NULL DEFAULT(''), ");
        sql.append("            email    VARCHAR(200) NOT NULL DEFAULT(''), ");
        sql.append("            telefone VARCHAR(20)  NOT NULL DEFAULT('')  ) ");

        return sql.toString();
    }
}
