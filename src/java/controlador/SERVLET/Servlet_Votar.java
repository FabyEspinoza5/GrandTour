package controlador.SERVLET;

import controlador.SQL.SQLProcessData;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.usuario;
import modelo.voto;

@WebServlet(name = "Servlet_Votar", urlPatterns = {"/Servlet_Votar"})
public class Servlet_Votar extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        SQLProcessData sql = new SQLProcessData();
        HttpSession session = request.getSession();
        
        int coduser = ((usuario)session.getAttribute("user")).getCodigo();
        int codcoment = Integer.parseInt(request.getParameter("codcoment"));
        
        int suma = 0;
        sql.addVoto(new voto(codcoment, coduser, Integer.parseInt(request.getParameter("valor"))));
        List<voto> votos = sql.selectWhereFromVoto("*", "codcoment", codcoment);
        if (!votos.isEmpty()) {
            for (voto v : votos) {
                suma += v.getValor();
            }
            suma /= votos.size();
        }
        sql.updateVoteFromComentario(codcoment, suma);
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
