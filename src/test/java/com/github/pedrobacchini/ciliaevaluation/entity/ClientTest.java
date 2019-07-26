package com.github.pedrobacchini.ciliaevaluation.entity;

import com.github.pedrobacchini.ciliaevaluation.validation.ValidationTest;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientTest extends ValidationTest {

    public static final String DEFAULT_NAME = "Pedro Bacchini";
    public static final Long DEFAULT_BIRTHDATE = 642481200000L;
    public static final String DEFAULT_EMAIL = "pedro.bacchini@outlook.com";

    private static final String PROPERTY_UUID = "uuid";

    private static final Client OTHER_CLIENT = new Client("Other Name", "other@mail.com");

    private static Client createDefaultClient() {
        Client client = new Client(DEFAULT_NAME, DEFAULT_EMAIL);
        client.setBirthdate(new Date(DEFAULT_BIRTHDATE));
        return client;
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
}