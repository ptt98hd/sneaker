package entity;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ProductDetail {

	private int productDetailId;
	private Product product;
	private Size size;
	private int stock;

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 59 * hash + this.productDetailId;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ProductDetail other = (ProductDetail) obj;
		return this.productDetailId == other.productDetailId;
	}

}
