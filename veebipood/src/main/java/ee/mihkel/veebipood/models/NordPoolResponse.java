package ee.mihkel.veebipood.models;

import lombok.Data;

// json to pojo
// javascript object notation to plain old java object

@Data
public class NordPoolResponse {
    public boolean success;
    public Andmed data;
}
