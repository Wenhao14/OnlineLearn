package com.oll.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
@JsonIgnoreProperties({ "handler","hibernateLazyInitializer" })
public class User implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long uid;
  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;
  @Column(name = "grade")
  private String grade;
  @Column(name = "isdel")
  private String isdel;
  @Column(name = "headimg")
  private String headimg;

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public String getIsdel() {
    return isdel;
  }

  public void setIsdel(String isdel) {
    this.isdel = isdel;
  }

  public String getHeadimg() {
    return headimg;
  }

  public void setHeadimg(String headimg) {
    this.headimg = headimg;
  }
}
