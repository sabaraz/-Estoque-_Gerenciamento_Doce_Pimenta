package DAO;

import DTO.VendaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VendaDAO {

    Connection conn;
    PreparedStatement pstm;

    public void cadastrarVenda(VendaDTO venda) throws ClassNotFoundException {
        String sql = "INSERT INTO vendas (data_venda, valor_total, forma_pagamento, desconto, tipo_venda) VALUES (?, ?, ?, ?, ?)";

        conn = new ConexaoDAO().conectaBD(); // Sua classe de conexão

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, venda.getDataVenda());
            pstm.setDouble(2, venda.getValorTotal());
            pstm.setString(3, venda.getFormaPagamento());
            pstm.setDouble(4, venda.getDesconto());
            pstm.setString(5, venda.getTipoVenda());
            
            pstm.execute();
            pstm.close();
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar venda: " + e.getMessage());
        }
    }
}
