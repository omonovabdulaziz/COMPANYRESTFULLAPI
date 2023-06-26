package uz.pdp.companyrestfullapi.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.companyrestfullapi.entity.Address;
import uz.pdp.companyrestfullapi.entity.Department;
import uz.pdp.companyrestfullapi.entity.Worker;
import uz.pdp.companyrestfullapi.payload.Result;
import uz.pdp.companyrestfullapi.payload.WorkerDTO;
import uz.pdp.companyrestfullapi.repository.AddressRepository;
import uz.pdp.companyrestfullapi.repository.DepartmentRepository;
import uz.pdp.companyrestfullapi.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    //create

    public Result addWorker( WorkerDTO workerDTO) {
        if (workerRepository.existsByPhoneNumber(workerDTO.getPhoneNumber())){
            return new Result("bunday phone numberli user mavjud" , false);
        }

        Address address = new Address();
        address.setStreet(workerDTO.getStreet());
        address.setHomeNumber(workerDTO.getHomeNumber());
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDTO.getDepartmentId());
        if (optionalDepartment.isEmpty())
            return new Result("topilmadi department" , false);

        Worker worker = new Worker();
        worker.setAddress(address);
        worker.setName(worker.getName());
        worker.setDepartment(optionalDepartment.get());
        worker.setPhoneNumber(workerDTO.getPhoneNumber());
        workerRepository.save(worker);
        return new Result("saved" , true);
    }

    //read

    public List<Worker> getWorkers() {
        return workerRepository.findAll();
    }


    //read2
    public Worker getWorker( Integer id) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        return optionalWorker.orElseGet(Worker::new);

    }

    //update
    public Result editWorker(Integer id , WorkerDTO workerDTO ){
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty())
            return new Result("bunday id li worker topilmadi"  , false);
if (workerRepository.existsByPhoneNumber(workerDTO.getPhoneNumber()))
    return new Result("bunday nomer bor user mavjud " , false);

        Worker worker = optionalWorker.get();
        worker.setPhoneNumber(workerDTO.getPhoneNumber());
        worker.setId(id);
        Address address = new Address();
        address.setHomeNumber(workerDTO.getHomeNumber());
        address.setStreet(workerDTO.getStreet());
        worker.setAddress(address);
        worker.setName(workerDTO.getName());
        Optional<Department> optionalDepartment = departmentRepository.findById(workerDTO.getDepartmentId());
        worker.setDepartment(optionalDepartment.get());
        workerRepository.save(worker);
           return new Result("updated " , true);
    }

    //delete
    public Result deleteWorker( Integer id){
        try {
            departmentRepository.deleteById(id);
            return new Result("deleted" , true);
        }catch (Exception e){
            e.printStackTrace();
            return new Result("error!!!" , false);
        }

    }

}
