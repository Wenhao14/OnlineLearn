package com.oll.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "testpaper")
public class Testpaper implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long tpid;
  @Column(name = "tpname")
  private String tpname;
  @Column(name = "tpupuser")
  private Long tpupuser;
  @Column(name = "tpupdate")
  private String tpupdate;
  @Column(name = "tpdescribe")
  private String tpdescribe;
  @Column(name = "tppassdate")
  private java.sql.Timestamp tppassdate;
  @Column(name = "toisdel")
  private String tpisdel;
  @Column(name = "tpcontent")
  private String tpcontent;

  public Long getTpid() {
    return tpid;
  }

  public void setTpid(Long tpid) {
    this.tpid = tpid;
  }

  public String getTpname() {
    return tpname;
  }

  public void setTpname(String tpname) {
    this.tpname = tpname;
  }

  public Long getTpupuser() {
    return tpupuser;
  }

  public void setTpupuser(Long tpupuser) {
    this.tpupuser = tpupuser;
  }

  public String getTpupdate() {
    return tpupdate;
  }

  public void setTpupdate(String tpupdate) {
    this.tpupdate = tpupdate;
  }

  public String getTpdescribe() {
    return tpdescribe;
  }

  public void setTpdescribe(String tpdescribe) {
    this.tpdescribe = tpdescribe;
  }

  public java.sql.Timestamp getTppassdate() {
    return tppassdate;
  }

  public void setTppassdate(java.sql.Timestamp tppassdate) {
    this.tppassdate = tppassdate;
  }

  public String getTpisdel() {
    return tpisdel;
  }

  public void setTpisdel(String tpisdel) {
    this.tpisdel = tpisdel;
  }

  public String getTpcontent() {
    return tpcontent;
  }

  public void setTpcontent(String tpcontent) {
    this.tpcontent = tpcontent;
  }
}
