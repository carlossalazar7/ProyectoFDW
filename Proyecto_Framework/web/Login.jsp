<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
	<link rel="stylesheet" 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="css/style.css">

</head>
<body>
<div class="bg-img-login">
	<br>
	<br>
	<br>
	<br>
	<form name="form" action="<%=request.getContextPath()%>/LoginServlet"
		method="post">
		<div class=" bg-light w-50 mx-auto">
			<br>
			<h1 class="text-center">Login</h1>
			<div class="form-group w-75 mx-auto">
				<label for="exampleInputEmail1">Usuario</label> <input type="text"
					class="form-control" name="username" aria-describedby="emailHelp" required
					placeholder="ingrese su usuario">
				<!-- <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>  -->
			</div>
			<div class="form-group w-75 mx-auto">
				<label for="exampleInputPassword1">Contraseña</label> <input required
					type="password" class="form-control" name="password"
					placeholder="contraseña">
			</div>
			<div class="form-group form-check w-75 mx-auto">
				<label class="form-check-label " for="exampleCheck1"><a href="<%=request.getContextPath()%>/reperar.jsp">Recuperar Contraseña</a></label>
				<label class="form-check-label " for="exampleCheck1"><a href="<%=request.getContextPath()%>/UserController?op=nuevo2">Crear cuenta</a></label>
			</div>

			<div class="form-group mx-auto text-center">
				<span style="color: red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span>
				<br>
			</div>
			<div class="form-group mx-auto text-center">
				<button type="submit" class="btn btn-primary " value="Login">Entrar</button>
				<br>
			</div>
			<br>

		</div>
	</form>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	</div>
</body>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
		integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
		integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
		crossorigin="anonymous"></script>
</html>