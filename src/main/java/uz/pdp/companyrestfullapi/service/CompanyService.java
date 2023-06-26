package uz.pdp.companyrestfullapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.companyrestfullapi.entity.Address;
import uz.pdp.companyrestfullapi.entity.Company;
import uz.pdp.companyrestfullapi.payload.CompanyDTO;
import uz.pdp.companyrestfullapi.payload.Result;
import uz.pdp.companyrestfullapi.repository.AddressRepository;
import uz.pdp.companyrestfullapi.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.util.ClassUtils.isPresent;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;


    //CREATE
    public Result addCompany(CompanyDTO companyDTO) {
        boolean b = companyRepository.existsCompanyByCorpName(companyDTO.getCorpName());
        if (b)
            return new Result("bunday corparatsiya ushbu kampaniyada mavjud", false);
        Company company = new Company();
        Address address = new Address();
        address.setHomeNumber(companyDTO.getHomeNumber());
        address.setStreet(companyDTO.getStreet());
        addressRepository.save(address);
        company.setAddress(address);
        company.setCorpName(companyDTO.getCorpName());
        company.setDirectorName(companyDTO.getDirectorName());
        companyRepository.save(company);
        return new Result("saqlandi", true);
    }

    //READ
    public List<Company> getCompanies() {
        return companyRepository.findAll();
    }

    //READ2
    public Company getCompany(Integer id) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        return optionalCompany.orElse(null);

    }
    //UPDATE
    public Result updateCompany(Integer id, CompanyDTO companyDTO) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (!optionalCompany.isPresent())
            return new Result("bunday id li companiya topilmadi", false);
        Optional<Address> optionalAddress = addressRepository.findById(optionalCompany.get().getAddress().getId());
        Address address = optionalAddress.get();
        address.setStreet(companyDTO.getStreet());
        address.setHomeNumber(companyDTO.getHomeNumber());
        address.setId(optionalAddress.get().getId());
        addressRepository.save(address);
        Company company = new Company();
        company.setDirectorName(companyDTO.getDirectorName());
        company.setAddress(address);
        company.setCorpName(companyDTO.getCorpName());
        company.setId(id);
        companyRepository.save(company);
        return new Result("updated", true);
    }


    //DELETE
    public Result deleteCompany(Integer id){
         try {
             companyRepository.deleteById(id);
             return new Result("deleted" , true);
         }catch (Exception e){
             e.printStackTrace();
             return new Result("xatolik !!!" , false);
         }
    }

}
