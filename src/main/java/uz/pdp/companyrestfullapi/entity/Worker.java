package uz.pdp.companyrestfullapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false  , unique = true)
    private String phoneNumber;
    @OneToOne(optional = false)
    private Address address;
    @ManyToOne(optional = false)
    private Department department;
}
