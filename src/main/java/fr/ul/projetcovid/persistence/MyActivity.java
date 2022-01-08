package fr.ul.projetcovid.persistence;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "activity", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"}),
        @UniqueConstraint(columnNames = {"name"})
})
public class MyActivity {


    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @OneToOne
    @JoinColumn(name = "activity", nullable = false)
    private Activity activity;

    @OneToOne
    @JoinColumn(name = "user", nullable = false)
    UserAccount userAccount;

    @Column(name = "city", nullable = false)
    private String city;


    @Column(name = "codePostal", nullable = false)
    String codePostal;


    @Temporal(TemporalType.TIME)
    @Column(name = "endTime", nullable =false)
    private Date endTime;

    @Temporal(TemporalType.TIME)
    @Column(name = "startTime", nullable =false)
    private Date startTime;



    @Temporal(TemporalType.DATE)
    @Column(name = "date", nullable = false)
    private Date date;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}