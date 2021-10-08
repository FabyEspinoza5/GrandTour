package controlador.SERVLET;

import controlador.SQL.SQLProcessData;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.*;

@WebServlet(name = "Servlet_Login", urlPatterns = {"/Servlet_Login"})
public class Servlet_Login extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        SQLProcessData sql = new SQLProcessData();
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        usuario u = sql.selectSomethingWhereFromUsuario("*", "email", email);
        String redirectTo = "login.jsp";
        if (u != null) {
            if (u.getPassword().equals(password)) {
                redirectTo = "inicio.jsp";
                session.setAttribute("user", u);
            } else {
                request.setAttribute("correctPassword", "Clave incorrecta");
            }
        } else {
            request.setAttribute("foundEmail", "Direccion e-mail incorrecta");
        }
        RequestDispatcher rd=request.getRequestDispatcher(redirectTo);
        rd.forward(request, response);
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
