package com.bookstore.resource;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bookstore.domain.Book;
import com.bookstore.service.BookService;
import com.bookstore.service.UserService;


@RestController
@RequestMapping("/book")
public class BookResource {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public Book addBook(@RequestBody Book book){
		
		System.out.println("------------------------------------------------------------");
		System.out.println("                ADD BOOK API HAS BEEN HIT");
		System.out.println("------------------------------------------------------------");
		return bookService.save(book);
	}
	
	@RequestMapping(value="/add/image", method=RequestMethod.POST)
	public ResponseEntity addImage(@RequestParam("id") Long id, HttpServletRequest request, HttpServletResponse response){
		
		System.out.println("------------------------------------------------------------");
		System.out.println("                ADD IMAGE API HAS BEEN HIT");
		System.out.println("------------------------------------------------------------");
		try {
			Book book = bookService.findOne(id);
			MultipartHttpServletRequest mpsr = (MultipartHttpServletRequest) request;
			Iterator<String> it = mpsr.getFileNames();
			if(it.hasNext()){
				MultipartFile multiPartFile = mpsr.getFile(it.next());
				String fileName = id+".png";
				byte [] bytes = multiPartFile.getBytes();
				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("src/main/resources/static/image/book/" + fileName)));
				stream.write(bytes);
				stream.close();
				return new ResponseEntity("Upload Successfull!", HttpStatus.OK);
			}
			else{
				return new ResponseEntity("No Filed Provided!", HttpStatus.BAD_REQUEST);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity("Upload Failed!", HttpStatus.BAD_REQUEST);
		}
	
	}
	
	
	@RequestMapping(value="/bookList", method=RequestMethod.GET)
	public List<Book> getBookList(){
		return bookService.findAll();
	}	
	
	
	@RequestMapping("/{id}")
	public Book getBook(@PathVariable("id") Long id){
		Book book = bookService.findOne(id);
		return book;
	}	
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public Book updateBook(@RequestBody Book book){
			return bookService.save(book);
	}	
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public ResponseEntity removeBook(@RequestBody Long id){
			bookService.removeOne(id);
			String fileName = id + ".png";
			try {
				Files.deleteIfExists(Paths.get("src/main/resources/static/image/book/"+fileName));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return  new ResponseEntity("Removed Successfully!", HttpStatus.OK);
	}		
	
	
}





