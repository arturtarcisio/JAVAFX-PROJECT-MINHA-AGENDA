package br.com.treinaweb.agenda.repositorios.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.treinaweb.agenda.entidades.Contato;
import br.com.treinaweb.agenda.fabricas.FabricaConexaoJDBC;
import br.com.treinaweb.agenda.repositorios.interfaces.AgendaRepositorio;

public class ContatoRepositorioJDBC implements AgendaRepositorio<Contato> {

	@Override
	public List<Contato> selecionar() throws SQLException, IOException {
		List<Contato> listaContatos = new ArrayList<Contato>();
		try (Connection conexao = FabricaConexaoJDBC.criarConexao()) {
			Statement comando = conexao.createStatement();
			ResultSet rs = comando.executeQuery("select * from contatos");
			while (rs.next()) {
				Contato contato = new Contato();
				contato.setId(rs.getInt("id"));
				contato.setNome(rs.getString("nome"));
				contato.setIdade(rs.getInt("idade"));
				contato.setTelefone(rs.getString("telefone"));
				listaContatos.add(contato);
			}
		}
		return listaContatos;

	}

	@Override
	public void inserir(Contato entidade) throws SQLException, IOException {
		try (Connection conexao = FabricaConexaoJDBC.criarConexao()) {
			PreparedStatement comando = conexao
					.prepareStatement("INSERT INTO contatos (nome, idade, telefone) VALUES (?, ?, ?)");
			comando.setString(1, entidade.getNome());
			comando.setInt(2, entidade.getIdade());
			comando.setString(3, entidade.getTelefone());
			comando.execute();
		}

	}

	@Override
	public void atualizar(Contato entidade) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Contato entidade) {
		// TODO Auto-generated method stub

	}

}
