<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sản phẩm</title>
		<%@include file="/WEB-INF/jspf/style.jspf" %>
    </head>

    <body>
		<%@include file="/WEB-INF/jspf/navbar.jspf" %>

		<div class="container-fluid container-lg my-5">
			<form action="/sneaker/product" method="get"
				  class="col-12 d-flex flex-column gap-4">
				<section class="d-flex gap-2">
					<div class="input-group">
						<i class="bi bi-search input-group-text"></i>
						<input type="search" name="productName" value="${productName}"
							   placeholder="Tìm kiếm sản phẩm"
							   class="form-control">
					</div>
					<div class="input-group">
						<i class="bi bi-tags input-group-text"></i>
						<select class="form-select" name="categoryId">
							<option value="0">Category</option>
							<c:forEach items="${categories}" var="category">
								<option value="${category.categoryId}" ${categoryId == category.categoryId ? "selected" : ""}>
									${category.categoryName}
								</option>
							</c:forEach>
						</select>
					</div>
					<div class="input-group">
						<i class="bi bi-patch-check input-group-text"></i>
						<select class="form-select" name="brandId">
							<option value="0">Brand</option>
							<c:forEach items="${brands}" var="brand">
								<option value="${brand.brandId}" ${brandId == brand.brandId ? "selected" : ""}>
									${brand.brandName}
								</option>
							</c:forEach>
						</select>
					</div>
					<div class="input-group">
						<i class="bi bi-eyedropper input-group-text"></i>
						<select class="form-select" name="colorId">
							<option value="0">Color</option>
							<c:forEach items="${colors}" var="color">
								<option value="${color.colorId}" ${colorId == color.colorId ? "selected" : ""}>
									${color.colorName}
								</option>
							</c:forEach>
						</select>
					</div>
					<button type="submit" class="btn btn-danger">Filter</button>
				</section>

				<hr class="m-0 p-0 border border-5 rounded border-secondary">

				<section>
					<div class="row row-cols-2 row-cols-sm-2 row-cols-md-3 row-cols-lg-3 row-cols-xl-4 g-4">
						<c:forEach items="${products}" var="product">
							<a href="detail?productId=${product.productId}" class="col text-decoration-none">
								<div class="card bg-body-tertiary p-2 h-100">
									<img src="${product.productImage}" class="card-img-top rounded border">
									<div class="card-body h-100 d-flex flex-column justify-content-between">
										<h5 class="card-title">${product.productName}</h5>
										<h5 class="card-text text-danger">
											<fmt:formatNumber value="${product.price}" type="number" groupingUsed="true"/> VND
										</h5>
									</div>
								</div>
							</a>
						</c:forEach>
					</div>
				</section>

				<hr class="m-0 p-0 border border-5 rounded border-secondary">

				<nav>
					<ul class="pagination justify-content-center">
						<li class="page-item">
							<button type="submit" name="page" value="${page > 1 ? (page - 1) : 1}"
									class="page-link bg-secondary text-light">
								<i class="bi bi-arrow-left"></i>
							</button>
						</li>
						<c:forEach begin="1" end="${pages}" varStatus="status">
							<li class="page-item">
								<button type="submit" name="page" value="${status.index}"
										class="page-link ${page == status.index ? "active" : ""}">
									${status.index}
								</button>
							</li>
						</c:forEach>
						<li class="page-item">
							<button type="submit" name="page" value="${page + 1}"
									class="page-link bg-secondary text-light">
								<i class="bi bi-arrow-right"></i>
							</button>
						</li>
					</ul>
				</nav>
			</form>
		</div>

		<%@include file="/WEB-INF/jspf/footer.jspf" %>
		<%@include file="/WEB-INF/jspf/script.jspf" %>
	</body>
</html>