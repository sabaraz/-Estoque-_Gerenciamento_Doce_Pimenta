package Controller;

import DAO.FornecedorDAO;
import DTO.CadFornecedorDTO;
import java.io.IOException;
import java.servlet.*;
import javax.servlet.http.*;


@WebServlet("/CadastrarFornecedorServlet")

public class CadastrarFornecedorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nomeEmpresa = request.getParameter("nomeEmpresa");
        String nomeFornecedor = request.getParameter("nomeFornecedor");
        int cnpj = Integer.parseInt(request.getParameter("cnpj"));
        double telefone = Double.parseDouble(request.getParameter("telefone"));
        String nomeEntregador = request.getParameter("nomeEntregador");

        CadFornecedorDTO fornecedorDTO = new CadFornecedorDTO();
        fornecedorDTO.setNomeEmpresa(nomeEmpresa);
        fornecedorDTO.setNomeFornecedor(nomeFornecedor);
        fornecedorDTO.setCnpj(cnpj);
        fornecedorDTO.setTelefone(telefone);
        fornecedorDTO.setNomeEntregador(nomeEntregador);

        try {
            FornecedorDAO fornecedorDAO = new FornecedorDAO();
            fornecedorDAO.cadastrarProduto(fornecedorDTO);
            response.sendRedirect("cadastro_sucesso.html");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("cadastro_fornecedor.html?erro=1");
        }
    }
}
