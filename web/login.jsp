<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--Bootstrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
        <!------------->
        <title>Login</title>
    </head>
    <body class="body1">
        <!------------------------------------------------------Christian Valera Murillo Design-------------------------------------------------------->
        <section style="position: fixed; width: 100%; z-index: 1;">
            <header class="header">
                <nav class="navbar-light navbar header" style="background-color: #067774;">
                    <div class="btn-menu">
                        <label class="button_menu" for="btn-menu">☰</label>	
                        <a style="color: white;" class="navbar-brand">GrandTour</a>
                    </div>
                    <div style="display:flex;">
                        <c:if test="${user != ''}">
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
                    </nav>
                    <label for="btn-menu">✖️</label>
                </div>
            </div>
        </section>
        <!--------------------------------------------------------------------------------------------------------------------------------------------->
        <section class="vh-100">
            <div class="container-fluid h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col-md-9 col-lg-6 col-xl-6" style="margin-left: -20px;">
                        <img src="https://mdbootstrap.com/img/Photos/new-templates/bootstrap-login-form/draw2.png" class="img-fluid" alt="Sample image">
                    </div>
                    <div class="col-md-6 col-lg-6 col-xl-4 offset-xl-0 form">
                        <form class="formu" method="post" action="Servlet_Login">
                            <%String registered = "";%>
                            <%
                                if (request.getAttribute("registered") != null) {
                                    registered = request.getAttribute("registered").toString();
                                }
                            %>
                            <p style="margin-bottom: 12px;"><%=registered%></p>
                            <h2 style="margin-bottom: 50px;">Login</h2>
                            <!-- Email input -->
                            <%String foundEmail = "";%>
                            <%
                                if (request.getAttribute("foundEmail") != null) {
                                    foundEmail = request.getAttribute("foundEmail").toString();
                                }
                            %>
                            <div class="form-outline mb-4">
                                <input type="email" name="email" id="form3Example3" class="form-control form-control-lg" placeholder="Enter a valid email address" required/>
                                <label style="display:flex;" class="form-label" for="form3Example3">Email address<div style="margin-left: 8px; color:red"><%=foundEmail%></div></label>
                            </div>
                            <!-- Password input -->
                            <%String correctPassword = "";%>
                            <%
                                if (request.getAttribute("correctPassword") != null) {
                                    correctPassword = request.getAttribute("correctPassword").toString();
                                }
                            %>
                            <div class="form-outline mb-3">
                                <input type="password" name="password" id="form3Example4" class="form-control form-control-lg" placeholder="Enter password" required/>
                                <label style="display:flex;" class="form-label" for="form3Example4">Password<div style="margin-left: 8px; color:red"><%=correctPassword%></div></label>
                            </div>
                            <div class="text-center text-lg-start mt-4 pt-2">
                                <button type="submit" class="btn btn-primary btn-lg" style="padding-left: 2.5rem; padding-right: 2.5rem;">Login</button>
                                <p class="small fw-bold mt-2 pt-1 mb-0">Don't have an account? <a href="http://localhost:8080/Turismo/register.jsp" class="link-danger">Register</a></p>
                                <a href="http://localhost:8080/Turismo/login.jsp" class="link-danger">reset</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
