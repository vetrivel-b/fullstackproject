package com.fullstack.model;


import java.util.ArrayList;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class BookedList {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	
	private int bookingid;
	//	@NotNull
	//@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userid")
	
	//@OneToOne(cascade = CascadeType.ALL) @JoinColumn(name = "fk_user_id")
	//private Users fkuserid;
	private long fkuserid;
	private String moviename;
	private Date bookeddate;
	private String place;
	
	public boolean isAvailable(ArrayList<BookedList> bookings, ArrayList<MovieList> mvl) {
		int capacity=0;
		for(MovieList ml: mvl) {
			if(ml.getMoviename().equals(moviename))
				capacity=ml.getAvailableseats();
		}
		int booked=0;
		for(BookedList b:bookings) {
			if(b.getMoviename().equals(moviename) && b.bookeddate.equals(bookeddate)) {
				booked++;
			}
		}
		return booked<capacity?true:false;
	}
	
	
	
}
