package com.company;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;


import java.util.ArrayList;


public class SAXParse extends DefaultHandler {

    private ArrayList<Students> studentsList = null;
    private Students std = null;
    private StringBuilder data = null;

    // getter method for employee list
    public ArrayList<Students> getStdList() {
        return studentsList;
    }

    boolean bname = false;
    boolean bage = false;
    boolean baverage = false;


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes)  {

        //Если встречается тэг student
        if (qName.equalsIgnoreCase("student")) {

            String id = attributes.getValue("ID");
            //Инициализируем объект класса студенты
            std = new Students();
            std.setID(Integer.parseInt(id)); //Устанавливаем айдишник для студента
            //Если лист равен нулю, то создаем новый лист
            if (studentsList == null)
                studentsList = new ArrayList<>();

        } else if (qName.equalsIgnoreCase("name")) {
            //Если встречается тег "name" то тогда ставим для bname true
            bname = true;

        } else if (qName.equalsIgnoreCase("age")) {
            bage = true;

        } else if (qName.equalsIgnoreCase("average_score")) {
            baverage = true;
        }

        // Создаем контейнер данных
        data = new StringBuilder();


    }

    @Override
    public void endElement(String uri, String localName, String qName)  {
        if (bage) {
            // Передаем возраст через сеттер
            std.setAge(Integer.parseInt(data.toString()));
            bage = false;

        } else if (bname) {
            std.setName(data.toString());
            bname = false;

        } else if (baverage) {
            std.setAverage(Integer.parseInt(data.toString()));
            baverage = false;
        }

        if (qName.equalsIgnoreCase("student")) {
            // Добавляем объект класса студенты в лист
            studentsList.add(std);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)  {
        data.append(new String(ch, start, length));
    }
}