<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="styles.css"/>
        <!--Bootstrap-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ" crossorigin="anonymous"></script>
        <!------------->
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body class="body2">
        <!------------------------------------------------------Christian Valera Murillo Design-------------------------------------------------------->
        <section style="position: fixed; width: 100%; z-index: 1;">
            <header class="header">
                <nav class="navbar-light navbar header" style="background-color: #067774;">
                    <div class="btn-menu">
                        <label class="button_menu" for="btn-menu">☰</label>	
                        <a style="color: white;" class="navbar-brand">GrandTour</a>
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
        <section class="contsalv">
            <div class="capsula">
                <h2 style="margin-bottom: 50px;">Register</h2>
                <form method="get" action="Servlet_Register">
                    <!-- 2 column grid layout with text inputs for the first and last names -->
                    <div class="row mb-4">
                        <div class="col">
                            <div class="form-outline">
                                <input type="text" name="name" id="form3Example1" class="form-control" placeholder="Enter name" required/>
                                <label class="form-label" for="form3Example1">First name</label>
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-outline">
                                <input type="text" name="lastname" id="form3Example2" class="form-control" placeholder="Enter lastname" required/>
                                <label class="form-label" for="form3Example2">Last name</label>
                            </div>
                        </div>
                    </div>
                    <!-- Email input -->
                    <%String sameEmail = "";%>
                    <%
                        if (request.getAttribute("sameEmail") != null) {
                            sameEmail = request.getAttribute("sameEmail").toString();
                        }
                    %>
                    <div class="form-outline mb-4">
                        <input type="email" name="email" id="form3Example3" class="form-control" placeholder="Enter a valid email address" required/>
                        <label style="display:flex;" class="form-label" for="form3Example3">Email address<div style="margin-left: 8px; color:red"><%=sameEmail%></div></label>
                    </div>
                    <!-- Password input -->
                    <div class="form-outline mb-4">
                        <input type="password" name="password" id="form3Example4" class="form-control" placeholder="Enter password" required/>
                        <label class="form-label" for="form3Example4">Password</label>
                    </div>
                    <!-- Submit button -->
                    <button type="submit" class="btn btn-primary btn-block mb-4">Register account</button>
                    <a href="http://localhost:8080/Turismo/register.jsp" class="link-danger">reset</a>
                </form>
            </div>
        </section>
    </body>
</html>
