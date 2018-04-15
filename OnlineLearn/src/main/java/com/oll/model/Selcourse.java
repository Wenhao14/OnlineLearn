package com.oll.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "selcourse")
public class Selcourse implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long scid;
  @Column(name = "uid")
  private Long uid;
  @Column(name = "cid")
  private Long cid;
  @Column(name = "chnum")
  private String chnum;
  @Column(name = "studyhour")
  private String studyhour;
  @Column(name = "studyplan")
  private String studyplan;
  @Column(name = "latestime")
  private String latestime;

  public Long getScid() {
    return scid;
  }

  public void setScid(Long scid) {
    this.scid = scid;
  }

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public Long getCid() {
    return cid;
  }

  public void setCid(Long cid) {
    this.cid = cid;
  }

  public String getChnum() {
    return chnum;
  }

  public void setChnum(String chnum) {
    this.chnum = chnum;
  }

  public String getStudyhour() {
    return studyhour;
  }

  public void setStudyhour(String studyhour) {
    this.studyhour = studyhour;
  }

  public String getStudyplan() {
    return studyplan;
  }

  public void setStudyplan(String studyplan) {
    this.studyplan = studyplan;
  }

  public String getLatestime() {
    return latestime;
  }

  public void setLatestime(String latestime) {
    this.latestime = latestime;
  }
}
