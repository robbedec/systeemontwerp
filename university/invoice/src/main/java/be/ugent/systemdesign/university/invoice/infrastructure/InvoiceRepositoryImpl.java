package be.ugent.systemdesign.university.invoice.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import be.ugent.systemdesign.university.invoice.domain.Invoice;
import be.ugent.systemdesign.university.invoice.domain.InvoiceRepository;

@Repository
public class InvoiceRepositoryImpl implements InvoiceRepository{
	
	@Autowired
	InvoiceJPARepository repo;

	@Override
	public Invoice findOne(Integer id) {
		Invoice invoice = repo.findById(id).orElseThrow(InvoiceNotFoundException::new);
		return invoice;
	}

	@Override
	public void save(Invoice _i) {
		repo.save(_i);		
	}

	@Override
	public List<Invoice> findByStudentNumber(String studentNumber) {
		List<Invoice> invoices = repo.findByStudentNumber(studentNumber).stream().collect(Collectors.toList());
		return invoices;
	}
	
	
}