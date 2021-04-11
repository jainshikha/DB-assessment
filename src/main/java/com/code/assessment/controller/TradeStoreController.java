package com.code.assessment.controller;

import com.code.assessment.beans.Trade;
import com.code.assessment.service.TradeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TradeStoreController {
  @Autowired TradeOrderService tradeOrderService;

  // get all trade order
  @GetMapping("/tradeOrder")
  public List<Trade> findAllTrades() {
    return tradeOrderService.findAll();
  }

  // should use for insert operation
  /* @PostMapping("/tradeOrder")
  public ResponseEntity<String> tradeOrderInsert(@RequestBody Trade trade) {
    String msgFromValidationServcie = tradeOrderService.createAndUpdateTrade(trade);
    System.out.println("msgFromValidationServcie " + msgFromValidationServcie);
    if (msgFromValidationServcie.contains("successfully")) {
      return ResponseEntity.status(HttpStatus.OK).build();
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msgFromValidationServcie);
  }*/

  @PutMapping("/tradeOrder")
  public ResponseEntity<String> tradeOrderUpdate(@RequestBody Trade trade) {
    String msgFromValidationServcie = tradeOrderService.createAndUpdateTrade(trade);

    if (msgFromValidationServcie.contains("successfully")) {
      return ResponseEntity.status(HttpStatus.OK).build();
    }
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msgFromValidationServcie);
  }
}
