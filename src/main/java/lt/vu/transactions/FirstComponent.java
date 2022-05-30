package lt.vu.transactions;

import lt.vu.usecases.CarsForDealer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.TransactionSynchronizationRegistry;

@Named
@RequestScoped
public class FirstComponent {

    @Inject
    SecondComponent second;

    @Resource
    private TransactionSynchronizationRegistry tx;

    @Inject
    CarsForDealer carsForDealer;

//    @Transactional
//    public void createPlayerAndAssignTeam() {
//        playersForTeam.setTeam();
//        try {
//            Player player = playersForTeam.createPlayer();
//        }
//    }

    @PostConstruct
    public void init() {
        System.out.println(second.getClass().getName());
        System.out.println(toString() + " constructed.");
    }

    @PreDestroy
    public void aboutToDie() {
        System.out.println(toString() + " ready to die.");
    }
}
