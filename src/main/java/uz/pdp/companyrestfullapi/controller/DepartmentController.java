package uz.pdp.companyrestfullapi.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyrestfullapi.entity.Department;
import uz.pdp.companyrestfullapi.payload.DepartmentDTO;
import uz.pdp.companyrestfullapi.payload.Result;
import uz.pdp.companyrestfullapi.service.DepartmentService;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    //create
    @PostMapping
    public ResponseEntity<Result> addDepartment(@RequestBody DepartmentDTO departmentDTO ){
        Result result = departmentService.addDepartment(departmentDTO);
        return ResponseEntity.status(result.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(result);
    }


    //read
    @GetMapping
    public  List<Department> getAllDepartment(){
        return departmentService.getAllDepartment();
    }


    //READ2
    @GetMapping("/byId/{id}")
    public  Department getDepartment(@PathVariable Integer id){
        return departmentService.getDepartment(id);
    }
    //update
    @PutMapping("/byId/{id}")
    public ResponseEntity<Result> editDepartment(@PathVariable Integer id , @RequestBody DepartmentDTO departmentDTO ){
        Result result = departmentService.editDepartment(id, departmentDTO);
        return ResponseEntity.status(result.isSuccess()? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }
//
//
//    //delete
    @DeleteMapping("/byId/{id}")
    public ResponseEntity<Result> deleteDepartment(@PathVariable Integer id){
        Result result = departmentService.deleteDepartment(id);
        return ResponseEntity.status(result.isSuccess()? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }



}
