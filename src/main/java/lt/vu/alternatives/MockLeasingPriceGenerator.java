package lt.vu.alternatives;

import lt.vu.services.PriceGenerator;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;
import java.io.Serializable;

@Alternative
public class MockLeasingPriceGenerator implements Serializable, PriceGenerator {
    public Double generateLeasingPrice(Integer _msrpPrice, Double _deposit, Integer _leaseTerm) {
        Integer price = 30000;
        Double deposit = 3000.0;
        Integer leaseTerm = 24;
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        Double monthlyLeasingPrice = (price - deposit) / leaseTerm;
        return monthlyLeasingPrice;
    }
}
