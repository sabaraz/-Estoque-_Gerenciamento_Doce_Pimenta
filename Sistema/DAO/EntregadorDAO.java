package DAO;

import DTO.CadEntregadorDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class EntregadorDAO {
    
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<CadEntregadorDTO> lista = new ArrayList<>();

    public void cadastrarEntregador(CadEntregadorDTO objEntregadorDto) throws ClassNotFoundException {
        String sql = "INSERT INTO entregador (nome, telefone, id_fornecedor) "
                    + "VALUES (?,?,?)";

        conn = new ConexaoDAO().conectaBD();
        
                try {

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objEntregadorDto.getNomeEntregador());
            pstm.setString(2, objEntregadorDto.getTelefoneEntregador());
            pstm.setString(3, objEntregadorDto.getEmpresaTrabalha());

            pstm.execute();
            pstm.close();

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "ProdutoDAO" + e);

        }
    }
}