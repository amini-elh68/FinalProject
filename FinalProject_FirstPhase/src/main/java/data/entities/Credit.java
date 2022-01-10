package data.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "user_credit")
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
}
