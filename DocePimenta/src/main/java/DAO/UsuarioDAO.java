package DAO;

import DTO.UsuarioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class UsuarioDAO {

    public void atualizarNomeUsuario(int idUsuario, String novoNome) {
        Connection conexao = new ConexaoDAO().conectaBD();

        if (conexao != null) {
            String sql = "UPDATE usuarios SET nome = ? WHERE id = ?";

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, novoNome);
                stmt.setInt(2, idUsuario);
                int linhasAfetadas = stmt.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Usuário atualizado com sucesso!");
                } else {
                    System.out.println("Nenhum usuário encontrado com esse ID.");
                }
            } catch (SQLException e) {
                System.out.println("Erro ao atualizar usuário: " + e.getMessage());
            }
        }
    }

    public void deletarUsuarioPorId(int idUsuario) {
        Connection conexao = new ConexaoDAO().conectaBD();

        if (conexao != null) {
            String sql = "DELETE FROM usuarios WHERE id = ?";

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setInt(1, idUsuario);
                int linhasAfetadas = stmt.executeUpdate();

                if (linhasAfetadas > 0) {
                    System.out.println("Usuário deletado com sucesso!");
                } else {
                    System.out.println("Nenhum usuário encontrado com esse ID.");
                }
            } catch (SQLException e) {
                System.out.println("Erro ao deletar usuário: " + e.getMessage());
            }
        }
    }

    public void cadastrarUsuario(UsuarioDTO usuarioDTO) {
        Connection conexao;
        try {
            conexao = new ConexaoDAO().conectaBD();
            String sql = "INSERT INTO usuarios (nome, senha) VALUES (?, ?)";

            try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
                stmt.setString(1, usuarioDTO.getNome_usuario());
                stmt.setString(2, usuarioDTO.getSenha_usuario());

                stmt.execute();
                System.out.println("Usuário cadastrado com sucesso!");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: " + e.getMessage());
        }
    }
}