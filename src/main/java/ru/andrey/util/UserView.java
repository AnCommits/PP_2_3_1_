package ru.andrey.util;

import ru.andrey.model.User;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class UserView {
    private String firstName;
    private String lastName;
    private String email;

    /** If birthYear is negative, it indicates the BC era */
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
        User user = new User(firstName, lastName, email);
        try {
            int year = Integer.parseInt(birthYear);
            boolean eraBc = year < 0;
            year = Math.abs(year);
            int month = Integer.parseInt(birthMonth) - 1;
            int day = Integer.parseInt(birthDay);
            if (year <= 9999 && month >= 0 && month <= 11 && day >= 1 && day <= 31) {
                user.setBirthDate(new GregorianCalendar(year, month, day).getTime());
            }
            user.setEraBc(eraBc);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static UserView getUserView(User user) {
        UserView userView = new UserView();

        userView.setFirstName(user.getFirstName());
        userView.setLastName(user.getLastName());
        userView.setEmail(user.getEmail());

        userView.setBirthYear("");
        userView.setBirthMonth("");
        userView.setBirthDay("");
        Date birthDate = user.getBirthDate();
        if (birthDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(birthDate);
            userView.setBirthYear(String.valueOf(calendar.get(Calendar.YEAR)));
            userView.setBirthMonth(String.valueOf(calendar.get(Calendar.MONTH) + 1));
            userView.setBirthDay(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
            if (user.isEraBc()) {
                userView.setBirthYear("-" + userView.getBirthYear());
            }
        }
        return userView;
    }
}
