package com.oll.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "news")
public class News implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long nid;
  @Column(name = "ntitle")
  private String ntitle;
  @Column(name = "nurl")
  private String nurl;
  @Column(name = "nupuser")
  private Long nupuser;
  @Column(name = "nupdate")
  private String nupdate;

  public Long getNid() {
    return nid;
  }

  public void setNid(Long nid) {
    this.nid = nid;
  }

  public String getNtitle() {
    return ntitle;
  }

  public void setNtitle(String ntitle) {
    this.ntitle = ntitle;
  }

  public String getNurl() {
    return nurl;
  }

  public void setNurl(String nurl) {
    this.nurl = nurl;
  }

  public Long getNupuser() {
    return nupuser;
  }

  public void setNupuser(Long nupuser) {
    this.nupuser = nupuser;
  }

  public String getNupdate() {
    return nupdate;
  }

  public void setNupdate(String nupdate) {
    this.nupdate = nupdate;
  }
}
