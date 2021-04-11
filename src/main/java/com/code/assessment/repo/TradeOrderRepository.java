package com.code.assessment.repo;

import com.code.assessment.beans.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeOrderRepository extends JpaRepository<Trade,String> {
}
