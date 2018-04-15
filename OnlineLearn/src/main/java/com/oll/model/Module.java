package com.oll.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "module")
public class Module implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long mid;
  @Column(name = "mname")
  private String mname;
  @Column(name = "mdescribe")
  private String mdescribe;
  @Column(name = "mupuser")
  private Long mupuser;
  @Column(name = "mupdate")
  private String mupdate;

  public Long getMid() {
    return mid;
  }

  public void setMid(Long mid) {
    this.mid = mid;
  }

  public String getMname() {
    return mname;
  }

  public void setMname(String mname) {
    this.mname = mname;
  }

  public String getMdescribe() {
    return mdescribe;
  }

  public void setMdescribe(String mdescribe) {
    this.mdescribe = mdescribe;
  }

  public Long getMupuser() {
    return mupuser;
  }

  public void setMupuser(Long mupuser) {
    this.mupuser = mupuser;
  }

  public String getMupdate() {
    return mupdate;
  }

  public void setMupdate(String mupdate) {
    this.mupdate = mupdate;
  }
}
