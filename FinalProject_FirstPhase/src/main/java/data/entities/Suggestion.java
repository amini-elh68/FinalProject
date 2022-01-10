package data.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Data
@Entity
@Table(name = "order_suggestions")
public class Suggestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double proposedPrice;
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    private Time duration;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private User proposer;
    @ManyToOne(cascade = CascadeType.ALL)
    private Order order;
}
