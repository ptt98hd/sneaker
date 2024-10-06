<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP</title>
		<%@include file="/WEB-INF/jspf/style.jspf"%>
    </head>

    <body>
        <%@include file="/WEB-INF/jspf/navbar.jspf"%>

		<main class="container-fluid container-lg my-5">
			<div class="row row-cols-2">
				<div class="col col-8">
					<table class="table table-bordered">
						<thead>
							<tr class="row row-cols-3">
								<th class="col">Sản phẩm</th>
								<th class="col">Số lượng</th>
								<th class="col">Tổng tiền</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${carts}" var="cart">
								<tr class="row row-cols-3">
									<th class="col">
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
									<td class="col d-flex align-items-center">
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
									<td class="col d-flex align-items-center">
										<h4>
											<fmt:formatNumber value="${cart.key.product.price * cart.value}" groupingUsed="true"/> VNĐ
										</h4>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>

				<div class="col col-4">
					<form action="order" method="post" class="bg-body-tertiary border rounded p-3">
						<h1 class="text-center">Đặt hàng</h1>
						<hr>
						<label for="customerName" class="form-label">Tên khách hàng</label>
						<div class="input-group">
							<i class="bi bi-person input-group-text"></i>
							<input type="text" name="customerName"
								   placeholder="Ví dụ: Nguyễn Văn A"
								   class="form-control" id="customerName">
						</div>
					</form>
				</div>
			</div>
		</main>

		<%@include file="/WEB-INF/jspf/script.jspf"%>
    </body>
</html>
