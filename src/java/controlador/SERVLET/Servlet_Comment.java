package controlador.SERVLET;

import controlador.SQL.SQLProcessData;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.comentario;
import modelo.usuario;

@WebServlet(name = "Servlet_Comment", urlPatterns = {"/Servlet_Comment"})
public class Servlet_Comment extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        SQLProcessData sql = new SQLProcessData();
        
        usuario user = (usuario)session.getAttribute("user");
        String codpack = request.getParameter("codigo");
        String comentario = request.getParameter("comentario");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime now = LocalDateTime.now();
        int codresp = 0;
        if (request.getParameter("codcoment") != null) {
            codresp = Integer.parseInt(request.getParameter("codcoment"));
        }
        comentario c = new comentario(0, codpack, user.getNombre(), comentario, dtf.format(now), codresp, 0);
        sql.addComentario(c);
        
        String url = request.getParameter("refreshURL");
        response.sendRedirect(url);
        
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
