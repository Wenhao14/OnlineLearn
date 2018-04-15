package com.oll.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "course")
public class Course implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cid;
  @Column(name = "cname")
  private String cname;
  @Column(name = "ckeyspeaker")
  private String ckeyspeaker;
  @Column(name = "mid")
  private Long mid;
  @Column(name = "cispush")
  private String cispush;
  @Column(name = "cseltime")
  private Long cseltime;
  @Column(name = "cimg")
  private String cimg;
  @Column(name = "ccontent")
  private String ccontent;
  @Column(name = "cisdel")
  private String cisdel;
  @Column(name = "cupuser")
  private Long cupuser;
  @Column(name = "cupdate")
  private String cupdate;

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

  public String getCkeyspeaker() {
    return ckeyspeaker;
  }

  public void setCkeyspeaker(String ckeyspeaker) {
    this.ckeyspeaker = ckeyspeaker;
  }

  public Long getMid() {
    return mid;
  }

  public void setMid(Long mid) {
    this.mid = mid;
  }

  public String getCispush() {
    return cispush;
  }

  public void setCispush(String cispush) {
    this.cispush = cispush;
  }

  public Long getCseltime() {
    return cseltime;
  }

  public void setCseltime(Long cseltime) {
    this.cseltime = cseltime;
  }

  public String getCimg() {
    return cimg;
  }

  public void setCimg(String cimg) {
    this.cimg = cimg;
  }

  public String getCcontent() {
    return ccontent;
  }

  public void setCcontent(String ccontent) {
    this.ccontent = ccontent;
  }

  public String getCisdel() {
    return cisdel;
  }

  public void setCisdel(String cisdel) {
    this.cisdel = cisdel;
  }

  public Long getCupuser() {
    return cupuser;
  }

  public void setCupuser(Long cupuser) {
    this.cupuser = cupuser;
  }

  public String getCupdate() {
    return cupdate;
  }

  public void setCupdate(String cupdate) {
    this.cupdate = cupdate;
  }
}
