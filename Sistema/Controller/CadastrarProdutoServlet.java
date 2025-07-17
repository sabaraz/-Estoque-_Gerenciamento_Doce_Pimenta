package Controller;

import DAO.ProdutoDAO;
import DTO.CadProdutoDTO;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class CadastrarProdutoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int codProduto = Integer.parseInt(request.getParameter("codProduto"));
        String nomeProduto = request.getParameter("nomeProduto");
        String marcaProduto = request.getParameter("marcaProduto");
        String fornecedorProd = request.getParameter("fornecedorProd");
        int quantProd = Integer.parseInt(request.getParameter("quantProd"));
        double precoVenda = Double.parseDouble(request.getParameter("precoVenda"));
        String tipoProduto = request.getParameter("tipoProduto");

        CadProdutoDTO produtoDTO = new CadProdutoDTO();
        produtoDTO.setCodProduto(codProduto);
        produtoDTO.setNomeProduto(nomeProduto);
        produtoDTO.setMarcaProduto(marcaProduto);
        produtoDTO.setFornecedorProd(fornecedorProd);
        produtoDTO.setQuantProd(quantProd);
        produtoDTO.setPrecoVenda(precoVenda);
        produtoDTO.setTipoProduto(tipoProduto);

        try {
            ProdutoDAO produtoDAO = new ProdutoDAO();
            produtoDAO.cadastrarProduto(produtoDTO);
            response.sendRedirect("lista_produtos.html"); // ou uma página de sucesso
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("cadastro_produto.html?erro=1");
        }
    }
}
