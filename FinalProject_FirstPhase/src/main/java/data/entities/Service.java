package data.entities;

import lombok.Data;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "services")
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "service_name", unique = true, nullable = false)
    private String serviceName;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "service")
    private List<SubService> subServices = new ArrayList<>();//set
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade = CascadeType.ALL)
    private List<User> experts = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "service")
    private List<Order> orders = new ArrayList<>();//delete
}
