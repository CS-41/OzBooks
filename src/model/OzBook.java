/**
 * @author Elizabeth Allen - eallen12
 * CIS175 - Fall 2021
 * Sep 16, 2021
 */

package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="books")
public class OzBook {
	@Id 
	@GeneratedValue
	
	private int bookNumber;
	@Column(name="Book Title")
	private String bookTitle;
	@Column(name="Publication Year")
	private int bookPubYear;
	
		
	public OzBook() {
		super();
	}
	
	public OzBook(String bookTitle, int bookPubYear) {
		super();
		this.bookTitle = bookTitle;
		this.bookPubYear = bookPubYear;
	}
	
	
	public int getBookNumber() {
		return bookNumber;
	}
	public void setBookNumber(int bookNumber) {
		this.bookNumber = bookNumber;
	}
	public String getBookTitle() {
		return bookTitle;
	}
	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}
	public int getBookPubYear() {
		return bookPubYear;
	}
	public void setBookPubYear(int bookPubYear) {
		this.bookPubYear = bookPubYear;
	}

	
	public String returnBookDetails() {
		return this.bookPubYear + ": " + this.bookTitle;
	}

	@Override
	public String toString() {
		return "OzBook [bookNumber=" + bookNumber + ", bookTitle=" + bookTitle
				+ ", bookPubYear=" + bookPubYear + "]";
	}
	
}