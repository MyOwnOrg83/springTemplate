package com.raj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "aarti_content")
public class AartiContent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="aarti_master_id")
	private Long aartiId;
	private String lang;
	@Column(name="content")
	private String content;
	@Column(name="pref_lang")
	private boolean prefLang;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAartiId() {
		return aartiId;
	}
	public void setAartiId(Long aartiId) {
		this.aartiId = aartiId;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isPrefLang() {
		return prefLang;
	}
	public void setPrefLang(boolean prefLang) {
		this.prefLang = prefLang;
	}
}
