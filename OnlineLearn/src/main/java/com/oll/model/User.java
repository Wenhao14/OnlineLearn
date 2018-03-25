package com.oll.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user")
public class User implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column(name = "username")
  private String username;
  @Column(name = "password")
  private String password;
  @Column(name = "scale")
  private Integer scale;
  @Column(name = "headimg")
  private String headimg;
  @Column(name = "registdate")
  private Date registdate;
  @Column(name = "realname")
  private String realname;
  @Column(name = "email")
  private String email;
  @Column(name = "phone")
  private String phone;
  @Column(name = "feeling")
  private String feeling;
  @Column(name = "department")
  private String department;
  @Column(name = "integral")
  private Integer integral;
  @Column(name = "learnmin")
  private Integer learnmin;
  @Column(name = "ranking")
  private String ranking;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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

  public Integer getScale() {
    return scale;
  }

  public void setScale(Integer scale) {
    this.scale = scale;
  }

  public String getHeadimg() {
    return headimg;
  }

  public void setHeadimg(String headimg) {
    this.headimg = headimg;
  }

  public Date getRegistdate() {
    return registdate;
  }

  public void setRegistdate(Date registdate) {
    this.registdate = registdate;
  }

  public String getRealname() {
    return realname;
  }

  public void setRealname(String realname) {
    this.realname = realname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFeeling() {
    return feeling;
  }

  public void setFeeling(String feeling) {
    this.feeling = feeling;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public Integer getIntegral() {
    return integral;
  }

  public void setIntegral(Integer integral) {
    this.integral = integral;
  }

  public Integer getLearnmin() {
    return learnmin;
  }

  public void setLearnmin(Integer learnmin) {
    this.learnmin = learnmin;
  }

  public String getRanking() {
    return ranking;
  }

  public void setRanking(String ranking) {
    this.ranking = ranking;
  }
}
