package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "Dealer.findAll", query = "select d from Dealer as d"),
        @NamedQuery(name = "Dealer.findByName", query = "select d from Dealer as d where d.name LIKE :dealerName")
})
@Table(name = "DEALER")
@Getter @Setter
public class Dealer {

    public Dealer(){

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "dealer")
    private List<Car> cars = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dealer dealer = (Dealer) o;
        return Objects.equals(name, dealer.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
