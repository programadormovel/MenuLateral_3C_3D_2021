package br.com.itb.menulateral_3c_3d_2021.data.dao;

import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.itb.menulateral_3c_3d_2021.data.model.LoggedInUser;

// Classe de acesso ao banco de dados do LoggedInUser
public class LoggedInUserDao {

    // Método estático de pesquisa ao banco de dados
    public static LoggedInUser verificarUsuario(String usuario,
                                                String senha){
        LoggedInUser login = null;

        // Criar a pesquisa ao banco
        try{
            // Preparar a declaração de pesquisa (comando select)
            PreparedStatement pst;
            pst = Conexao.conectar().prepareStatement("" +
                    "Select id, conta, senha, nome, email, nivel_acesso from login " +
                    "where email = ? and senha = ?;");
            // Passagem de parâmetros
            pst.setString(1, usuario);
            pst.setString(2, senha);
            // Chamada da execução da query
            ResultSet res = pst.executeQuery();

            if(res!=null){
                while(res.next()){
                    login = new LoggedInUser(
                            String.valueOf(res.getInt(1)),
                            res.getString(4)
                    );
                }
            }
        }catch (SQLException e){
            e.getMessage();
        }

        return login;
    }

}
