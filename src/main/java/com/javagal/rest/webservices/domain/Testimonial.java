package com.javagal.rest.webservices.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;


@Entity
@Table(name = "Testimonial")
public class Testimonial {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@NotNull
	@ApiModelProperty(notes="Testimonial content must not be empty.")
	private String content;	
	
	@Column(name = "customer_name")
	@NotNull
	@ApiModelProperty(notes="Name must not be empty.")
	private String customerName;
	private String address;
	
	
	public Testimonial() {
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getCustomerName() {
		return customerName;
	}


	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	@Override
	public String toString() {
		return "Testimonial [id=" + id + ", content=" + content + ", customerName=" + customerName + ", address="
				+ address + "]";
	}
}
