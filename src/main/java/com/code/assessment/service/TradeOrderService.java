package com.code.assessment.service;

import com.code.assessment.beans.Trade;
import com.code.assessment.exception.InvalidTradeException;
import com.code.assessment.repo.TradeOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TradeOrderService {

  @Autowired TradeOrderRepository tradeRepository;

  // returns all trades
  public List<Trade> findAll() {
    return tradeRepository.findAll();
  }

  // check if trade already exist
  private Optional<Trade> tradeExists(String id) {
    return tradeRepository.findById(id);
  }

  public String createAndUpdateTrade(Trade trade) {
    String response = "";
    Optional<Trade> existingTrade = tradeExists(trade.getTradeId());
    try {
      if (existingTrade.isPresent()) {
        // version should true
        if (validateVersion(trade, existingTrade.get())) {
          // maturity could be true false
          if (isMaturityDateExpired(trade)) {
            trade.setExpiredFlag("Y");
          }
          trade.setExpiredFlag("N");
          persist(trade);
          response = "record updated successfully";
        } else {
          response = "increase the version";
        }
      } // new record
      else {
        if (isMaturityDateExpired(trade)) {
          response = "Trade already expired";
        } else {
          trade.setExpiredFlag("N");
          persist(trade);
          response = "new trade created successfully ";
        }
      }

    } catch (Exception e) {
      throw new InvalidTradeException("Exception is , cauase : " + e.getMessage());
    }

    return response;
  }

  public boolean validateVersion(Trade trade, Trade oldTrade) {
    // condition 1 version check
    return trade.getVersion() > oldTrade.getVersion();
  }

  // check if maturity date is today condition 2
  public boolean isMaturityDateExpired(Trade trade) {
    return trade.getMaturityDate().isBefore(Instant.now());
  }

  // persist operations
  public void persist(Trade trade) {
    trade.setCreatedDate(Instant.now());
    tradeRepository.save(trade);
  }
}
