package br.com.itb.menulateral_3c_3d_2021.data.dao;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Conexão com o banco de dados externo SQL Server
public class Conexao {

    // Método estático de conexão com o banco de dados
    public static Connection conectar(){
        Connection conexao = null;
        try{
            //Liberar política de seguranção
            StrictMode.ThreadPolicy policy;
            policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            // Aplicar política, ou quebra de segurança
            StrictMode.setThreadPolicy(policy);

            // Verificar se o driver de conexão esta importado
            Class.forName("net.sourceforge.jtds.jdbc.Driver");

            // Preparar string de conexão
            String strConn = "jdbc:jtds:sqlserver://192.168.0.200;" +
                    "databaseName=PRAP3_2021;user=sa;password=123456;";

            // Criar conexão com o banco
            conexao = DriverManager.getConnection(strConn);
        }catch (SQLException | ClassNotFoundException e){
            e.getMessage();
        }
        return conexao;
    }
}
