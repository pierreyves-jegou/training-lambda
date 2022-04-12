package traininglambda.keepthecontext;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import traininglambda.beans.Address;
import traininglambda.beans.Person;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class KeepTheContextTest {

    private static List<Person> persons;

    @BeforeAll
    static void beforeAll(){
        Person jeanBon = Person.builder()
                .id(1)
                .firstName("jean")
                .lastName("bon")
                .age(12)
                .email("jeanbon@cgi.com")
                .email("jeanbon@gmail.com")
                .address(Address.builder()
                        .number("19")
                        .street("rue des mouettes")
                        .town("vic la gardiole")
                        .country("France")
                        .build())
                .address(Address.builder()
                        .number("4")
                        .street("rue de Montpellier")
                        .town("Lattes")
                        .country("France")
                        .build())
                .address(Address.builder()
                        .number("46")
                        .street("Franklin Roosevelt")
                        .town("New York")
                        .country("US")
                        .build())
                .build();

        Person johnDo = Person.builder()
                .id(2)
                .firstName("john")
                .lastName("do")
                .age(25)
                .email("johndo@cgi.com")
                .email("johndo@gmail.com")
                .address(Address.builder()
                        .number("21")
                        .street("rue des Herisson")
                        .town("Sydney")
                        .country("Australie")
                        .build())
                .build();

        persons = Arrays.asList(jeanBon, johnDo);
    }

    @Test
    void recupererIdPersonEtAdressePourAdresseEnFrance(){
        // Exo2 :  avec 1 lambda, récuperer l'identifiant et l'adresse si celle ci est 'France'

        //// TODO : commit solution
        List<Pair<Integer, Address>> idAndAddressInFrance = persons.stream()
                .flatMap(person -> person.getAddresses()
                        .stream()
                        .map(address -> Pair.of(person.getId(), address))
                ).filter(pair -> "France".equals(pair.getRight().getCountry()))
                .collect(Collectors.toList());


//        List<Pair<Integer, Address>> idAndAddressInFrance = null;
        assertThat(idAndAddressInFrance)
                .hasSize(2)
                .containsExactlyInAnyOrder(
                        Pair.of(1, Address.builder()
                        .number("19")
                        .street("rue des mouettes")
                        .town("vic la gardiole")
                        .country("France")
                        .build()),
                        Pair.of(1,Address.builder()
                                .number("4")
                                .street("rue de Montpellier")
                                .town("Lattes")
                                .country("France")
                                .build())
                        );
    }

}
