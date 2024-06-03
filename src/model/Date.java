package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Date {
    int day;
    int month;
    int year;

    public Date(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public long toMilisecond() {
        LocalDateTime localDateTime = LocalDate.parse(day + " " + month + " " + year, DateTimeFormatter.ofPattern("dd MM yyyy")).atStartOfDay();
        return localDateTime.toEpochSecond(ZoneOffset.UTC) * 1000;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return this.day + " / " + this.month + " / " + this.year;
    }
}
