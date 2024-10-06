<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
		<%@include file="/WEB-INF/jspf/style.jspf"%>
    </head>
    <body>
		<%@include file="/WEB-INF/jspf/navbar.jspf"%>

		<main class="container-fluid container-lg my-5">
			<div class="d-flex justify-content-center">
				<form action="/sneaker/account/login" method="post"
					  class="bg-body-tertiary border rounded p-3"
					  style="width: 500px">
					<h1 class="fw-bold text-center">LOGIN</h1>
					<h5 class="text-danger text-center fw-bold">${message}</h5>
					<hr class="my-3">

					<div class="mb-3">
						<label for="email" class="form-label">Email</label>
						<div class="input-group">
							<i class="bi bi-envelope input-group-text"></i>
							<input type="email" id="email" name="email" required
								   placeholder="example@email.com"
								   class="form-control">
						</div>
						<span class="form-text">Enter your email address</span>
					</div>

					<div class="mb-3">
						<label for="password" class="form-label">Password</label>
						<div class="input-group">
							<i class="bi bi-shield-lock input-group-text"></i>
							<input type="password" id="password" name="password" required
								   placeholder="* * * * * *"
								   class="form-control">
						</div>
						<span class="form-text">Enter your password</span>
					</div>

					<input type="submit" name="login" value="Login"
						   class="btn btn-primary w-100 mb-3"/>

					<a href="/sneaker/account/signup" class="btn btn-outline-success w-100">SignUp</a>
				</form>
			</div>
		</main>

		<%@include file="/WEB-INF/jspf/footer.jspf" %>
		<%@include file="/WEB-INF/jspf/script.jspf"%>
</html>
