package ru.andrey.mapper;

import ru.andrey.dto.UserDto;
import ru.andrey.model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UserMapper {

    public static UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());

        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());

        userDto.setBirthYear("");
        userDto.setBirthMonth("");
        userDto.setBirthDay("");
        userDto.setEraBC(userDto.isEraBC());
        Date birthDate = user.getBirthDate();
        if (birthDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(birthDate);
            userDto.setBirthYear(String.valueOf(calendar.get(Calendar.YEAR)));
            userDto.setBirthMonth(String.valueOf(calendar.get(Calendar.MONTH) + 1));
            userDto.setBirthDay(String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
        }
        return userDto;
    }

    public static User toUser(UserDto userDto) {
        User user = new User(userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
        user.setId(userDto.getId());
        try {
            String yearStr = userDto.getBirthYear();
            int year = Integer.parseInt(yearStr);
            yearStr = String.format("%04d", year);

            int month = Integer.parseInt(userDto.getBirthMonth());
            int day = Integer.parseInt(userDto.getBirthDay());

            DateFormat df = new SimpleDateFormat("yyyyMMdd");
            df.setLenient(false);
            Date date = df.parse(yearStr + String.format("%02d", month) + String.format("%02d", day));
            user.setBirthDate(date);
        } catch (NumberFormatException | ParseException e) {
            e.printStackTrace();
        }
        user.setEraBc(userDto.isEraBC());
        return user;
    }
}
