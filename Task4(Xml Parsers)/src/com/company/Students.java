package com.company;

import java.util.ArrayList;


import static com.company.Main.*;

public class Students {

    int id;
    String name;
    int age;
    int average_score;

    //Конструктор для создания листа студентов
    public Students(int id, String name, int age, int average_score) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.average_score = average_score;
    }

    //Конструктор для парсинга
    public Students() {

    }

    //Создаем лист со студентами
    public static void get(ArrayList<Students> list) {
        String str;
        int age;
        int average;
        int index = 0;
        while(true) {
            System.out.println("\nВведите фамилию: ");
            str = in.nextLine(); //Читаем строку
            if(str.equals("stop")) //Если слово стоп видим - заканчиваем чтение
                break;
                //Если слово не stop, то
            else
                System.out.println("\nВведите возраст: ");
                age = count.nextInt();
                System.out.println("\nВведите средний балл: ");
                average = digit.nextInt();
                index++;
            list.add(new Students(index, str, age, average)); //Отправляем в List
        }


    }


    //Корректный вывод
    @Override
    public String toString() {


        return getID() + "| " + "Студент " + getName() + " Возраст: " + getAge() + " Средний балл: " + getAverage();
}


    //Геттеры
     public String getID() {return String.valueOf(id);}
     public String getName() { return name;}
     public String getAge() {return String.valueOf(age);}
     public String getAverage() {return String.valueOf(average_score);}

    //Сеттеры
    public void setID(int id) { this.id = id;}
    public void setName(String name) { this.name = name;}
    public void setAge (int age) {this.age = age;}
    public void setAverage(int average_score) { this.average_score = average_score;}



}
