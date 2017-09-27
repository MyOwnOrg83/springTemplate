package com.raj.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "category_master")
public class CategoryBase {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String type;
	/*@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="parent_id")
	private CategoryBase parent;
	@OneToMany(mappedBy="parent")
	private Set<CategoryBase> child = new HashSet<>();
	@Column(name="disp_indicator")
	private boolean disp;*/
	
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
	/*public CategoryBase getParent() {
		return parent;
	}
	public void setParent(CategoryBase parent) {
		this.parent = parent;
	}
	public Set<CategoryBase> getChild() {
		return child;
	}
	public void setChild(Set<CategoryBase> child) {
		this.child = child;
	}
	public boolean isDisp() {
		return disp;
	}
	public void setDisp(boolean disp) {
		this.disp = disp;
	}*/
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
