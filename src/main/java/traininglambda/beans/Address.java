package traininglambda.beans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Address {

    private String number;
    private String street;
    private String town;
    private String country;


}
