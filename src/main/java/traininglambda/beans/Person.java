package traininglambda.beans;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class Person {

    private Integer id;
    private String firstName;
    private String lastName;
    private String sex;
    private Integer age;

    @Singular
    private List<String> emails = new ArrayList<>();

    @Singular
    private List<Address> addresses = new ArrayList<>();

}
