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
				<form action="/sneaker/account/info" method="post"
					  class="bg-body-tertiary border rounded p-3"
					  style="width: 500px">
					<h1 class="fw-bold text-center">ACCOUNT</h1>
					<h6 class="text-danger text-center">${message}</h6>
					<hr class="my-3">

					<div class="mb-3">
						<label for="fullname" class="form-label">Fullname</label>
						<div class="input-group">
							<i class="bi bi-person input-group-text"></i>
							<input type="text" id="fullname" name="fullname" readonly
								   placeholder="${account.fullname}"
								   class="form-control">
						</div>
					</div>

					<div class="mb-3">
						<label for="email" class="form-label">Email</label>
						<div class="input-group">
							<i class="bi bi-envelope input-group-text"></i>
							<input type="email" id="email" name="email" readonly
								   placeholder="${account.email}"
								   class="form-control">
						</div>
					</div>

					<div class="mb-3">
						<label for="password" class="form-label">Password</label>
						<div class="input-group">
							<i class="bi bi-shield-lock input-group-text"></i>
							<input type="password" id="password" name="password" readonly
								   placeholder="${account.password}"
								   class="form-control">
						</div>
					</div>

					<input type="submit" name="login" value="Logout"
						   class="btn btn-danger w-100"/>
				</form>
			</div>
		</main>

		<%@include file="/WEB-INF/jspf/footer.jspf" %>
		<%@include file="/WEB-INF/jspf/script.jspf"%>
</html>
