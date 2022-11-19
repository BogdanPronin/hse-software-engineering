package com.github.bogdan.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "student")
public class Student {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField
    private String fname;

    @DatabaseField
    private String lname;

    public Student(){}

    public Student(String fname, String lname) {
        this.fname = fname;
        this.lname = lname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    @Override
    public String toString() {
        return "Студент: " +
                "№" + id +
                ", Имя:" + fname +
                ", Фамилия:'" + lname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                Objects.equals(fname, student.fname) &&
                Objects.equals(lname, student.lname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fname, lname);
    }
}
