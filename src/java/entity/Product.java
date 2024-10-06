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
public class Product {

	private int productId;
	private String productName;
	private LineUp lineUp;
	private Color color;
	private double price;
	private Date date;
	private String productImage;
}
