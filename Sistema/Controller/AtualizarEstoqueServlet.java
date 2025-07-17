package Controller;

import DAO.ProdutoDAO;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class AtualizarEstoqueServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idProduto = Integer.parseInt(request.getParameter("idProduto"));
        int novaQuantidade = Integer.parseInt(request.getParameter("novaQuantidade"));

        try {
            ProdutoDAO dao = new ProdutoDAO();
            dao.atualizarEstoquePorId(idProduto, novaQuantidade);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
