package uz.pdp.companyrestfullapi.repository;

import org.hibernate.jdbc.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.companyrestfullapi.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker , Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
