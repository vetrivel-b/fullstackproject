package com.fullstack.controller;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;


import com.fullstack.model.BookedList;

import com.fullstack.service.BookedListService;


@RestController
//@RequestMapping("/booking")
public class BookingListController {
	
//	Logger logger = LoggerFactory.getLogger(BookingListController.class);
	

	
	@Autowired
	BookedListService bookedListService;
	
//	@PostMapping("/insert")
//	public ResponseEntity<ResponseEntity<BookedList>> addBooking(@RequestBody BookedList bookedList){
//		return ResponseEntity.ok(bookedListService.addOneBooking(bookedList));
//	}
	@PostMapping("/bookMovie")
	public BookedList addBooking(@RequestBody BookedList bookedlist) {
		return bookedListService.addNewBooking(bookedlist);
	}
	
	@DeleteMapping("/cancelTicket/{bookingid}")
	public ResponseEntity<BookedList> userDelete(@PathVariable int bookingid) {
		return bookedListService.deleteById(bookingid);
	}
	
}
