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
public class Category {
    private int categoryId;
    private String categoryName;
    private String categoryImage;
}
