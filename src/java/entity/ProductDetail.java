package entity;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductDetail {

	private int productDetailId;
	private Product product;
	private Size size;
	private int stock;
}
