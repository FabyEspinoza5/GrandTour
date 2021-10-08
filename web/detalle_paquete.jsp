<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@page import="controlador.*"%>
<%@page import="modelo.*"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styles.css"/>
        <!--Bootstrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
        <!------------->
        <title>Detalles de Paquete</title>
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
        <section style="padding: 150px 0px 100px 90px;">
            <div style="border: 5px solid #067774; width:1200px; margin: 0px 0px 0px 50px; padding: 20px;">
                <h1><%=Support_Functions.asciiToText(request.getAttribute("pack_name").toString())%></h1><br>
                <img src="<%=request.getAttribute("pack_img").toString()%>" width="300px" height="300px">
                <br>
                <br>
                <h3><%=Support_Functions.asciiToText(request.getAttribute("pack_desc").toString())%></h3>
                <br>
                <h3>Precio por día: S/ <%=request.getAttribute("pack_cost").toString()%></h3>
                <br>
                <a class="btn btn-primary textoprod" target="blank" href="<%=request.getAttribute("pack_link").toString()%>">DETALLES</a>
            </div>
            <!--COMENTARIO FORMULARIO-->
            <c:if test="${user != null}">
                <div id="comentarioForm" style="border: 5px solid #067774; width:1200px; margin: -5px 0px 0px 50px; padding: 20px;">
                    <h3>Comentar</h3>
                    <form action="Servlet_Comment" method="post">
                        <div style="display: inline-block; background: #A7FFFB; padding: 5px;">${user.getNombre()}</div>
                        <input type="hidden" name="codigo" value="<%=request.getAttribute("pack_code").toString()%>">
                        <input type="hidden" name="refreshURL" value="<%="http://localhost:8080/Turismo/Servlet_Details?"+request.getQueryString()%>">
                        <div style="display:flex">
                            <textarea rows="4" cols="100" name="comentario" required></textarea>
                            <input style="margin: 60px 0px 0px 20px; height: 40px;" class="btn btn-primary textoprod" type="submit" value="PUBLICAR">
                        </div>
                    </form>
                </div>
            </c:if>
            <!------------------------->
            <!--COMENTARIO LISTA-->
            <%!int i;%>
            <%i = 0;%>
            <c:forEach var="com" items="<%=(List<comentario>)request.getAttribute("coments")%>">
                <div style="width:1200px; margin: -5px 0px 20px 50px;">
                    <div style="border: 5px solid #067774; padding: 20px;">
                        <div Style="display: flex;">
                            <p style="font-size: 20px;"><c:out value = "${com.getNomus()}"/></p>
                            <div style="padding-top: 4px; margin-left: auto; margin-right: 0;"><i>Fecha: <c:out value = "${com.getFecha()}"/></i></div>
                        </div>
                        <p style=" text-align: justify; border: 2px solid gray; padding: 15px;"><c:out value = "${com.getComent()}"/></p>
                        <div style="display: flex; margin-top: 12px;">
                            <c:if test="${com.getVoto() != 0}">
                                <div style="padding-top: 4px;"><i>Calificación:</i></div>
                            </c:if>
                            
                            <div style="display: flex;">
                                <c:forEach begin="1" end="${com.getVoto()}" var="s">
                                    <img src="https://png.pngtree.com/element_our/png_detail/20181206/star-vector-icon-png_260850.jpg" width="30px" height="30px">
                                    <c:if test="${s == com.getVoto()}">
                                        <div style="margin-right: 30px;"></div>
                                    </c:if>
                                </c:forEach>
                            </div>
                            <c:if test="${user != null}">
                                <%!boolean agregar;%>
                                <%
                                    agregar = true;
                                    List<voto> v = (List<voto>)request.getAttribute("votes");
                                %>
                                <%for (int j = 0; j < v.size(); j++) {%>
                                    <c:set var="codcomentario" value="<%=v.get(j).getCodcom()%>"/>
                                    <c:if test="${com.getCodcoment() == codcomentario}">
                                        <%
                                            if (v.get(j).getCodus() == ((usuario)session.getAttribute("user")).getCodigo()) {
                                                agregar = false;
                                                break;
                                            }
                                        %>
                                    </c:if>
                                <%}%>
                                <%if(agregar) {%>
                                    <div style="padding-top: 4px;"><i>Califique:</i></div>
                                    <div style="display: flex;">
                                        <form action="Servlet_Votar" method="post">
                                            <input type="hidden" name="refreshURL" value="<%="http://localhost:8080/Turismo/Servlet_Details?"+request.getQueryString()%>">
                                            <input type="hidden" name="codcoment" value="${com.getCodcoment()}">
                                            <c:forEach begin="1" end="5" var="s">
                                                <label class="lab">
                                                    <input class="stars" type="radio" id="${s}" name="valor" value="${s}" onclick="this.form.submit();">
                                                    <img style="cursor: pointer;" width="26px" height="26px" src="https://media.istockphoto.com/vectors/star-icon-star-shape-symbol-of-award-decoration-quality-rating-etc-vector-id1082898672?s=612x612">
                                                </label>
                                            </c:forEach>
                                        </form>
                                    </div>
                                <%} else {%>
                                    <p style="padding-top: 4px;">Gracias por valorar el comentario.</p>
                                <%}%>
                                <div style="height: 40px; margin-left: auto; margin-right: 0;">
                                    <button style="" class="btn btn-primary textoprod" onclick="answer(<%=i%>)">RESPONDER</button>
                                </div>
                                <%i++;%>
                            </c:if>
                        </div>
                    </div>
                    <!--RESPUESTA FORMULARIO-->
                    <div style="margin-left: 52px;">
                        <div name="respuesta" style="display: none; border: 5px solid #067774; padding: 20px; margin-top: -5px;">
                            <div style="display:flex">
                                <h4>Responder a:&nbsp</h4>
                                <p style="font-size: 20px;"><c:out value = "${com.getNomus()}"/></p>
                            </div>
                            <form action="Servlet_Comment" method="post">
                                <div style="display: inline-block; background: #A7FFFB; padding: 5px;">${user.getNombre()}</div>
                                <input type="hidden" name="codigo" value="<%=request.getAttribute("pack_code").toString()%>">
                                <input type="hidden" name="codcoment" value="${com.getCodcoment()}">
                                <input type="hidden" name="refreshURL" value="<%="http://localhost:8080/Turismo/Servlet_Details?"+request.getQueryString()%>">
                                <div style="display:flex">
                                    <textarea rows="4" cols="100" name="comentario"></textarea>
                                    <input style="margin: 60px 0px 0px 20px; height: 40px;" class="btn btn-primary textoprod" type="submit" value="PUBLICAR">
                                </div>
                            </form>
                        </div>
                    </div>
                    <!------------------------>
                    <!--RESPUESTA LISTA-->
                    <c:forEach var="ans" items="<%=(List<comentario>)request.getAttribute("answers")%>">
                        <c:if test="${ans.getCodanswer() == com.getCodcoment()}">
                            <div style="margin-left: 52px;">
                                <div style="border: 5px solid #067774; padding: 20px; margin-top: -5px;">
                                    <div Style="display: flex;">
                                        <p style="font-size: 20px;"><c:out value = "${ans.getNomus()}"/></p>
                                        <div style="padding-top: 4px; margin-left: auto; margin-right: 0;"><i>Fecha: <c:out value = "${ans.getFecha()}"/></i></div>
                                    </div>
                                    <p style=" text-align: justify; border: 2px solid gray; padding: 15px;">
                                        <mark style="background: #C9E0DE">@<c:out value = "${com.getNomus()}"/></mark>&nbsp<c:out value = "${ans.getComent()}"/>
                                    </p>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                    <!------------------->
                </div>            
            </c:forEach>
            <!-------------------->
        </section>
        <div class="sticky-ref">Christian Valera Murillo Design</div>
        
        <script>
            function answer(n){
                var r = document.getElementsByName("respuesta");
                for (var i = 0; i < r.length; i++) {
                    r[i].style.display = "none";
                    if (i === n) {
                        r[n].style.display = "block";
                    }
                }
            };
        </script>
    </body>
</html>
