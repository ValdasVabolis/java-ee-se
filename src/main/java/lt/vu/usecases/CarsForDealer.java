package lt.vu.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Car;
import lt.vu.interceptors.LoggedInvocation;
import lt.vu.persistence.CarsDAO;
import lt.vu.persistence.DealersDAO;
import lt.vu.entities.Dealer;

@Model
public class CarsForDealer implements Serializable {

    @Inject
    private DealersDAO dealersDAO;

    @Inject
    private CarsDAO carsDAO;

    @Getter @Setter
    private Dealer dealer;

    @Getter @Setter
    private Car carToCreate = new Car();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        System.out.println(requestParameters);
        Integer dealerId = Integer.parseInt(requestParameters.get("dealerId"));
        this.dealer = dealersDAO.findOne(dealerId);
    }

    @Transactional
    @LoggedInvocation
    public void createCar() {
        carToCreate.setDealer(this.dealer);
        carsDAO.persist(carToCreate);
    }
}
