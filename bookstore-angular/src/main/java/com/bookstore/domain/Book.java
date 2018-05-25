package com.bookstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Book {

	private static final long serialVersionUID = 123123L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)	//For Auto Incrementing the id.
	@Column(name="Id", nullable=false, updatable=false)
	private Long id;

	private String title;
	private String author;
	private String publisher;
	private String publicationDate;
	private String language;
	private String category;
	private String numberOfPages;
	private String format;
	private String isbn;
	
	private double shippingWeight;
	private double listPrice;
	private boolean active = true;
	
	@Column(columnDefinition="text")
	private String description;
	private int inStockNumber;
	
	@Transient
	private MultipartFile file;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(String numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public double getShippingWeight() {
		return shippingWeight;
	}

	public void setShippingWeight(double shippingWeight) {
		this.shippingWeight = shippingWeight;
	}

	public double getListPrice() {
		return listPrice;
	}

	public void setListPrice(double listPrice) {
		this.listPrice = listPrice;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getInStockNumber() {
		return inStockNumber;
	}

	public void setInStockNumber(int inStockNumber) {
		this.inStockNumber = inStockNumber;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	
}
