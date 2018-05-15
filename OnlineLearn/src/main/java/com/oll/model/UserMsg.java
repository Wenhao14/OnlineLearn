package com.oll.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "usermsg")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class UserMsg implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long uid;
  @Column(name = "uname")
  private String uname;
  @Column(name = "uphone")
  private String uphone;
  @Column(name = "umail")
  private String umail;
  @Column(name = "usection")
  private String usection;
  @Column(name = "ugoal")
  private Long ugoal;
  @Column(name = "urank")
  private String urank;

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public String getUname() {
    return uname;
  }

  public void setUname(String uname) {
    this.uname = uname;
  }

  public String getUphone() {
    return uphone;
  }

  public void setUphone(String uphone) {
    this.uphone = uphone;
  }

  public String getUmail() {
    return umail;
  }

  public void setUmail(String umail) {
    this.umail = umail;
  }

  public String getUsection() {
    return usection;
  }

  public void setUsection(String usection) {
    this.usection = usection;
  }

  public Long getUgoal() {
    return ugoal;
  }

  public void setUgoal(Long ugoal) {
    this.ugoal = ugoal;
  }

  public String getUrank() {
    return urank;
  }

  public void setUrank(String urank) {
    this.urank = urank;
  }
}
