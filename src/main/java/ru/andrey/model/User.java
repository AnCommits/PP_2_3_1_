package ru.andrey.model;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * The procedure for creating a User object with a BC date.
 * <p>
 * setBirthDate and setEraBc in addition to setting its own value
 * also mutually change the other field.
 * Therefore, the order is as follows:
 * 1st option
 * Pass birthDate with the date BC to the constructor or setBirthDate.
 * eraBc will be set automatically.
 * 2nd option
 * Pass birthDate to the constructor or setBirthDate without taking into account the era.
 * Then call setEraBc(true) on the User object.
 */
@Entity
@Table(name = "users")
public class User {

    /**
     * Start date of a new era
     */
    private static final Date DATE0 =
            (new GregorianCalendar(1, Calendar.JANUARY, 1, 0, 0, 0)).getTime();

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
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    /**
     * Field keeps era in MySQL
     */
    @Column(name = "era_bc")
    private boolean eraBc;

    @Column(name = "record_date_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date recordDateTime;

    public User() {
        this.recordDateTime = new Date();
    }

    public User(String firstName, String lastName, String email) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public User(String firstName, String lastName, String email, Date birthDate) {
        this(firstName, lastName, email);
        setBirthDate(birthDate);
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
        if (birthDate != null) {
            eraBc = birthDate.before(DATE0);
        }
    }

    public boolean isEraBc() {
        return eraBc;
    }

    /**
     * When storing java.util.Date object in MySQL, the era information is lost.
     * The eraBc field stores era information in MySQL.
     * To compare dates considering era, the birthDate field must contain a date considering era.
     * Recalculating is as follows.
     *
     * @param eraBc era of the BirthDate:
     *              "true" for BC era
     *              "false" for AD era
     */
    public void setEraBc(boolean eraBc) {
        this.eraBc = eraBc;
        if (birthDate != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(birthDate);
            calendar.set(Calendar.ERA, eraBc ? GregorianCalendar.BC : GregorianCalendar.AD);
            setBirthDate(calendar.getTime());
        }
    }

    public Date getRecordDateTime() {
        return recordDateTime;
    }

    public void setRecordDateTime(Date recordDateTime) {
        this.recordDateTime = recordDateTime;
    }

    public String birthDateToString() {
        final DateFormat BC = new SimpleDateFormat("yyyy-MM-dd до н.э.");
        final DateFormat AD = new SimpleDateFormat("yyyy-MM-dd");
        return birthDate != null
                ? birthDate.before(DATE0) ? BC.format(birthDate) : AD.format(birthDate)
                : "";
    }

    public String recordDateTimeToString() {
        return new SimpleDateFormat("yyyy-MM-dd, HH:mm:ss").format(recordDateTime);
    }

    @Override
    public String toString() {
        return id + " " + getFirstName() + " " + getLastName() + " " + getEmail() + " " +
                birthDateToString() + " " + recordDateTimeToString();
    }
}
