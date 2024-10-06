<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Detail</title>
		<%@include file="/WEB-INF/jspf/style.jspf" %>
    </head>
    <body>
		<%@include file="/WEB-INF/jspf/navbar.jspf" %>

		<main class="container-fluid container-lg my-5">
			<div class="row row-cols-1 row-cols-lg-2 g-5">
				<section class="col">
					<div class="rounded border p-2 bg-body-tertiary">
						<img src="${product.productImage}" class="w-100 rounded-1 border"/>
					</div>
				</section>
				<section class="col">
					<h2>${product.productName}</h2>
					<h3 class="text-danger">
						<fmt:formatNumber value="${product.price}" groupingUsed="true"/> VND
					</h3>
					<hr class="border-5 rounded">
					<div class="d-flex justify-content-between align-items-center">
						<div class="d-flex flex-column justify-content-between">
							<p><b>Loại sản phẩm:</b> ${product.lineUp.category.categoryName}</p>
							<p><b>Thương hiệu:</b> ${product.lineUp.brand.brandName}</p>
							<p><b>Dòng giày:</b> ${product.lineUp.lineUpName}</p>
							<p><b>Trạng thái:</b> ${productDetails.isEmpty() ? "Hết hàng" : "Còn hàng"}</p>
						</div>
						<div class="p-2 border rounded bg-body-tertiary">
							<img src="${product.lineUp.brand.brandImage}" height="150px" class="border rounded-1"/>
						</div>
					</div>
					<hr class="border-5 rounded">
					<form action="cart" method="post" class="d-flex flex-column gap-3">
						<h6 class="mb-2">CỠ GIÀY:</h6>
						<div class="d-flex gap-3 flex-wrap">
							<c:forEach items="${productDetails}" var="productDetail" varStatus="status">
								<div class=" bg-body-tertiary p-2 border rounded-1">
									<div class="form-check">
										<input type="radio" name="productDetailId" value="${productDetail.productDetailId}"  ${status.index == 0 ? 'checked' : ''}
											   class="form-check-input"
											   id="size${productDetail.size.sizeId}">
										<label class="form-check-label" for="size${productDetail.size.sizeId}">
											${productDetail.size.eu}
										</label>
									</div>
								</div>
							</c:forEach>
						</div>
						<div class="input-group">
							<label for="inputQuantity" class="input-group-text bg-secondary text-light">Số lượng</label>
							<input type="number" name="quantity" value="1" min="1"
								   class="form-control bg-body-tertiary" id="inputQuantity">
							<i class="bi bi-123 input-group-text bg-secondary text-light"></i>
						</div>
						<hr class="border-5 rounded">
						<button type="submit" name="submit" value="add"
								class="btn btn-dark">
							Thêm vào giỏ hàng
						</button>
					</form>
				</section>
			</div>
		</main>

		<%@include file="/WEB-INF/jspf/footer.jspf" %>
		<%@include file="/WEB-INF/jspf/script.jspf" %>
    </body>
</html>
