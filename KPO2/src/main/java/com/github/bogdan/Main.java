package com.github.bogdan;

import com.github.bogdan.databaseConfiguration.DatabaseConfiguration;
import com.github.bogdan.models.Journal;
import com.github.bogdan.models.Student;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.query.In;

import java.sql.SQLException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        String databasePath = "jdbc:sqlite:test.db";
        DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration(databasePath);
        Dao<Student, Integer> studentDao = DaoManager.createDao(databaseConfiguration.getConnectionSource(), Student.class);
        Dao<Journal, Integer> journalDao = DaoManager.createDao(databaseConfiguration.getConnectionSource(), Journal.class);
        Scanner sc = new Scanner(System.in);
        System.out.println();
        String line = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = LocalDate.now().format(formatter);


        System.out.println("/l - показывает журнал посещаемости\n" +
                "/r - выдает рандомного студента, которого вы СЕГОДНЯ еще не отметили\n" +
                "/e - выход из программы\n" +
                "Пока что, вы не можете изменять записи, поэтому будьте осторожны)\n" +
                "(Но подключена БД и если у вас есть db browser, то можете сделать это там)");

        while(!line.equals("/e")){
            checkR(line,journalDao,date,studentDao);
            checkL(line,journalDao);

            System.out.println("Введите команду");
            line = sc.nextLine();
        }
    }

    public static void checkR(String string, Dao<Journal,Integer> journalDao, String date,Dao<Student,Integer> studentDao) throws SQLException {
        if(string.equals("/r")){
            List<Student> studentList = getUniqueJournalRecords(date,journalDao,studentDao);
            Random generator = new Random();
            if (studentList.isEmpty()) {
                System.out.println("Упс, вы отметили всех студентов за сегодня");
                return;
            }
            int randomIndex = generator.nextInt(studentList.size());
            Student student = studentList.get(randomIndex);
            System.out.println("Отвечает " + student);
            System.out.println("Присутствует на паре?(y/n)");
            Scanner sc = new Scanner(System.in);
            while (true){
                String line = sc.nextLine();
                if(line.equals("y")){

                    System.out.print("Введите оценку: ");
                    int grade = sc.nextInt();
                    Journal record = new Journal(student, date,true, grade);
                    journalDao.create(record);
                    return;
                }else if (line.equals("n")){
                    Journal record = new Journal(student, date,false, 0);
                    journalDao.create(record);
                    return;
                }else if (line.equals("/e")){
                    return;
                }
                studentList.remove(student);
            }

        }
    }

    public static List<Student> getUniqueJournalRecords(String date, Dao<Journal, Integer> journalDao, Dao<Student, Integer> studentDao) throws SQLException {
        List<Student> list = studentDao.queryForAll();
        for(Student student:studentDao.queryForAll()){
            for (Journal record:journalDao.queryForAll()) {
                if (record.getStudent().equals(student) && record.getDate().equals(date)) {
                    list.remove(student);
                }
            }
        }
        return list;
    }
    public static void checkL(String line, Dao<Journal,Integer> journalDao) throws SQLException {
        if(line.equals("/l")){
            for (Journal record:journalDao.queryForAll()){
                System.out.println(record);
            }
        }
    }
}

