package com.oll.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "notice")
public class Notice implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long ntid;
  @Column(name = "nttitle")
  private String nttitle;
  @Column(name = "ntcontent")
  private String ntcontent;
  @Column(name = "ntupuser")
  private Long ntupuser;
  @Column(name = "ntupdate")
  private String ntupdate;

  public Long getNtid() {
    return ntid;
  }

  public void setNtid(Long ntid) {
    this.ntid = ntid;
  }

  public String getNttitle() {
    return nttitle;
  }

  public void setNttitle(String nttitle) {
    this.nttitle = nttitle;
  }

  public String getNtcontent() {
    return ntcontent;
  }

  public void setNtcontent(String ntcontent) {
    this.ntcontent = ntcontent;
  }

  public Long getNtupuser() {
    return ntupuser;
  }

  public void setNtupuser(Long ntupuser) {
    this.ntupuser = ntupuser;
  }

  public String getNtupdate() {
    return ntupdate;
  }

  public void setNtupdate(String ntupdate) {
    this.ntupdate = ntupdate;
  }
}
