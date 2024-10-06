package entity;

import java.sql.Date;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDetail {

	private int orderDetailId;
	private Order order;
	private ProductDetail productDetail;
	private int quantity;
	private double amount;
}
