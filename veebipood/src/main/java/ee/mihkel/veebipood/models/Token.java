package ee.mihkel.veebipood.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Token {
    private String token;
    private Date expiration;
}
