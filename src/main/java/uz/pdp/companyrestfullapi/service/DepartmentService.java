package uz.pdp.companyrestfullapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyrestfullapi.entity.Company;
import uz.pdp.companyrestfullapi.entity.Department;
import uz.pdp.companyrestfullapi.payload.DepartmentDTO;
import uz.pdp.companyrestfullapi.payload.Result;
import uz.pdp.companyrestfullapi.repository.CompanyRepository;
import uz.pdp.companyrestfullapi.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    CompanyRepository companyRepository ;
    //create
    public Result addDepartment(DepartmentDTO departmentDTO) {
        Integer integer = departmentRepository.existsDepartmentsByCompanyAndName(departmentDTO.getCompanyId(), departmentDTO.getName());
        if (integer > 0){
            return new Result("bunday nomli departament ushbu kampaniyaga qoshilgan " , false);
        }
        Department department = new Department();
        Optional<Company> optionalCompany = companyRepository.findById(departmentDTO.getCompanyId());
        if (!optionalCompany.isPresent())
            return new Result("bunday id li companiya topilmadi" , false);
        Company company = optionalCompany.get();
        department.setCompany(company);
        department.setName(departmentDTO.getName());
        departmentRepository.save(department);
        return new Result("saved" , true);
    }


    //read

    public List<Department> getAllDepartment() {
        return departmentRepository.findAll();
    }


    //READ2

    public Department getDepartment( Integer id){
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        return optionalDepartment.orElseGet(Department::new);

    }
//    //update
    public Result editDepartment( Integer id ,  DepartmentDTO departmentDTO ){
        Integer integer = departmentRepository.existsDepartmentsByCompanyAndName(id, departmentDTO.getName());
        if (integer> 0)
            return new Result("bunday id li department ushbu korxonada bor" , false);
       Department department = new Department();
        Company company = companyRepository.findById(id).get();
        department.setName(departmentDTO.getName());
        department.setCompany(company);
        department.setId(id);
        departmentRepository.save(department);
        return new Result("updated " , true);
    }
//
//
//    //delete
    public Result deleteDepartment( Integer id){
       try {
           departmentRepository.deleteById(id);
           return new Result("deleted " , true);
       }catch (Exception e){
           e.printStackTrace();
           return new Result("xatoliikkk!!" , false);
       }
    }


}
