<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles.css"/>
        <!--Bootstrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
        <!------------->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
    </head>
    <body class="body3">
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
        <div class="sticky-ref">Christian Valera Murillo Design</div>
    </body>
</html>
