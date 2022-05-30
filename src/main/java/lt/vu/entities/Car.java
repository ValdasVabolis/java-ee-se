package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Car.findAll", query = "select a from Car as a")
})
@Table(name = "CAR")
@Getter @Setter
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @Column(name = "MAKE")
    private String make;

    @Size(max = 50)
    @Column(name = "MODEL")
    private String model;

    @Column(name = "LEASING_PRICE")
    private Integer leasingPrice;

    @Column(name = "PRICE")
    private Integer price;

    @ManyToOne
    @JoinColumn(name="DEALER_ID")
    private Dealer dealer;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer version;

    public Car() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(id, car.id) &&
                Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model);
    }
}
