package com.oll.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "classhour")
public class Classhour implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long chid;
  @Column(name = "cid")
  private Long cid;
  @Column(name = "cname")
  private String cname;
  @Column(name = "churl")
  private String churl;

  public Long getChid() {
    return chid;
  }

  public void setChid(Long chid) {
    this.chid = chid;
  }

  public Long getCid() {
    return cid;
  }

  public void setCid(Long cid) {
    this.cid = cid;
  }

  public String getCname() {
    return cname;
  }

  public void setCname(String cname) {
    this.cname = cname;
  }

  public String getChurl() {
    return churl;
  }

  public void setChurl(String churl) {
    this.churl = churl;
  }
}
