package ru.andrey.util;

import ru.andrey.model.User;

import java.util.GregorianCalendar;

public class UserView {
    private String firstName;
    private String lastName;
    private String email;
    private String birthYear;
    private String birthMonth;
    private String birthDay;

    public UserView() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getBirthMonth() {
        return birthMonth;
    }

    public void setBirthMonth(String birthMonth) {
        this.birthMonth = birthMonth;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public User getUser() {
        int year = Integer.parseInt(birthYear);
        boolean eraBc = year < 0;
        year = Math.abs(year);
        int month = Integer.parseInt(birthMonth) - 1;
        int day = Integer.parseInt(birthDay);
        User user = new User(firstName, lastName, email,
                new GregorianCalendar(year, month, day).getTime());
        user.setEraBc(eraBc);
        return user;
    }
}
