package controlador.SERVLET;

import controlador.SQL.SQLProcessData;
import controlador.Support_Functions;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.usuario;

@WebServlet(name = "Servlet_Register", urlPatterns = {"/Servlet_Register"})
public class Servlet_Register extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        SQLProcessData sql = new SQLProcessData();
        String a = request.getParameter("name")+" "+request.getParameter("lastname");
        String fullname = Support_Functions.toUpperEachFirstChar(a);
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String redirectTo = "register.jsp";
        if (sql.selectSomethingWhereFromUsuario("*", "email", email) != null) {
            request.setAttribute("sameEmail", "La direccion e-mail ya esta en uso");
        } else {
            usuario u = new usuario(0, email, fullname, password);
            sql.addUsuario(u);
            request.setAttribute("registered", "Registro Satisfactorio! Por favor inicie sesión");
            redirectTo = "login.jsp";
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
