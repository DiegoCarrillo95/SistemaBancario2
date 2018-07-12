package com.diego.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@Component
@Entity
@Table(name = "accounts")
public class Account {
	
	@Id // primary key
	@GeneratedValue // auto-generated id
	private int id;
	
	private int clientId;
	private double balance; 
	
	public Account(int id, int clientId) {
		this.id = id;
		this.clientId = clientId;
		this.balance = 0;
	}
	
	
	
}
