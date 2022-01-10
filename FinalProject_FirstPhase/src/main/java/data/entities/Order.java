package data.entities;

import data.enums.OrderStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "service_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Service service;
    @ManyToOne(cascade = CascadeType.ALL)
    private SubService subService;
    private double proposedPrice;//قیمت پیشنهادی
    private String description;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date workDate;
    private String address;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<Suggestion> suggestions = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Comment comment;
}
