package com.github.pedrobacchini.ciliaevaluation.entity;

import com.github.pedrobacchini.ciliaevaluation.util.TestUtil;
import com.github.pedrobacchini.ciliaevaluation.validation.ValidationTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientTest extends ValidationTest {

    private static final String DEFAULT_NAME = "Pedro Bacchini";
    private static final Long DEFAULT_BIRTHDATE = 642481200000L;
    private static final String DEFAULT_EMAIL = "pedro.bacchini@outlook.com";

    private static final String PROPERTY_UUID = "uuid";
    private static final String PROPERTY_NAME = "name";
    private static final String PROPERTY_EMAIL = "email";

    private static final Client OTHER_CLIENT = new Client("Other Name", "other@mail.com");

    private static final String[] VALID_NAMES = {
            "Maria Silva",
            "Pedro Carlos",
            "Luiz Antônio",
            "Albert Einstein",
            "João Doria",
            "Barack Obama",
            "Friedrich von Hayek",
            "Ludwig van Beethoven",
            "Jeanne d'Arc",
            "Saddam Hussein al-Tikriti",
            "Osama bin Mohammed bin Awad bin Laden",
            "Luís Inácio Lula da Silva",
            "Getúlio Dornelles Vargas",
            "Juscelino Kubitschek de Oliveira",
            "Jean-Baptiste le Rond d'Alembert",
            "Pierre-Simon Laplace",
            "Hans Christian Ørsted",
            "Joseph Louis Gay-Lussac",
            "Scarlett O'Hara",
            "Ronald McDonald",
            "María Antonieta de las Nieves",
            "Luís Augusto Maria Eudes de Saxe-Coburgo-Gota",
            "Martin Luther King Jr.",
            "William Henry Gates III",
            "John William D'Arcy",
            "John D'Largy",
            "Samuel Eto'o",
            "Åsa Ekström",
            "Gregor O'Sulivan",
            "Ítalo Gonçalves"
    };

    private static final String[] INVALID_NAMES = {
            "",
            "Maria",
            "Maria-Silva",
            "Marcos E",
            "E Marcos",
            "Maria  Silva",
            "Maria Silva ",
            " Maria Silva ",
            "Maria silva",
            "maria Silva",
            "MARIA SILVA",
            "MAria Silva",
            "Maria SIlva",
            "Jean-Baptiste",
            "Jeanne d' Arc",
            "Joseph Louis Gay-lussac",
            "Pierre-simon Laplace",
            "Maria daSilva",
            "Maria~Silva",
            "Maria Silva~",
            "~Maria Silva",
            "Maria~ Silva",
            "Maria ~Silva",
            "Maria da da Silva",
            "Maria da e Silva",
            "Maria de le Silva",
            "William Henry Gates iii",
            "Martin Luther King, Jr.",
            "Martin Luther King JR",
            "Martin Luther Jr. King",
            "Martin Luther King Jr. III",
            "Maria G. Silva",
            "Maria G Silva",
            "Maria É Silva",
            "Maria wi Silva",
            "Samuel 'Etoo",
            "Samuel Etoo'",
            "Samuel Eto''o"
    };

    private static Client createDefaultClient() {
        Client client = new Client(DEFAULT_NAME, DEFAULT_EMAIL);
        client.setBirthdate(new Date(DEFAULT_BIRTHDATE));
        return client;
    }

    @Test
    public void shouldHaveNoViolations() {
        Client client = createDefaultClient();

        assertThat(DEFAULT_NAME).isEqualTo(client.getName());
        assertThat(DEFAULT_EMAIL).isEqualTo(client.getEmail());
        assertThat(DEFAULT_BIRTHDATE).isEqualTo(client.getBirthdate().getTime());

        assertThat(validator.validate(client).isEmpty()).isTrue();
    }

    @Test
    public void shouldDetectNullName() { shouldDetectConstraintNotNullIn(PROPERTY_NAME); }

    @Test
    public void shouldDetectInvalidSizeName() {
        shouldDetectConstraintIn(PROPERTY_NAME, "Pedro de Alcântara Francisco António João Carlos Xavier de " +
                "Paula Miguel Rafael Joaquim José Gonzaga Pascoal Cipriano Serafim", Collections.singletonList(Size.class));
    }

    @Test
    public void shouldDetectValidPatternName() {
        for(String validName : VALID_NAMES) {
            Client client = createDefaultClient();
            ReflectionTestUtils.setField(client, PROPERTY_NAME, validName);
            Assert.assertTrue(validator.validate(client).isEmpty());
        }
    }

    @Test
    public void shouldDetectInvalidPatternName() {
        for(String invalidName : INVALID_NAMES)
            shouldDetectConstraintIn(PROPERTY_NAME, invalidName, Collections.singletonList(Pattern.class));
    }

    @Test
    public void shouldDetectNullEmail() { shouldDetectConstraintNotNullIn(PROPERTY_EMAIL); }

    @Test
    public void shouldDetectInvalidEmail() {
        shouldDetectConstraintIn(PROPERTY_EMAIL, "test", Collections.singletonList(Email.class));
        shouldDetectConstraintIn(PROPERTY_EMAIL, "testglobo.com", Collections.singletonList(Email.class));
        shouldDetectConstraintIn(PROPERTY_EMAIL, "test@", Collections.singletonList(Email.class));
        shouldDetectConstraintIn(PROPERTY_EMAIL, "@.com", Collections.singletonList(Email.class));
    }

    @Test
    public void shouldDetectEqualsClients() {
        UUID uuid = UUID.randomUUID();
        Client defaultClient = createDefaultClient();
        ReflectionTestUtils.setField(defaultClient, PROPERTY_UUID, uuid);
        ReflectionTestUtils.setField(OTHER_CLIENT, PROPERTY_UUID, uuid);
        assertThat(defaultClient.equals(OTHER_CLIENT)).isTrue();
        assertThat(defaultClient.hashCode() == OTHER_CLIENT.hashCode()).isTrue();
    }


    @Test
    public void shouldDetectNotEqualsClients() {
        Client a = createDefaultClient();
        ReflectionTestUtils.setField(OTHER_CLIENT, PROPERTY_UUID, UUID.randomUUID());
        assertThat(a.equals(OTHER_CLIENT)).isFalse();
        assertThat(a.hashCode() == OTHER_CLIENT.hashCode()).isFalse();
    }

    private void shouldDetectConstraintNotNullIn(String property) {
        shouldDetectConstraintIn(property, null, Collections.singletonList(NotNull.class));
    }

    private <T> void shouldDetectConstraintIn(String property, Object value, List<T> constraintAnnotationTypeExpected) {
        try {
            Client client = createDefaultClient();
            ReflectionTestUtils.setField(client, property, value);
            TestUtil.buildConstraintViolationException(validator.validate(client));
            TestUtil.failure();
        } catch(ConstraintViolationException e) {
            TestUtil.assertConstrainViolationEquals(e, constraintAnnotationTypeExpected, property);
        }
    }
}