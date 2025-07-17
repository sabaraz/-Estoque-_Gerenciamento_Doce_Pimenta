package Controller;

import DAO.ItensVendidosDAO;
import DTO.ItensVendidosDTO;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class VendaServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String tipoProduto = request.getParameter("tipoProduto");
        String nomeProduto = request.getParameter("nomeProduto");
        String marca = request.getParameter("marca");
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        double precoUnitario = Double.parseDouble(request.getParameter("precoUnitario"));
        double desconto = Double.parseDouble(request.getParameter("desconto"));
        double imposto = Double.parseDouble(request.getParameter("imposto"));

        double subtotal = precoUnitario * quantidade;
        double total = subtotal - desconto + imposto;

        ItensVendidosDTO dto = new ItensVendidosDTO();
        dto.setTipoProduto(tipoProduto);
        dto.setNomeProduto(nomeProduto);
        dto.setMarca(marca);
        dto.setQuantidadeVendida(quantidade);
        dto.setPrecoUnitario(precoUnitario);
        dto.setSubtotalVenda(subtotal);
        dto.setDesconto(desconto);
        dto.setImposto(imposto);
        dto.setTotal(total);

        try {
            ItensVendidosDAO dao = new ItensVendidosDAO();
            dao.salvar(dto);
            response.sendRedirect("venda_sucesso.html");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("venda.html?erro=1");
        }
    }
}
