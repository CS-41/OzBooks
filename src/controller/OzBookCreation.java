/**
 * @author Elizabeth Allen - eallen12
 * CIS175 - Fall 2021
 * Sep 16, 2021
 */

package controller;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import model.OzBook;


public class OzBookCreation {

	static EntityManagerFactory emfactory = 
			Persistence.createEntityManagerFactory("OzBooks");
	
	public void insertBook(OzBook ob) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(ob);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<OzBook> showAllBooks(){
		EntityManager em = emfactory.createEntityManager();
		List<OzBook>allBooks = em.createQuery("SELECT i FROM OzBook i").getResultList();
		return allBooks;
	}
	
	public void deleteBook(OzBook toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<OzBook>typedQuery=em.createQuery("select ob from "
				+ "OzBook ob where ob.bookTitle =:selectedBookTitle and "
				+ "ob.bookPubYear =:selectedBookPubYear", OzBook.class);
		
		typedQuery.setParameter("selectedBookTitle", toDelete.getBookTitle());
		typedQuery.setParameter("selectedBookPubYear", toDelete.getBookPubYear());
		
		typedQuery.setMaxResults(1);
		
		OzBook result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<OzBook> searchByBookTitle(String bookTitle){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<OzBook>typedQuery = em.createQuery("select ob from OzBook ob"
				+ "where ob.bookTitle =:selectedBookTitle", OzBook.class);
		typedQuery.setParameter("selectedBookTitle", bookTitle);
		
		List<OzBook>foundBooks = typedQuery.getResultList();
		em.close();
		return foundBooks;
	}
	

	public List<OzBook> searchByPubYear(String bookPubYear){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<OzBook>typedQuery = em.createQuery("select ob from OzBook ob"
				+ "ob.bookPubYear=:selectedBookPubYear", OzBook.class);
		typedQuery.setParameter("selectedBookPubYear", bookPubYear);
		
		List<OzBook>foundBooks = typedQuery.getResultList();
		em.close();
		return foundBooks;
	}
	
	public OzBook searchByBookNumber(int bookNumberToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		OzBook found = em.find(OzBook.class, bookNumberToEdit);
		em.close();
		return found;
	}
	
	public void updateBook(OzBook toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
	
	public void cleanUp() {
		emfactory.close();
	}
	
	
}
