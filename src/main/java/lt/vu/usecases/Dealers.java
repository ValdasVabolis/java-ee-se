package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.entities.Dealer;
import lt.vu.persistence.DealersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Dealers {

    @Inject
    private DealersDAO dealersDAO;

    @Getter @Setter
    private Dealer dealerToCreate = new Dealer();

    @Getter
    private List<Dealer> allDealers;

    @PostConstruct
    public void init(){
        loadAllDealers();
    }

    @Transactional
    public void createDealer(){
        this.dealersDAO.persist(dealerToCreate);
    }

    private void loadAllDealers(){
        this.allDealers = dealersDAO.loadAll();
    }
}
