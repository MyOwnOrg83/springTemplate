package com.raj.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "chalisa_master")
public class ChalisaBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@Column(name="short_desc")
	private String shortDesc;
	
	@OneToMany(targetEntity=ChalisaContent.class, mappedBy="chalisaId", fetch=FetchType.LAZY)
	private List<ChalisaContent> mainDesc;
	
	@Column(name="icon_loc")
	private String icon;
	@Column(name="img_loc")
	private String image;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortDesc() {
		return shortDesc;
	}
	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}
	public List<ChalisaContent> getMainDesc() {
		return mainDesc;
	}
	public void setMainDesc(List<ChalisaContent> mainDesc) {
		this.mainDesc = mainDesc;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
}
