package com.nikita.model.entity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AdminTest {

    private int id = 1;
    private String username = "testUsername";
    private String password = "testPassword";
    private Admin admin;

    @Before
    public void setUp() throws Exception {
        admin = new Admin(id, username, password);
    }

    @Test
    public void getId() {
        int id = admin.getId();

        assertEquals(this.id, id);
    }

    @Test
    public void setId() {
        int newId = 2;
        admin.setId(newId);

        int id = admin.getId();

        assertEquals(newId, id);
    }

    @Test
    public void getUsername() {
        String username = admin.getUsername();

        assertEquals(this.username, username);
    }

    @Test
    public void setUsername() {
        String newUsername = "newUsername";
        admin.setUsername(newUsername);

        String username = admin.getUsername();

        assertEquals(newUsername, username);
    }

    @Test
    public void getPassword() {
        String password = admin.getPassword();

        assertEquals(this.password, password);
    }

    @Test
    public void setPassword() {
        String newPassword = "newPassword";
        admin.setPassword(newPassword);

        String password = admin.getPassword();

        assertEquals(newPassword, password);
    }

    @Test
    public void builder() {
        Admin admin = Admin.builder()
            .id(id)
            .username(username)
            .password(password)
            .build();
        assertEquals(admin, this.admin);
    }
}