package data.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "service_subServices")
//, uniqueConstraints = @UniqueConstraint(columnNames = {"sub_service_name", "service_id"}
public class SubService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "sub_service_name",unique = true, nullable = false)
    private String subServiceName;
    @ManyToOne(cascade = CascadeType.ALL)
    private Service service;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subService")
    private List<Order> orders = new ArrayList<>();
}
