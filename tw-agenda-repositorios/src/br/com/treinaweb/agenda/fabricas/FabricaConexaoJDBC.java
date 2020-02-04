package br.com.treinaweb.agenda.fabricas;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class FabricaConexaoJDBC {

	public static Connection criarConexao() throws IOException, SQLException {
		InputStream is = FabricaConexaoJDBC.class.getClassLoader().getResourceAsStream("application.properties");
		if (is == null) {
			throw new FileNotFoundException(
					"O arquivo de configuração do banco não foi encontrado. Nao está no classpath");
		}
		Properties props = new Properties();
		props.load(is);

		Connection conexao = null;
		conexao = DriverManager.getConnection(props.getProperty("urlConexao"), props.getProperty("usuario"),
				props.getProperty("senhaConexao"));

		return conexao;
	}

}
