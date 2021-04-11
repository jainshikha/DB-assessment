package com.code.assessment;

import com.code.assessment.beans.Trade;
import com.code.assessment.controller.TradeStoreController;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TradeOrderServiceTest {

  @Autowired private TradeStoreController tradeStoreController;

  @Test
  void contextLoads() {}

  @Test
  void testCreateAndUpdateTradeSucc() {
    ResponseEntity responseEntity =
        tradeStoreController.tradeOrderUpdate(
            createTrade("T1", 1, Instant.now().plus(365, ChronoUnit.DAYS)));
    Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build(), responseEntity);
    List<Trade> tradeList = tradeStoreController.findAllTrades();
    Assertions.assertEquals(1, tradeList.size());
    Assertions.assertEquals("T1", tradeList.get(0).getTradeId());
  }

  @Test
  void testIsMaturityDateExpired() {
    Instant localDate = Instant.parse("2015-05-21T00:00:00.001Z");
    ResponseEntity responseEntity =
        tradeStoreController.tradeOrderUpdate(createTrade("T2", 1, localDate));
  }

  @Test
  void testValidateWithOldVersion() {
    ResponseEntity responseEntity =
        tradeStoreController.tradeOrderUpdate(
            createTrade("T1", 2, Instant.now().plus(365, ChronoUnit.DAYS)));
    Assertions.assertEquals(ResponseEntity.status(HttpStatus.OK).build(), responseEntity);
    List<Trade> tradeList = tradeStoreController.findAllTrades();

    Assertions.assertEquals("T1", tradeList.get(0).getTradeId());
    Assertions.assertEquals(2, tradeList.get(0).getVersion());

    ResponseEntity responseEntity1 =
        tradeStoreController.tradeOrderUpdate(
            createTrade("T1", 3, Instant.now().plus(365, ChronoUnit.DAYS)));
    List<Trade> tradeList1 = tradeStoreController.findAllTrades();

    Assertions.assertEquals("T1", tradeList1.get(0).getTradeId());
  }

  @Test
  void testTradeValidateAndStoreWhenSameVersionTrade() {
    ResponseEntity responseEntity =
        tradeStoreController.tradeOrderUpdate(createTrade("T2", 2, Instant.now()));
    System.out.println(
        "ResponseEntity.status(HttpStatus.BAD_REQUEST).build().getStatusCode() "
            + ResponseEntity.status(HttpStatus.BAD_REQUEST).build().getStatusCode());
    System.out.println("responseEntity.getStatusCode() " + responseEntity.getStatusCode());
    Assertions.assertEquals(
        ResponseEntity.status(HttpStatus.BAD_REQUEST).build().getStatusCode(),
        responseEntity.getStatusCode());
  }

  private Trade createTrade(String tradeId, int version, Instant maturityDate) {
    Trade trade = new Trade();
    trade.setTradeId(tradeId);
    trade.setBookId(tradeId + "B1");
    trade.setVersion(version);
    trade.setCounterPartyId(tradeId + "Cpty");
    trade.setMaturityDate(maturityDate);
    trade.setExpiredFlag("Y");
    return trade;
  }
}
