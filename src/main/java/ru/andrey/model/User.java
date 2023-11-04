package ru.andrey.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name = "users")
public class User {

    private final static Calendar newEra;

    static {
        newEra = new GregorianCalendar(1, Calendar.DECEMBER, 31, 23, 59, 59);
        newEra.set(Calendar.ERA, GregorianCalendar.BC);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "birth_date")
    private Date birthDate;

    // field keeps era in MySQL
    @Column(name = "era_bc")
    private boolean eraBc;

    @Column(name = "record_date_time")
    private Date recordDateTime;

    public User() {
    }

    public User(String firstName, String lastName, String email, Date birthDate, Date recordDateTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.birthDate = birthDate;
        eraBc = birthDate.before(newEra.getTime());
        this.recordDateTime = recordDateTime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName == null ? "" : firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName == null ? "" : lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email == null ? "" : email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        eraBc = birthDate.before(newEra.getTime());
    }

    public boolean isEraBc() {
        return eraBc;
    }

    public void setEraBc(boolean eraBc) {
        this.eraBc = eraBc;
        // При сохранении даты в MySQL информация об эре теряется.
        // Для сравнения дат до н.э. в поле birthDate должно быть корректное значение даты.
        // Флаг eraBc нужен только для сохрания в MySQL дат до н.э.
        // Поэтому пересчитываем birthDate с учетом эры.
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(getBirthDate());
        calendar.set(Calendar.ERA, isEraBc() ? GregorianCalendar.BC : GregorianCalendar.AD);
        setBirthDate(calendar.getTime());
    }

    public Date getRecordDateTime() {
        return recordDateTime;
    }

    public void setRecordDateTime(Date recordDateTime) {
        this.recordDateTime = recordDateTime;
    }

    public String birthDayToString() {
        final DateFormat BC = new SimpleDateFormat("yyyy-MM-dd до н.э.");
        final DateFormat AD = new SimpleDateFormat("yyyy-MM-dd");
        return birthDate.before(newEra.getTime()) ? BC.format(birthDate) : AD.format(birthDate);
    }

    public String recordDateTimeToString() {
        return new SimpleDateFormat("yyyy-MM-dd, HH:mm").format(recordDateTime);
    }

    @Override
    public String toString() {
        return id + " " + firstName + " " + lastName + " " + email + " " +
                birthDayToString() + " " + recordDateTimeToString();

    }
}
