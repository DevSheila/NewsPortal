package models;

import java.util.Objects;

public class Departments {
    private int id;
    private String name;
    private String descri;
    private int num_employees;

    public Departments(String name, String descri, int num_employees) {
        this.name = name;
        this.descri= descri;
        this.num_employees = num_employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return descri;
    }

    public void setDescription(String descri) {
        this.descri = descri;
    }

    public int getNumEmployees() {
        return num_employees;
    }

    public void setNumEmployees(int num_employees) {
        this.num_employees = num_employees;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Departments)) return false;
        Departments that = (Departments) o;
        return getNumEmployees() == that.getNumEmployees() && Objects.equals(getName(), that.getName()) && Objects.equals(descri, that.descri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), descri, getNumEmployees());
    }
}
