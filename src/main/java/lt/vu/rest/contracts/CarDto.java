package lt.vu.rest.contracts;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CarDto {
    public String Make;
    public String Model;
    public Integer Price;
    public Integer LeasingPrice;
    public String DealerName;
}
