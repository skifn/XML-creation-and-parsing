package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    final static Scanner in = new Scanner(System.in);
    final static Scanner count = new Scanner(System.in);
    final static Scanner digit = new Scanner(System.in);
    final static String f_name = "students.xml";

    public static void main(String[] args) throws ParserConfigurationException, InterruptedException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();


        //Считаем студентов с клавиатуры
        ArrayList<Students> source = new ArrayList<>(); //Источник студентов
        ArrayList<Students> students; //Лист для считывания студентов через SAX

        System.out.println("\nВведите фамилию, возраст и ср. балл студента\n");
        Students.get(source);

        System.out.println("Создаем XML файл на основе введенных данных...");
        Thread.sleep(1000);

        //Создаем root элемент
        Document document = builder.newDocument();
        Element root = document.createElement("students");
        document.appendChild(root);


        //Создаем необходимые тэги
        for (int i = 0; i < source.size(); i++) {
            //Создаем XML элемент Student (не root)
            Element student = document.createElement("student");
            //Добавляем аттрибут ID
            student.setAttribute("ID", String.valueOf(i + 1));
            //Добавляем student к root элементу
            root.appendChild(student);


            //Создаем XML элемент name (не root)
            Element name = document.createElement("name");
            //Добавляем name к  элементу student (шобы внутри был)
            student.appendChild(name);
            //Добавляем текст
            name.setTextContent(source.get(i).getName()); //Добавляем фамилию к тэгу name

            //Создаем XML документ age (не root)
            Element age = document.createElement("age");
            //Добавляем age к элементу student (шобы внутри был);
            student.appendChild(age);
            //Добавляем текст
            age.setTextContent(source.get(i).getAge());

            //Создаем XML документ age (не root)
            Element average_score = document.createElement("average_score");
            //Добавляем age к элементу student (шобы внутри был);
            student.appendChild(average_score);
            //Добавляем текст
            average_score.setTextContent(source.get(i).getAverage());


        }

        //Создаем XML файл
        try (FileOutputStream output = new FileOutputStream(f_name)) {
            writeXml(document, output);
            System.out.println("XML файл " + f_name + " Создан!");
        } catch (IOException | TransformerException e) {
            e.printStackTrace();
        }

        Thread.sleep(1000);



        System.out.println("\nЧитаем из XML файла:\n");

        //Читаем в лист из XML файла
        SAXParserFactory factory_sax = SAXParserFactory.newInstance();
        try  {

            SAXParser saxParser = factory_sax.newSAXParser();

            //Объявляем слушатель
            SAXParse handler = new SAXParse();

            saxParser.parse(f_name, handler);

            //Выводим лист
            students = handler.getStdList();
            students.forEach(System.out::println);

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

    }






    private static void writeXml(Document document, OutputStream output) throws TransformerException {

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        //Чтобы красиво выводилось
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(output);

        transformer.transform(source, result);



    }


    }

