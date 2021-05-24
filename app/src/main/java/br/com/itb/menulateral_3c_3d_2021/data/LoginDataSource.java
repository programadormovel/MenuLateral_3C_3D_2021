package br.com.itb.menulateral_3c_3d_2021.data;

import br.com.itb.menulateral_3c_3d_2021.data.dao.LoggedInUserDao;
import br.com.itb.menulateral_3c_3d_2021.data.model.LoggedInUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<LoggedInUser> login(String username, String password) {

        try {
            // TODO: ESTABELECER CONEXÃO COM O BANCO DE DADOS
            LoggedInUser login = LoggedInUserDao.verificarUsuario(username, password);

            // Controlar Erro ao logar
            if(login == null){
                return new Result.Error(new IOException("Usuário não encontrado ou " +
                        "senha errada!"));
            }

            /*LoggedInUser fakeUser =
                    new LoggedInUser(
                            java.util.UUID.randomUUID().toString(),
                            "Jane Doe");
            */
            return new Result.Success<>(login);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}