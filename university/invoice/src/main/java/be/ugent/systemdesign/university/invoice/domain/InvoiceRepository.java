package be.ugent.systemdesign.university.invoice.domain;

import java.util.List;

public interface InvoiceRepository {
	public Invoice findOne(String id);
	public List<Invoice> findByStudentNumber(String studentNumber);
	public void save(Invoice _i);	
}
