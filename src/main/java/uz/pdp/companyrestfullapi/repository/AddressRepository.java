package uz.pdp.companyrestfullapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.companyrestfullapi.entity.Address;

public interface AddressRepository extends JpaRepository<Address , Integer> {
}
