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
public class Account {
    private int accountId;
    private String fullname;
    private String email;
    private String password;
    private boolean admin;
}
