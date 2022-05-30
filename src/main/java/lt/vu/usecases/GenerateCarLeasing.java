package lt.vu.usecases;

import lt.vu.alternatives.stereotypes.ValidatesMokilizingas;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.services.LeasingPriceGenerator;
import lt.vu.services.LeasingPriceValidator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class GenerateCarLeasing implements Serializable {
    @Inject
    LeasingPriceGenerator leasingPriceGenerator;
    @Inject
    @ValidatesMokilizingas
    LeasingPriceValidator leasingPriceValidator;

    private CompletableFuture<Double> leasingPriceGenerationTask = null;

    @LoggedInvocation
    public String generateNewLeasingPrice(Integer msrpPrice, Double deposit, Integer term) {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        if (leasingPriceValidator.validatePrice(msrpPrice, deposit, term)) {
            leasingPriceGenerationTask = CompletableFuture.supplyAsync(() -> leasingPriceGenerator.generateLeasingPrice(msrpPrice, deposit, term));
        }

        return  "/carDetails.xhtml?faces-redirect=true&carId=" + requestParameters.get("carId");
    }

    public boolean isLeasingPriceGenerationRunning() {
        return leasingPriceGenerationTask != null && !leasingPriceGenerationTask.isDone();
    }

    public String getLeasingPriceGenerationStatus() throws ExecutionException, InterruptedException {
        if (leasingPriceGenerationTask == null) {
            return null;
        } else if (isLeasingPriceGenerationRunning()) {
            return "Leasing generation in progress";
        }
        return "Suggested leasing price: " + leasingPriceGenerationTask.get() + " (Eur/mo.)";
    }
}
