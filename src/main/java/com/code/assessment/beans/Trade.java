package com.code.assessment.beans;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "OrderBook")
public class Trade {

  @Id private String tradeId;

  private int version;
  private String counterPartyId;
  private String bookId;
  private Instant maturityDate;
  private Instant createdDate;
  private String expiredFlag;

  public Trade(
      String tradeId,
      int version,
      String counterPartyId,
      String bookId,
      Instant maturityDate,
      Instant createdDate,
      String expiredFlag) {
    this.tradeId = tradeId;
    this.version = version;
    this.counterPartyId = counterPartyId;
    this.bookId = bookId;
    this.maturityDate = maturityDate;
    this.createdDate = createdDate;
    this.expiredFlag = expiredFlag;
  }

  public String getTradeId() {
    return tradeId;
  }

  public void setTradeId(String tradeId) {
    this.tradeId = tradeId;
  }

  public int getVersion() {
    return version;
  }

  public void setVersion(int version) {
    this.version = version;
  }

  public String getCounterPartyId() {
    return counterPartyId;
  }

  public void setCounterPartyId(String counterPartyId) {
    this.counterPartyId = counterPartyId;
  }

  public String getBookId() {
    return bookId;
  }

  public void setBookId(String bookId) {
    this.bookId = bookId;
  }

  public Instant getMaturityDate() {
    return maturityDate;
  }

  public void setMaturityDate(Instant maturityDate) {
    this.maturityDate = maturityDate;
  }

  public Instant getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Instant createdDate) {
    this.createdDate = createdDate;
  }

  public String getExpiredFlag() {
    return expiredFlag;
  }

  public void setExpiredFlag(String expiredFlag) {
    this.expiredFlag = expiredFlag;
  }

  @Override
  public String toString() {
    return "Trade{"
        + "tradeId='"
        + tradeId
        + '\''
        + ", version="
        + version
        + ", counterPartyId='"
        + counterPartyId
        + '\''
        + ", bookId='"
        + bookId
        + '\''
        + ", maturityDate="
        + maturityDate
        + ", createdDate="
        + createdDate
        + ", expiredFlag='"
        + expiredFlag
        + '\''
        + '}';
  }
}
