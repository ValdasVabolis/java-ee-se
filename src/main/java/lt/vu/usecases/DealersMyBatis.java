package lt.vu.usecases;

import lombok.Getter;
import lombok.Setter;
import lt.vu.mybatis.dao.DealerMapper;
import lt.vu.mybatis.model.Dealer;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class DealersMyBatis {
    @Inject
    private DealerMapper dealerMapper;

    @Getter
    private List<Dealer> allDealers;

    @Getter @Setter
    private Dealer dealerToCreate = new Dealer();

    @PostConstruct
    public void init() {
        this.loadAllDealers();
    }

    private void loadAllDealers() {
        this.allDealers = dealerMapper.selectAll();
    }

    @Transactional
    public String createDealer() {
        dealerMapper.insert(dealerToCreate);
        return "/myBatis/dealers?faces-redirect=true";
    }
}
