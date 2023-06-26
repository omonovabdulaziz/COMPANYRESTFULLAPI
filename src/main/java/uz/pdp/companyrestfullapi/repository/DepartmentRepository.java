package uz.pdp.companyrestfullapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.companyrestfullapi.entity.Company;
import uz.pdp.companyrestfullapi.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @Query(nativeQuery = true , value = "select count(*) from department where company_id =:companyId and department.name =:forname")
    Integer existsDepartmentsByCompanyAndName(@Param("companyId")Integer companyid , @Param("forname") String name);
}
