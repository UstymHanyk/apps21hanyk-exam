package domain;

import json.*;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    protected Tuple<String, Integer>[] exams;

    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = exams;
    }

    @Override
    public JsonObject toJsonObject() {
        JsonPair name = new JsonPair("name", new JsonString(this.name));
        JsonPair surname = new JsonPair("surname", new JsonString(this.surname));
        JsonPair year = new JsonPair("year", new JsonNumber(this.year));

        JsonObject[] examsList = new JsonObject[this.exams.length];
        int examId = 0;

        for (Tuple<String, Integer> currExam : this.exams) {
            JsonPair course = new JsonPair("course", new JsonString(currExam.key));
            JsonPair mark = new JsonPair("mark", new JsonNumber(currExam.value));
            JsonPair passed = new JsonPair("passed", new JsonBoolean((currExam.value > 2) ? true: false));

            examsList[examId] = new JsonObject(course, mark, passed);
            examId++;
        }

        JsonPair exams = new JsonPair("exams", new JsonArray(examsList));
        return new JsonObject(name, surname, year, exams);
    }
}