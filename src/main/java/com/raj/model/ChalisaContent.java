package com.raj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chalisa_content")
public class ChalisaContent {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name="chalisa_master_id")
	private Long chalisaId;
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
	public Long getChalisaId() {
		return chalisaId;
	}
	public void setChalisaId(Long chalisaId) {
		this.chalisaId = chalisaId;
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
