package fr.ul.projetcovid.persistence;


 import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Time;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "myActivity", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"id"}),

})
@NamedQueries({
        @NamedQuery(name = "MyActivity.findAll", query = "select distinct a from MyActivity a")

})
public class MyActivity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", nullable = false, length = 36)
    private String id;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "activity", nullable = false)
    private Activity activity;

    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "user", nullable = false)
    private UserAccount userAccount;


    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "lieux", nullable = false)
    private Place place;

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

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}