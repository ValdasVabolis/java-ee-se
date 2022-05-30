package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class LeasingPriceGenerator implements Serializable, PriceGenerator {

    public Double generateLeasingPrice(Integer msrpPrice, Double deposit, Integer leaseTerm) {
        Integer price = msrpPrice;
        try {
            Thread.sleep(3000); // Simulate intensive work
        } catch (InterruptedException e) {
        }
        Double monthlyLeasingPrice = (price - deposit) / leaseTerm;
        return monthlyLeasingPrice;
    }
}