package controlador.SERVLET;

import controlador.SQL.SQLProcessData;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet(name = "Servlet_Details", urlPatterns = {"/Servlet_Details"})
public class Servlet_Details extends HttpServlet { 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String packcode = request.getParameter("pck_code");
        request.setAttribute("pack_code", packcode);
        request.setAttribute("pack_img", request.getParameter("pck_img"));
        request.setAttribute("pack_name", request.getParameter("pck_name"));
        request.setAttribute("pack_desc", request.getParameter("pck_desc"));
        request.setAttribute("pack_cost", request.getParameter("pck_cost"));
        request.setAttribute("pack_link", request.getParameter("pck_link"));
        
        SQLProcessData sql = new SQLProcessData();
        request.setAttribute("coments", sql.selectWhereFromMSComentario("*", "codanswer","=", 0, packcode));
        request.setAttribute("answers", sql.selectWhereFromMSComentario("*", "codanswer","!=", 0, packcode));
        request.setAttribute("votes", sql.selectAllFromVoto());
        
        RequestDispatcher rd=request.getRequestDispatcher("detalle_paquete.jsp");
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
