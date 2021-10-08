package controlador.SERVLET;

import controlador.SQL.SQLProcessData;
import controlador.Support_Functions;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.paquete;

@WebServlet(name = "Servlet_Filter", urlPatterns = {"/Servlet_Filter"})
public class Servlet_Filter extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
        SQLProcessData sql = new SQLProcessData();
        List<paquete> packages = sql.selectSomethingFromPaquete("*");//Cargando lista de paquetes con todo
        request.setAttribute("continent_list", sql.simpleDistinctSelectLugares("continente"));//Cargando lista de continentes con cada uno
        Object ic = request.getParameter("index_continentes");
        Object ip = request.getParameter("index_paises");
        Object il = request.getParameter("index_lugares");
        if (ic != null) {
            request.setAttribute("continent_option", ic);//Cargando continente seleccionado [Reciclar JSP]
            String icts = Support_Functions.asciiToText(ic.toString());
            String mainCode_continente = sql.getIndexCodeFromLugares("continente",icts);//Preparando indice para consulta
            if (!ic.toString().equals("")) {
                packages = sql.selectTwoSubstringsFromPaquete("*", "code", 2, 1, mainCode_continente, 1, 1);//Cargando todo paquete => continente
            }
            List<String> cli = sql.simpleDistinctWhereSelectLugares("pais", "continente", icts);
            request.setAttribute("country_list", cli);//Cargando lista de paises según continente [Reciclar JSP]
            for (String cnt : cli) {
                if (ip == null) break;
                String ipts = Support_Functions.asciiToText(ip.toString());
                if (cnt.equals(ipts)) {
                    request.setAttribute("country_option", ip);//Cargando país seleccionado [Reciclar JSP]
                    String mainCode_pais = sql.getIndexCodeFromLugares("pais",ipts);//Preparando indice para consulta
                    packages = sql.selectTwoSubstringsFromPaquete("*", "code", 2, 3, mainCode_pais, 1, 3);//Cargando todo paquete => país
                    List<String> pli = sql.simpleDistinctWhereSelectLugares("lugtur", "pais", ipts);
                    request.setAttribute("place_list", pli);//Cargando lista de lugares según país [Reciclar JSP]
                    for (String plc : pli) {
                        if (il == null) break;
                        String ilts = Support_Functions.asciiToText(il.toString());
                        if (plc.equals(ilts)) {
                            request.setAttribute("place_option", il);//Cargando lugar seleccionado [Reciclar JSP]
                            String mainCode_lugar = sql.getIndexCodeFromLugares("lugtur",ilts);//Preparando indice para consulta
                            packages = sql.selectTwoSubstringsFromPaquete("*", "code", 2, 5, mainCode_lugar, 1, 5);//Cargando todo paquete => lugares
                            break;
                        }
                    }
                    break;
                }
            }
        }
        request.setAttribute("packListToShow", packages);//Enviando paquete con lista final
        RequestDispatcher rd=request.getRequestDispatcher("main.jsp");
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
