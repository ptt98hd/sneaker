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
public class Customer {

	private int customerId;
	private String customerName;
	private String phone;
	private String address;
}
