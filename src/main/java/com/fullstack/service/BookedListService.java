package com.fullstack.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fullstack.exception.ResourceNotFoundException;
import com.fullstack.model.BookedList;
import com.fullstack.model.MovieList;

import com.fullstack.repository.BookedListRepository;

@Service
public class BookedListService {

	@Autowired
	BookedListRepository bookedListrepo;
	
	@Autowired
	MovieListService mvlist;
	
	MovieList movielist=new MovieList();
	int n=movielist.getAvailableseats();
	ArrayList<BookedList> bookings=new ArrayList<BookedList>();
	ArrayList<MovieList> mvl=new ArrayList<MovieList>();
	
	public BookedList addNewBooking(BookedList bookedlist) {
		
		if(bookedlist.isAvailable(bookings,mvl)) {
			return bookedListrepo.save(bookedlist);
		}
		System.out.println("else part working");
		return bookedListrepo.save(bookedlist);
//		else {
//			
//		}
		
		
//		if(movielist.getAvailableseats() <=0) {
//			return null;
//		}
//		movielist.setAvailableseats(n-1);
	//	return bookedListrepo.save(bookedlist);
	}

	public ResponseEntity<BookedList> deleteById(int bookingid) {
		BookedList selectBooking=bookedListrepo.findById(bookingid)
				.orElseThrow(() -> new ResourceNotFoundException("bookingList not found with this Id"+bookingid));
		bookedListrepo.delete(selectBooking);
		movielist.setAvailableseats(n+1);
		return ResponseEntity.ok().build();
	}


//	public ResponseEntity<BookedList> addOneBooking(BookedList bookedList) {
//		
//		MovieList movielist=new MovieList();
//		
//		if(movielist.getAvailableseats() >0) {
//			System.out.println("seats are available");
//			 bookedListrepo.save(bookedList);
//			return ResponseEntity.ok().build();
//			//return bookedListrepo.findById(bookedList.getTransactionId()).get();
//		}
//		return null;
//		
//	}

}
