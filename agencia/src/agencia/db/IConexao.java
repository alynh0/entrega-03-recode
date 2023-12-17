package agencia.db;

import java.sql.Connection;

public interface IConexao {

    public Connection getConnection();
    public void closeConnection();
    
}