package DAO;

import DTO.CadFornecedorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.SQLException;

public class FornecedorDAO {

    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<CadFornecedorDTO> lista = new ArrayList<>();

    public void cadastrarProduto(CadFornecedorDTO objFornecedorDto) throws ClassNotFoundException {
        String sql = "INSERT INTO fornecedor (nome_empresa, nome_fornecedor, cnpj, telefone, entregador)"
                + "values (?, ?, ?, ?, ?)";

        conn = new ConexaoDAO().conectaBD();

        try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objFornecedorDto.getNomeEmpresa());
            pstm.setString(2, objFornecedorDto.getNomeFornecedor());
            pstm.setInt(3, objFornecedorDto.getCnpj());
            pstm.setDouble(4, objFornecedorDto.getTelefone());
            pstm.setString(5, objFornecedorDto.getNomeEntregador());

            pstm.execute();
            pstm.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar fornecedor: " + e.getMessage());
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar conexão: " + e.getMessage());
            }
        }
    }
    
    public ArrayList<CadFornecedorDTO> listarFornecedores() throws ClassNotFoundException {
        String sql = "SELECT * FROM fornecedor";

        ArrayList<CadFornecedorDTO> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = new ConexaoDAO().conectaBD();
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                CadFornecedorDTO objFornecedorDto = new CadFornecedorDTO();
                objFornecedorDto.setNomeEmpresa(rs.getString("nome_empresa"));
                objFornecedorDto.setNomeFornecedor(rs.getString("nome_fornecedor"));
                objFornecedorDto.setTelefone(rs.getDouble("telefone"));      
                objFornecedorDto.setCnpj(rs.getInt("cnpj"));             // Vamos manter como INT mesmo? Podemos perder dados com CNPJ iniciados em 0
                objFornecedorDto.setNomeEntregador(rs.getString("entregador"));

              

                lista.add(objFornecedorDto);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar fornecedores: " + e.getMessage());
        } 
          

        return lista;
    }
}


