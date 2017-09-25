package com.raj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pp_profile")
public class PanditProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name="fname")
	private String fName;
	@Column(name="lname")
	private String lName;
	@Column(name="edu")
	private String edu;
	@Column(name="mobile")
	private String mobile;
	@Column(name="profile_pic_loc")
	private String picLoc;
	
	
	public Long getId() {
		return id;
	}
	public String getfName() {
		return fName;
	}
	public void setfName(String fName) {
		this.fName = fName;
	}
	public String getlName() {
		return lName;
	}
	public void setlName(String lName) {
		this.lName = lName;
	}
	public String getEdu() {
		return edu;
	}
	public void setEdu(String edu) {
		this.edu = edu;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getPicLoc() {
		return picLoc;
	}
	public void setPicLoc(String picLoc) {
		this.picLoc = picLoc;
	}
}
