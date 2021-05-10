package models;

import java.util.Objects;

public class Users {
    private int id;
    private String name;
    private int depId;
    private String position;
    private String roles;

    public Users(String name, int depId, String position, String roles) {
        this.name = name;
        this.depId = depId;
        this.position = position;
        this.roles = roles;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepId() {
        return depId;
    }

    public void setDepId(int depId) {
        this.depId = depId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users)) return false;
        Users users = (Users) o;
        return getDepId() == users.getDepId() && Objects.equals(getName(), users.getName()) && Objects.equals(getPosition(), users.getPosition()) && Objects.equals(getRoles(), users.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDepId(), getPosition(), getRoles());
    }


}
