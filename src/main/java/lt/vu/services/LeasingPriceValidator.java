package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;

@ApplicationScoped
public class LeasingPriceValidator implements Serializable {
    private static final Integer MAX_TERM = 72;
    public boolean validatePrice(Integer price, Double deposit, Integer term) {
        boolean valid = true;
        if (deposit <= 0 || term <= 0) valid = false;
        if (deposit >= price) valid = false;
        if (term > MAX_TERM) valid = false;
        return valid;
    }
}
