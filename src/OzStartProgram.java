/**
 * @author Elizabeth Allen - eallen12
 * CIS175 - Fall 2021
 * Sep 16, 2021
 */

import java.util.List;
import java.util.Scanner;
import controller.OzBookCreation;
import model.OzBook;

public class OzStartProgram {

	static Scanner in = new Scanner(System.in);
	static OzBookCreation obc = new OzBookCreation();
	
			
	private static void addOzBook() {
		// TODO Auto-generated method stub
		System.out.print("Enter book title: ");
		String bookTitle = in.nextLine();
		System.out.print("Enter publication year: ");
		int bookPubYear = in.nextInt();
	
		OzBook toAdd = new OzBook(bookTitle, bookPubYear);
		obc.insertBook(toAdd);
	}

	private static void deleteOzBook() {
		// TODO Auto-generated method stub
		System.out.print("Enter book title to delete: ");
		String bookTitle = in.nextLine();
		System.out.print("Enter publication year to delete: ");
		int bookPubYear = in.nextInt();
		
		OzBook toDelete = new OzBook(bookTitle, bookPubYear);
		obc.deleteBook(toDelete);
	}

	private static void editOzBook() {
		// TODO Auto-generated method stub
		System.out.println("Select search method: ");
		System.out.println("1 : Search by Book Title");
		System.out.println("2 : Search by Publication Year");
		int searchBy = in.nextInt();
		in.nextLine();
		
		List<OzBook> foundBooks;
		if (searchBy == 1) {
			System.out.print("Enter book title: ");
			String bookTitle = in.nextLine();
			foundBooks = obc.searchByBookTitle(bookTitle);
			
		} else {
			System.out.print("Enter publication year: ");
			String bookPubYear = in.nextLine();
			foundBooks = obc.searchByPubYear(bookPubYear);
		}

		if (!foundBooks.isEmpty()) {
			System.out.println("Book Results.");
			for (OzBook o : foundBooks) {
				System.out.println(o.getBookNumber() + " : " + o.toString());
			}
			System.out.print("Enter book number to edit: ");
			int bookNumberToEdit = in.nextInt();

			OzBook toEdit = obc.searchByBookNumber(bookNumberToEdit);
			System.out.println("Retrieved " + " and " + toEdit.getBookPubYear() + " from " + toEdit.getBookTitle());
			System.out.println("1 : Update Book Title");
			System.out.println("2 : Update Publication Year");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Book Title: ");
				String newBookTitle = in.nextLine();
				toEdit.setBookTitle(newBookTitle);
			
			} else if (update == 2) {
				System.out.print("New Publication Year: ");
				int newPubYear = in.nextInt();
				toEdit.setBookPubYear(newPubYear);
			}

			obc.updateBook(toEdit);

		} else {
			System.out.println("---- No results found");
		}
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runLibrary();

	}

	public static void runLibrary() {
		boolean goAgain = true;
		System.out.println("* Follow the Yellow Brick Road... *");
		while (goAgain) {
			System.out.println("*  Select below:");
			System.out.println("*  1 -- Add Oz Book");
			System.out.println("*  2 -- Edit Oz Book");
			System.out.println("*  3 -- Delete Oz Book");
			System.out.println("*  4 -- View the books");
			System.out.println("*  5 -- Exit to Kansas");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addOzBook();
			} else if (selection == 2) {
				editOzBook();
			} else if (selection == 3) {
				deleteOzBook();
			} else if (selection == 4) {
				viewLibrary();
			} else {
				obc.cleanUp();
				System.out.println("   There's no place like home!   ");
				goAgain = false;
			}

		}

	}

	private static void viewLibrary() {
		// TODO Auto-generated method stub
		List<OzBook>allBooks=obc.showAllBooks();
		for(OzBook singleItem : allBooks) {
			System.out.println(singleItem.returnBookDetails());
		}
	}
		
}
