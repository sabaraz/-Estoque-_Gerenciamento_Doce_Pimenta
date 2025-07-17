package Controller;

import DAO.EntregadorDAO;
import DTO.CadEntregadorDTO;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class CadastrarEntregadorServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nomeEntregador = request.getParameter("nomeEntregador");
        String telefoneEntregador = request.getParameter("telefoneEntregador");
        String empresaTrabalha = request.getParameter("empresaTrabalha");

        CadEntregadorDTO entregadorDTO = new CadEntregadorDTO();
        entregadorDTO.setNomeEntregador(nomeEntregador);
        entregadorDTO.setTelefoneEntregador(telefoneEntregador);
        entregadorDTO.setEmpresaTrabalha(empresaTrabalha);

        try {
            EntregadorDAO entregadorDAO = new EntregadorDAO();
            entregadorDAO.cadastrarEntregador(entregadorDTO);
            response.sendRedirect("cadastro_sucesso.html");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("cadastro_entregador.html?erro=1");
        }
    }
}
