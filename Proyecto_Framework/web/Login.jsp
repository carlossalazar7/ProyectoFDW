<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://java.sun.com/jsf/html"
      xmlns:f="http://java.sun.com/jsf/core"
      xmlns:ui="http://java.sun.com/jsf/facelets"
      xmlns:b="http://bootsfaces.net/ui"
      xmlns:p="http://primefaces.org/ui">
    <h:head>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous"/>
        <title>Facelet Title</title>
    </h:head>
    <h:body>
        <ui:composition template="./WEB-INF/plantillaboot.xhtml">
            <ui:define name="contenido">
                <link rel="stylesheet" href="css/login.css" />
                <link href="https://fonts.googleapis.com/css2?family=Anton&family=Indie+Flower" rel="stylesheet" />
                <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
                <h2 class="titulo">Universal Music </h2>
                <div class="container" id="container">
                    <div class="form-container sign-in-container">
                        <form action="<%=request.getContextPath()%>/LoginServlet" method="post">
                            <p:messages id="messages" showDetail="true" closable="true">
                                <p:autoUpdate />
                            </p:messages>
                            <h1>Sign in</h1>
                            <div class="social-container">
                                <a href="#" class="social"><i class="fab fa-facebook-f"></i></a>
                                <a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
                                <a href="#" class="social"><i class="fab fa-linkedin-in"></i></a>
                            </div>
                            <span>or use your account</span>
                            <input type="text" name="username" placeholder="Usuario" required="" />
                            <input type="password" name="password" placeholder="Password" required="" />
                            <button type="submit">Sign In</button>
                            <a href="recuperarContra.xhtml">Forgot your password?</a>
                        </form>
                    </div>
                    <div class="overlay-container">
                        <div class="overlay">

                            <div class="overlay-panel overlay-right">
                                <h1>Welcome Back!</h1>
                                <p>To keep connected with us please create with your personal info</p>
                                <button class="text-decoration-none bg-warning"><a href="NewUser.xhtml" >Crear Cuenta</a></button>
                            </div>
                        </div>
                    </div>
                </div>

                <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
            </ui:define>
        </ui:composition>
    </h:body>
</html>