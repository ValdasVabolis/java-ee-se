package lt.vu.services;

public interface PriceGenerator {
    Double generateLeasingPrice(Integer msrp, Double deposit, Integer term);
}
