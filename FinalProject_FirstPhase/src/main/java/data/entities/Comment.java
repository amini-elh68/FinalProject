package data.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "order_comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float score;//rate int 0-5
    private String comment;
    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
    @OneToOne
    private Order order;
}
