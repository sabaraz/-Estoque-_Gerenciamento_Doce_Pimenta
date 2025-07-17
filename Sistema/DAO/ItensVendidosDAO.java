package DAO;

import DTO.ItensVendidosDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class ItensVendidosDAO {

    public void salvar(ItensVendidosDTO dto) throws ClassNotFoundException {

        //alterações Gustavo
        String sql = "INSERT INTO itens_vendidos (tipo_produto, nome_produto, marca, quantidade, preco_unitario, subtotal, desconto, imposto, total) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = new ConexaoDAO().conectaBD();

        try (PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setString(1, dto.getTipoProduto());
            pstm.setString(1, dto.getTipoProduto());
            pstm.setString(2, dto.getNomeProduto());
            pstm.setString(3, dto.getMarca());
            pstm.setInt(4, dto.getQuantidadeVendida());
            pstm.setDouble(5, dto.getPrecoUnitario());
            pstm.setDouble(6, dto.getSubtotalVenda());
            pstm.setDouble(7, dto.getDesconto());
            pstm.setDouble(8, dto.getImposto());
            pstm.setDouble(9, dto.getTotal());

            pstm.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");

            //Adição de nova funcionalidade - Julia
            ProdutoDAO produtoDao = new ProdutoDAO();
            produtoDao.atualizarEstoque(dto.getNomeProduto(), dto.getQuantidadeVendida());

            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso e estoque atualizado!");
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + erro.getMessage());
        }
    }

}
