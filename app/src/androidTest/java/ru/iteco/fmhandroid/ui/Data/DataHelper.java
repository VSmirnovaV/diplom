package ru.iteco.fmhandroid.ui.Data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {

    public static Faker faker = new Faker(new Locale("en"));

    public static String getValidLogin() {
        return ("login2");
    }

    public static String getValidPassword() {
        return ("password2");
    }

    public static String getValidLoginWithSpase() {
        return (" login2");
    }

    public static String getValidPasswordWithSpase() {
        return (" password2");
    }

    public static String generateInvalidLogin(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().username();
    }

    public static String generateInvalidPassword(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.internet().password();
    }

    public static String generateInvalidString(int stringLength) {
        return faker.regexify("[@!#$%^&*()_+=-}{]{" + stringLength + "}");
    }

    public static String generateNumber(int stringLength) {
        return faker.regexify("[0-9]{" + stringLength + "}");
    }

//    public static String generateCategory() {
//        String[] category = new String[]{
//                "Объявление",
//                "День рождения",
//                "Зарплата",
//                "Профсоюз",
//                "Праздник",
//                "Массаж",
//                "Благодарность",
//                "Нужна помощь"};
//        return category[new Random().nextInt(category.length)];
//    }

    public static String getCategory(int index) {
        String[] category = new String[]{
                "Объявление",
                "День рождения",
                "Зарплата",
                "Профсоюз",
                "Праздник",
                "Массаж",
                "Благодарность",
                "Нужна помощь"};
        return category[index - 1];
    }

//    public static String setData(String data) {
//        return data;
//    }

    public static String generateDate(int day) {
        String pattern = "dd.MM.yyyy";
        String date = LocalDate.now().plusDays(day).format(DateTimeFormatter.ofPattern(pattern));
        return date;
    }

    public static String getCurrentDate() {
        String pattern = "dd.MM.yyyy";
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
        return date;
    }

    public static String getCurrentTime() {
        String pattern = "HH:mm";
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern(pattern));
        return time;
    }

    public static String generateTime(int minute) {
        String pattern = "HH:mm";
        String time = LocalTime.now().plusMinutes(minute).format(DateTimeFormatter.ofPattern(pattern));
        return time;
    }

    public static String addTime(int hours, int minutes) {
        return hours + ":" + minutes;
    }
}





