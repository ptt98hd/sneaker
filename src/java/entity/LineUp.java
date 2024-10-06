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
public class LineUp {

	private int lineUpId;
	private String lineUpName;
	private String lineUpImage;
	private Brand brand;
	private Category category;
}
