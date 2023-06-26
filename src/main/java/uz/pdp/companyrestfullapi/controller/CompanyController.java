package uz.pdp.companyrestfullapi.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyrestfullapi.entity.Company;
import uz.pdp.companyrestfullapi.payload.CompanyDTO;
import uz.pdp.companyrestfullapi.payload.Result;
import uz.pdp.companyrestfullapi.service.CompanyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    CompanyService companyService;


    //CREATE
    @PostMapping
    public ResponseEntity<Result> addCompany(@Valid @RequestBody CompanyDTO companyDTO) {
        Result result = companyService.addCompany(companyDTO);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(result);
    }

    //READD
    @GetMapping
    public ResponseEntity<List<Company>> getCompanies() {
        List<Company> companies = companyService.getCompanies();
        return ResponseEntity.status(200).body(companies);
    }

    //READ1
    @GetMapping("/byId/{id}")
    public ResponseEntity<Company> getCompany(@PathVariable Integer id) {
        Company company = companyService.getCompany(id);
        return ResponseEntity.status(200).body(company);
    }


    //UPDATE
    @PutMapping("/byId/{id}")
    public ResponseEntity<Result> updateCompany(@PathVariable Integer id, @RequestBody CompanyDTO companyDTO) {
        Result result = companyService.updateCompany(id, companyDTO);
        return ResponseEntity.status(result.isSuccess() ? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }


    //DELETE
    @DeleteMapping("/byId/{id}")
    public ResponseEntity<Result> deleteCompany(@PathVariable Integer id){
        Result result = companyService.deleteCompany(id);
        return ResponseEntity.status(result.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(result);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
