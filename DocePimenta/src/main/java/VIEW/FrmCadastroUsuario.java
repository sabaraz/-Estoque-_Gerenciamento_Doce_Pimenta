package VIEW;

import DAO.UsuarioDAO;
import DTO.UsuarioDTO;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class FrmCadastroUsuario extends javax.swing.JFrame {

    public FrmCadastroUsuario() {
        initComponents();
        setTitle("Cadastro de Usuário");
        setLocationRelativeTo(null);
    }
public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(() -> {
        new FrmCadastroUsuario().setVisible(true);
    });
}
    private void cadastrarUsuario() {
        String nome = txtNome2.getText();
        String senha = new String(txtSenha2.getPassword());

        if (nome.isEmpty() || senha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
            return;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setNome_usuario(nome);
        usuarioDTO.setSenha_usuario(senha);

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        usuarioDAO.cadastrarUsuario(usuarioDTO);

        JOptionPane.showMessageDialog(this, "Usuário cadastrado com sucesso!");
        txtNome2.setText("");
        txtSenha2.setText("");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtNome2 = new javax.swing.JTextField();
        txtSenha2 = new javax.swing.JPasswordField();

        jFormattedTextField1.setText("jFormattedTextField1");

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(255, 204, 204));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setBackground(new java.awt.Color(255, 102, 102));
        jLabel1.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 102, 102));
        jLabel1.setText("Cadastro de Usuário");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, -1));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nome do Usuário");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, -1, -1));

        jButton1.setText("Cadastrar");
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 90, 30));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Senha");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, -1, -1));

        txtNome2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNome2ActionPerformed(evt);
            }
        });
        jPanel1.add(txtNome2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 220, -1));

        txtSenha2.setText("jPasswordField1");
        jPanel1.add(txtSenha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 200, -1));

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void txtNome2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNome2ActionPerformed
btnCadastrarActionPerformed(evt);
    }//GEN-LAST:event_txtNome2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtNome2;
    private javax.swing.JPasswordField txtSenha2;
    // End of variables declaration//GEN-END:variables

    private void btnCadastrarActionPerformed() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
