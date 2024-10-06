<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Giỏ hàng</title>
		<%@include file="/WEB-INF/jspf/style.jspf" %>
    </head>

    <body>
		<%@include file="/WEB-INF/jspf/navbar.jspf" %>

		<main class="container-fluid container-lg my-5 border p-5 rounded bg-body-tertiary">
			<div class="d-flex justify-content-between">
				<h1 class="h2 mb-0">GIỎ HÀNG</h1>
				<div class="d-flex gap-2 align-items-center">
					<h2 class="h3 mb-0">TỔNG TIỀN:</h2>
					<h2 class="h3 mb-0" id="total">
						<fmt:formatNumber value="${total}" groupingUsed="true"/> VNĐ
					</h2>
					<a href="/sneaker/order" class="btn btn-success">Đặt hàng</a>
				</div>
			</div>
			<hr>
			<table class="table table-bordered">
				<thead>
					<tr class="row row-cols-4">
						<th class="col col-5">Sản phẩm</th>
						<th class="col col-3">Số lượng</th>
						<th class="col col-3">Tổng tiền</th>
						<th class="col col-1 text-center">Xoá</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${carts}" var="cart">
						<tr class="row row-cols-4">
							<th class="col col-5">
								<div class="d-flex w-100 gap-3">
									<img src="${cart.key.product.productImage}"
										 width="100px" height="100px"
										 class="rounded border"/>
									<a href="detail?productId=${cart.key.product.productId}"
									   class="text-decoration-none text-dark fw-normal d-flex flex-column justify-content-between">
										<p class="d-block m-0 p-0">${cart.key.product.productName}</p>
										<p class="d-block m-0 p-0">Cỡ giày: ${cart.key.size.eu}</p>
										<p class="d-block m-0 p-0">
											Giá: <fmt:formatNumber value="${cart.key.product.price}" groupingUsed="true"/> VND
										</p>
									</a>
								</div>
							</th>
							<td class="col col-3 d-flex align-items-center">
								<form class="input-group" action="cart" method="post">
									<input type="number" name="quantity" value="${cart.value}" min="1"
										   class="form-control bg-body-tertiary" id="inputQuantity">
									<input type="hidden" name="productDetailId" value="${cart.key.productDetailId}">
									<button type="submit" name="submit" value="update"
											class="btn btn-primary">
										<i class="bi bi-arrow-clockwise"></i>
									</button>
								</form>
							</td>
							<td class="col col-3 d-flex align-items-center">
								<h4>
									<fmt:formatNumber value="${cart.key.product.price * cart.value}" groupingUsed="true"/> VNĐ
								</h4>
							</td>
							<td class="col col-1 d-flex align-items-center">
								<form class="w-100" action="cart" method="post">
									<input type="hidden" name="productDetailId" value="${cart.key.productDetailId}">
									<button type="submit" name="submit" value="delete"
											class="btn btn-danger w-100">
										<i class="bi bi-trash"></i>
									</button>
								</form>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</main>

		<%@include file="/WEB-INF/jspf/footer.jspf" %>
		<%@include file="/WEB-INF/jspf/script.jspf" %>
		<script>

		</script>
	</body>
</html>
