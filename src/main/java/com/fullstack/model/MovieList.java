package com.fullstack.model;




import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="movielist")
public class MovieList {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long movieid;
//	@ManyToMany(cascade= { CascadeType.ALL})
//	@JoinTable(name="moviename",
//	joinColumns= {
//			@JoinColumn(name="")
//	},
//	inverseJoinColumns ={
//			@JoinColumn(name="")
//	
//	}
//	)
	
	private String moviename;
	private Date bookingdate;
	private String place;
	private int availableseats;
}
