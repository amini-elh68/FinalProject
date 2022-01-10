package data.entities;

import data.enums.Role;
import data.enums.UserStatus;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NamedQueries({@NamedQuery(name = "searchUserByEmail", query = "from User where email=:email")})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String family;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date registerDate;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    private byte[] image;
    private float score;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "experts_services")
    private List<Service> services = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proposer")
    private List<Suggestion> suggestions = new ArrayList<>();
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
    private Credit credit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Comment> comments = new ArrayList<>();
}
