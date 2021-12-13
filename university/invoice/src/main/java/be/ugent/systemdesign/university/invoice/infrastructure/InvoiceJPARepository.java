package be.ugent.systemdesign.university.invoice.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import be.ugent.systemdesign.university.invoice.domain.Invoice;

public interface InvoiceJPARepository extends JpaRepository<Invoice, Integer>{
	
	List<Invoice> findAll();
	List<Invoice> findByStudentNumber(String studentNumber);
	Optional<Invoice> findByInvoiceId(String invoiceId);

}