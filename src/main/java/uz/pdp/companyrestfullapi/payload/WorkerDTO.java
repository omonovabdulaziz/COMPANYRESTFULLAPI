package uz.pdp.companyrestfullapi.payload;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDTO {
    private String name;
    private String phoneNumber;
    private Integer departmentId;
    private String street;
    private Integer homeNumber;

}
