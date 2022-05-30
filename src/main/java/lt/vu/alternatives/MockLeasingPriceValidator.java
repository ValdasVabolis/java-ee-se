package lt.vu.alternatives;

import lt.vu.services.LeasingPriceValidator;

import javax.enterprise.inject.Specializes;

@Specializes
public class MockLeasingPriceValidator extends LeasingPriceValidator {
    @Override
    public boolean validatePrice(Integer price, Double deposit, Integer term) {
        return true;
    }
}
