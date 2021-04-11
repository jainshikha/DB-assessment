package com.code.assessment;

import com.code.assessment.beans.Trade;
import com.code.assessment.repo.TradeOrderRepository;
import com.code.assessment.service.TradeOrderService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Instant;
import java.util.List;

@DataJpaTest
@RunWith(SpringRunner.class)
class TradeOrderServiceTest {

  @Autowired TestEntityManager entityManager;

  @MockBean TradeOrderService tradeOrderService;
  @Autowired TradeOrderRepository tradeOrderRepository;

  public Trade getTrade() {
    Trade trade =
        new Trade(
            "t1",
            4,
            "cp4",
            "b1",
            Instant.parse("2021-04-18T00:00:00.001Z"),
            Instant.parse("2021-04-09T00:00:00.001Z"),
            "N");
    return trade;
  }
  /** Method: findAll() */
  @Test
  void testFindAll() {
    Trade t = getTrade();
    Trade savedTrade = entityManager.persist(t);
    System.out.println("*-****** " + savedTrade);
    List<Trade> getFromDb = tradeOrderRepository.findAll();
    System.out.println("list is " + getFromDb);

    /*System.out.println("tradeOrderService.findAll() " + tradeOrderRepository.findAll());
    List<Trade> TradeList = tradeOrderRepository.findAll();
    System.out.println("TradeList TradeList " + TradeList);*/

    /*
    Trade newTrade =
        new Trade(
            "t1",
            3,
            "cp4",
            "b1",
            Instant.parse("2021-04-18T00:00:00.001Z"),
            Instant.parse("2021-04-09T00:00:00.001Z"),
            "N");
    Trade oldTrade =
        new Trade(
            "t1",
            4,
            "cp4",
            "b1",
            Instant.parse("2021-04-18T00:00:00.001Z"),
            Instant.parse("2021-04-09T00:00:00.001Z"),
            "N");
    System.out.println("-------------------"+tradeOrderService.validateVersion(newTrade, oldTrade));

    assertEquals(2 + 1, 3);*/
  }
  /*
   */
  /** Method: createAndUpdateTrade(Trade trade) */
  /*
  @Test
  public void testCreateAndUpdateTrade() throws Exception {
  }

  */
  /** Method: tradeExists(String id) */
  /*
  @Test
  public void testTradeExists() throws Exception {}

  */
  /** Method: validateVersion(Trade trade, Trade oldTrade) */
  /*
  @Test
  public void testValidateVersion() throws Exception {}

  */
  /** Method: isMaturityDateExpired(Trade trade) */
  /*
  @Test
  public void testIsMaturityDateExpired() throws Exception {}

  */
  /** Method: persist(Trade trade) */
  /*
  @Test
  public void testPersist() throws Exception {}*/
}
