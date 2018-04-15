package com.oll.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "answer")
public class Answer implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long aid;
  @Column(name = "uid")
  private Long uid;
  @Column(name = "tpid")
  private Long tpid;
  @Column(name = "agrade")
  private String agrade;
  @Column(name = "adate")
  private String adate;

  public Long getAid() {
    return aid;
  }

  public void setAid(Long aid) {
    this.aid = aid;
  }

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public Long getTpid() {
    return tpid;
  }

  public void setTpid(Long tpid) {
    this.tpid = tpid;
  }

  public String getAgrade() {
    return agrade;
  }

  public void setAgrade(String agrade) {
    this.agrade = agrade;
  }

  public String getAdate() {
    return adate;
  }

  public void setAdate(String adate) {
    this.adate = adate;
  }
}
