package com.github.pedrobacchini.ciliaevaluation.entity;

import com.github.pedrobacchini.ciliaevaluation.util.TestUtil;
import com.github.pedrobacchini.ciliaevaluation.validation.ValidationTest;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import javax.validation.ConstraintViolationException;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientTest extends ValidationTest {

    private static final String DEFAULT_NAME = "Pedro Bacchini";
    private static final Long DEFAULT_BIRTHDATE = 642481200000L;
    private static final String DEFAULT_EMAIL = "pedro.bacchini@outlook.com";

    private static final String PROPERTY_NAME = "name";
    private static final String PROPERTY_EMAIL = "email";

    private static final Client OTHER_CLIENT = new Client("Other Name", "other@mail.com");

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
    public void shouldDetectInvalidPatternName() {
        shouldDetectConstraintIn(PROPERTY_NAME, "Maria  Silva", Collections.singletonList(Pattern.class));
        shouldDetectConstraintIn(PROPERTY_NAME, "Maria silva", Collections.singletonList(Pattern.class));
        shouldDetectConstraintIn(PROPERTY_NAME, "maria Silva", Collections.singletonList(Pattern.class));
        shouldDetectConstraintIn(PROPERTY_NAME, " Maria Silva", Collections.singletonList(Pattern.class));
        shouldDetectConstraintIn(PROPERTY_NAME, "Maria Silva ", Collections.singletonList(Pattern.class));
        shouldDetectConstraintIn(PROPERTY_NAME, "Maria / Silva", Collections.singletonList(Pattern.class));
        shouldDetectConstraintIn(PROPERTY_NAME, "Maria . Silva", Collections.singletonList(Pattern.class));
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
        ReflectionTestUtils.setField(defaultClient, "uuid", uuid);
        ReflectionTestUtils.setField(OTHER_CLIENT, "uuid", uuid);
        assertThat(defaultClient.equals(OTHER_CLIENT)).isTrue();
        assertThat(defaultClient.hashCode() == OTHER_CLIENT.hashCode()).isTrue();
    }


    @Test
    public void shouldDetectNotEqualsClients() {
        Client a = createDefaultClient();
        ReflectionTestUtils.setField(OTHER_CLIENT, "uuid", UUID.randomUUID());
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