package com.nikita.model.entity;

public class Admin {
    private int id;
    private String username;
    private String password;

    public Admin(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static AdminBuilder builder() {
        return new AdminBuilder();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if(object == null || object.getClass() != this.getClass()) {
            return false;
        }

        Admin admin = (Admin) object;

        return
            id == admin.id &&
            username.equals(admin.username) &&
            password.equals(admin.password);
    }

    @Override public int hashCode() {
        return id;
    }

    public static class AdminBuilder {
        private int id;
        private String username;
        private String password;

        public AdminBuilder id(int id) {
            this.id = id;
            return this;
        }

        public AdminBuilder username(String username) {
            this.username = username;
            return this;
        }

        public AdminBuilder password(String password) {
            this.password = password;
            return this;
        }

        public Admin build() {
            return new Admin(id, username, password);
        }
    }
}
