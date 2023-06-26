package uz.pdp.companyrestfullapi.payload;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDTO {
    @NotNull(message = "corparatsiya nomi bosh bolishi mumkin emas")
    private String corpName;
    @NotNull(message = "direktor nomi bosh bolishi mumkin emas")
    private String directorName;
    @NotNull(message = "address bosh bolishi mumkin emas")
    private String street;
    @NotNull(message = "address bosh bolishi mumkin emas")
    private Integer homeNumber;
}
