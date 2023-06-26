package uz.pdp.companyrestfullapi.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false , unique = true)
    private String corpName;
    @Column(nullable = false)
    private String directorName;
    @OneToOne(optional = false)
    private Address address;
}
