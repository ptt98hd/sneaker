
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trang chủ</title>
		<%@include file="WEB-INF/jspf/style.jspf" %>
    </head>

    <body>
		<%@include file="WEB-INF/jspf/navbar.jspf" %>
		<%@include file="WEB-INF/jspf/heroes.jspf" %>

		<main class="my-5">
			<div class="container-fluid container-lg d-flex flex-column gap-5">
				<section>
					<h1 class="text-center">DANH MỤC SẢN PHẨM</h1>
					<hr class="border-5 rounded">
					<div class="row row-cols-3 g-4">
						<c:forEach items="${categories}" var="category">
							<a href="" class="col text-decoration-none">
								<div class="card p-2 bg-body-tertiary">
									<img src="${category.categoryImage}" class="card-img-top border rounded-1">
								</div>
							</a>
						</c:forEach>
					</div>
				</section>

				<section>
					<h1 class="text-center">DÒNG SẢN PHẨM</h1>
					<hr class="border-5 rounded">
					<div class="row row-cols-2 row-cols-md-3 row-cols-xl-4 g-4">
						<c:forEach items="${lineUps}" var="lineUp">
							<a href="" class="col text-decoration-none">
								<div class="card bg-body-tertiary p-2">
									<img src="${lineUp.lineUpImage}" class="card-img-top border rounded-1">
									<div class="card-body">
										<h5 class="card-title text-center">${lineUp.lineUpName}</h5>
									</div>
								</div>
							</a>
						</c:forEach>
					</div>
				</section>

				<section>
					<h1 class="text-center">THƯƠNG HIỆU</h1>
					<hr class="border-5 rounded">
					<div class="row row-cols-2 g-4">
						<c:forEach items="${brands}" var="brand">
							<a href="" class="col text-decoration-none">
								<div class="card p-2 bg-body-tertiary">
									<img src="${brand.brandImage}" class="card-img-top border rounded-1">
								</div>
							</a>
						</c:forEach>
					</div>
				</section>
			</div>
		</main>

		<%@include file="WEB-INF/jspf/footer.jspf" %>
		<%@include file="WEB-INF/jspf/script.jspf" %>
	</body>
</html>
