package uz.pdp.companyrestfullapi.controller;


import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.companyrestfullapi.entity.Worker;
import uz.pdp.companyrestfullapi.payload.Result;
import uz.pdp.companyrestfullapi.payload.WorkerDTO;
import uz.pdp.companyrestfullapi.repository.WorkerRepository;
import uz.pdp.companyrestfullapi.service.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    WorkerService workerService;

    //create
    @PostMapping
    public ResponseEntity<Result> addWorker(@RequestBody WorkerDTO workerDTO) {
        Result result = workerService.addWorker(workerDTO);
        return ResponseEntity.status(result.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(result);
    }

    //read
    @GetMapping
    public List<Worker> getWorkers() {
        return workerService.getWorkers();
    }


    //read2
    @GetMapping("/byId/{id}")
    public Worker getWorker(@PathVariable Integer id) {
        return workerService.getWorker(id);
    }

    //update
    @PutMapping("/byId/{id}")
    public ResponseEntity<Result> editWorker(@PathVariable Integer id  , @RequestBody WorkerDTO workerDTO ){
        Result result = workerService.editWorker(id , workerDTO);
        return ResponseEntity.status(result.isSuccess()?HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(result);
    }

    //delete
    @DeleteMapping("/byId/{id}")
    public ResponseEntity<Result> deleteWorker(@PathVariable Integer id){
        Result result = workerService.deleteWorker(id);
        return ResponseEntity.status(result.isSuccess()?  HttpStatus.ACCEPTED  : HttpStatus.CONFLICT).body(result);
    }

}
