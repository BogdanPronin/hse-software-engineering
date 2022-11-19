package com.github.bogdan.models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Objects;

@DatabaseTable(tableName = "journal")
public class Journal {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(foreign = true,foreignAutoRefresh = true)
    private Student student;

    @DatabaseField
    private String date;

    @DatabaseField
    private boolean isAttend;

    @DatabaseField
    private int grade;

    public Journal() {
    }

    public Journal(Student student, String date, boolean isAttend, int grade) {
        this.student = student;
        this.date = date;
        this.isAttend = isAttend;
        this.grade = grade;
    }

    public boolean isAttend() {
        return isAttend;
    }

    public void setAttend(boolean attend) {
        isAttend = attend;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Запись " +
                "№" + id +
                ", " + student +
                ", дата:'" + date +
                ", присутсвует?:" + isAttend +
                ", оценка:" + grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Journal journal = (Journal) o;
        return id == journal.id &&
                isAttend == journal.isAttend &&
                grade == journal.grade &&
                Objects.equals(student, journal.student) &&
                Objects.equals(date, journal.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, student, date, isAttend, grade);
    }
}
