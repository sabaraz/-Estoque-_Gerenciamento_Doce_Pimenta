package Controller;

import DAO.LoginDAO;
import DTO.LoginDTO;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        try {
            LoginDAO loginDAO = new LoginDAO();
            LoginDTO usuarioAutenticado = loginDAO.autenticarUsuario(login, senha);

            if (usuarioAutenticado != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuarioAutenticado);
                response.sendRedirect("home.html"); // ou uma página JSP
            } else {
                response.sendRedirect("login.html?erro=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.html?erro=2");
        }
    }
}
