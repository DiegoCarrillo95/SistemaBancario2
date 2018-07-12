package com.diego.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Component
@Entity
@Table(name = "clients")
public class Client {

	@Id // primary key
	@GeneratedValue // auto-generated id
	private int id;
	
	private String name;

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + "]";
	}

}
