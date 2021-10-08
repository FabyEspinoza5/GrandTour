<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="controlador.*"%>
<%@page import="modelo.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--Bootstrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
        <!------------->
        <title>GrandTour</title>
    </head>
    <body>
        <!------------------------------------------------------Christian Valera Murillo Design-------------------------------------------------------->
        <section style="position: fixed; width: 100%; z-index: 1;">
            <header class="header">
                <nav class="navbar-light navbar header" style="background-color: #067774;">
                    <div class="btn-menu">
                        <label class="button_menu" for="btn-menu">☰</label>	
                        <a style="color: white;" class="navbar-brand">GrandTour</a>
                    </div>
                    <div style="display:flex;">
                        <c:if test="${user == null}">
                            <a style="margin-right: 20px;" class="btn btn-primary" href="http://localhost:8080/Turismo/login.jsp" role="button">INICIAR SESIÓN</a>
                        </c:if>
                        <c:if test="${user != null}">
                            <a style="margin-right: 20px;" class="btn btn-primary" href="http://localhost:8080/Turismo/Servlet_CloseSession" role="button">CERRAR SESIÓN</a>
                            <div class="cuenta">USUARIO: ${user.getNombre()}</div>
                        </c:if>
                    </div>
                </nav>
                
            </header>
            <div class="capa"></div>
            <input type="checkbox" id="btn-menu">
            <div class="container-menu">
                <div class="cont-menu">
                    <nav>
                        <a href="http://localhost:8080/Turismo/inicio.jsp" class="nav-main__link-item"><i class="fas fa-user-cog"></i>INICIO</a>
                        <a href="http://localhost:8080/Turismo/Servlet_Filter" class="nav-main__link-item"><i class="fas fa-shopping-cart"></i>PAQUETES</a>
                        <c:if test="${user != null}">
                            <a href="#" class="nav-main__link-item"><i class="fas fa-shopping-bag"></i>MOCHILA</a>
                            <a href="#" class="nav-main__link-item"><i class="fas fa-exclamation-circle"></i>MI CUENTA</a>
                        </c:if>
                    </nav>
                    <label for="btn-menu">✖️</label>
                </div>
            </div>
        </section>
        <!--------------------------------------------------------------------------------------------------------------------------------------------->
        <script>
            //Se accedió a la página navegando en el historial.
            if(performance.navigation.type === 2){
                location.reload(true);
            }
        </script>
        <a style="margin: 100px 23px 23px 23px;" class="btn btn-primary" href="http://localhost:8080/Turismo/Servlet_Filter" role="button">RESET PROJECT</a>
        <section style="display:flex;">
            <div style="margin-left: 10px;">
                <form method="get" action="Servlet_Filter">
                    <%!String a;%>
                    <%!String b;%>
                    <%!String c;%>
                    <%!String d;%>
                    <%!String e;%>
                    <label>Continente:</label>
                    <%!String selected_continent = "";%>
                    <%
                        if (request.getAttribute("continent_option") != null) {
                            selected_continent = request.getAttribute("continent_option").toString(); 
                        }
                    %>
                    <select name="index_continentes" onchange="form.submit()">
                        <option value="" <%if (selected_continent.equals("")) {%><%="selected"%><%}%>>Recomendados</option>
                        <c:forEach var="continent" items="<%=(List<String>)request.getAttribute("continent_list")%>">
                            <%
                                a = (String)pageContext.getAttribute("continent");
                                b = Support_Functions.textToAscii(a);
                                c = Support_Functions.asciiToText(b);
                                d = Support_Functions.rit(c, '_', ' ');
                                e = Support_Functions.toUpperEachFirstChar(d);
                            %>
                            <option value="<%=b%>" <%if (selected_continent.equals(b)) {%><%="selected"%><%}%>><%=e%></option>
                        </c:forEach>
                    </select>
                    <label>País: </label>
                    <%!String selected_country = "";%>
                    <%
                        if (request.getAttribute("country_option") != null) {
                            selected_country = request.getAttribute("country_option").toString();
                        }
                    %>
                    <select name="index_paises" onchange="form.submit()">
                        <option value="" <%if (selected_country.equals("")) {%><%="selected"%><%}%>>Seleccionar</option>
                        <c:forEach var="pais" items="<%=(List<String>)request.getAttribute("country_list")%>">
                            <%
                                a = (String)pageContext.getAttribute("pais");
                                b = Support_Functions.textToAscii(a);
                                c = Support_Functions.asciiToText(b);
                                d = Support_Functions.rit(c, '-', ' ');
                                e = Support_Functions.toUpperEachFirstChar(d);
                            %>
                            <option value="<%=b%>" <%if (selected_country.equals(b)) {%><%="selected"%><%}%>><%=e%></option>
                        </c:forEach>
                    </select>
                    <label>Lugares: </label>
                    <%!String selected_place = "";%>
                    <%
                        if (request.getAttribute("place_option") != null) {
                            selected_place = request.getAttribute("place_option").toString();
                        }
                    %>
                    <select name="index_lugares" onchange="form.submit()">
                        <option value="" <%if (selected_place.equals("")) {%><%="selected"%><%}%>>Seleccionar</option>
                        <c:forEach var="lugar" items="<%=(List<String>)request.getAttribute("place_list")%>">
                            <%
                                a = (String)pageContext.getAttribute("lugar");
                                b = Support_Functions.textToAscii(a);
                                c = Support_Functions.asciiToText(b);
                                d = Support_Functions.rit(c, '-', ' ');
                                e = Support_Functions.toUpperEachFirstChar(d);
                            %>
                            <option value="<%=b%>" <%if (selected_place.equals(b)) {%><%="selected"%><%}%>><%=e%></option>
                        </c:forEach>
                    </select>
                </form>
            </div>
        </section>
        <table style="margin: 0 auto; margin-top: 30px;">
            <%for(paquete pack : (List<paquete>)request.getAttribute("packListToShow")){%>
                <tr>
                    <th>
                        <div class="capsula1 card mb-3" style="max-width: 740px; max-height: 243px;">
                            <div style="" class="row g-0">
                                <div style="width:242px; height:242px; border-radius:.25rem 0px 0px .25rem" class="col-md-4">
                                    <img style="" src="<%=pack.getImg()%>" width="100%" height="100%" class="rounded-start">
                                </div>
                                <div class="col-md-8">
                                    <div class="card-body">
                                        <h5 style="font-size: 17px;"><%=pack.getName()%></h5>
                                        <p style="font-size: 13px;"><%=pack.getDescription()%></p>
                                        <p style="float:left; padding-top: 10px;" class="card-text">
                                            <small class="text-muted">Precio por día: S/ <%=pack.getCost()%></small>
                                        </p>
                                        <form action="Servlet_Details" method="get">
                                            <input type="hidden" name="pck_code" value="<%=pack.getCodigo()%>">
                                            <input type="hidden" name="pck_img" value="<%=pack.getImg()%>">
                                            <input type="hidden" name="pck_name" value="<%=Support_Functions.textToAscii(pack.getName())%>">
                                            <input type="hidden" name="pck_desc" value="<%=Support_Functions.textToAscii(pack.getDescription())%>">
                                            <input type="hidden" name="pck_cost" value="<%=pack.getCost()%>">
                                            <input type="hidden" name="pck_link" value="<%=pack.getLink()%>">
                                            <input style="float:right;margin-top:10px;" class="btn btn-primary textoprod" type="submit" value="Detalles">
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </th>
                </tr>
            <%}%>
        </table>
        <div class="sticky-ref">Christian Valera Murillo Design</div>     
    </body>
</html>

