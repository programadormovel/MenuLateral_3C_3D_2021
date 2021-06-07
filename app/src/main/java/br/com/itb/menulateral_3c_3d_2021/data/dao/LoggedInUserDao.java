package br.com.itb.menulateral_3c_3d_2021.data.dao;

import android.util.Log;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.itb.menulateral_3c_3d_2021.data.Result;
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

    public static LoggedInUser inserirUsuario(LoggedInUser usuario){

        try{
            // Preparar Declaração de Inserção
            String declaracao = "Insert Into login (conta, senha, nome, email, nivel_acesso) " +
                    "values (?,?,?,?,?);";

            // Criar preparação de declaração para conectar no banco de dados
            PreparedStatement pst = Conexao.conectar().prepareStatement(declaracao);
            // Passagem de parâmetros
            pst.setString(1, usuario.getConta());
            pst.setString(2, usuario.getSenha());
            pst.setString(3, usuario.getDisplayName());
            pst.setString(4, usuario.getEmail());
            pst.setInt(5, usuario.getNivel_acesso());
            // Executar inserção
            pst.executeUpdate();
            // Consultar o ID inserido
            String declaracao2 = "Select id from login where email = ?";
            PreparedStatement pst2 = Conexao.conectar().prepareStatement(declaracao2);
            pst2.setString(1, usuario.getEmail());
            ResultSet resultado = pst2.executeQuery();
            // Se resultado existir, captura ID do email novo inserido
            if(resultado!=null){
                while (resultado.next()){
                    usuario.setUserId(String.valueOf(resultado.getInt("id")));
                }

                return usuario;
            }
        }catch (SQLException e){
            e.getMessage();
        }
        return null;
    }

}
