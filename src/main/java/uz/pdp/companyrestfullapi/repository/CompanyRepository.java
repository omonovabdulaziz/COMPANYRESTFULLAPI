package uz.pdp.companyrestfullapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import uz.pdp.companyrestfullapi.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
 boolean existsCompanyByCorpName(String corpName);

}
