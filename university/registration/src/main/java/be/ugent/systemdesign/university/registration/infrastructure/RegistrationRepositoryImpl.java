package be.ugent.systemdesign.university.registration.infrastructure;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import be.ugent.systemdesign.university.registration.domain.Registration;
import be.ugent.systemdesign.university.registration.domain.RegistrationRepository;
import be.ugent.systemdesign.university.registration.domain.Status;

@Repository
public class RegistrationRepositoryImpl implements RegistrationRepository {

	@Autowired
	RegistrationDataModelRepository registrationRepo;
	
	@Autowired
	ApplicationEventPublisher eventPublisher;
	
	@Override
	public Registration findOne(Integer id) {		
		RegistrationDataModel dataModel = registrationRepo.findById(id).orElseThrow(RegistrationNotFoundException::new);
		return mapToRegistration(dataModel);
	}
	
	@Override
	public Registration getActiveRegistration(String studentId) {
		RegistrationDataModel rdm = registrationRepo.findByAccountIdAndIsActive(studentId, true).get(0);	//only 1 active registration / accountId
		return mapToRegistration(rdm);
	}

	@Override
	public int save(Registration _r) {
		RegistrationDataModel dataModel = mapToRegistrationDataModel(_r);		
		RegistrationDataModel dm = registrationRepo.save(dataModel);
		
		_r.getDomainEvents().forEach(domainEvent -> eventPublisher.publishEvent(domainEvent));
		_r.clearDomainEvents();
		return dm.getRegistrationId();
	}
	
	public void removeRegistration(int registrationId) {
		registrationRepo.deleteById(registrationId);
	}
	
	private RegistrationDataModel mapToRegistrationDataModel(Registration _r) {
		return new RegistrationDataModel(_r.getRegistrationId(), _r.getAccountId(), _r.getRegistrationDate(), 
				_r.getEmail(), _r.getName(), _r.getFirstName(), _r.getDateOfBirth(), _r.getSocialSecurityNumber(), _r.getFaculty(), _r.getDegree(), _r.getStatus(), _r.getIsActive());
	}
	
	private Registration mapToRegistration(RegistrationDataModel _rdm) {
		Registration r = Registration.builder()
							.registrationId(_rdm.getRegistrationId())
							.accountId(_rdm.getAccountId())
							.registrationDate(_rdm.getRegistrationDate())
							.email(_rdm.getEmail())
							.name(_rdm.getName())
							.firstName(_rdm.getFirstName())							
							.dateOfBirth(_rdm.getDateOfBirth())
							.socialSecurityNumber(_rdm.getSocialSecurityNumber())
							.faculty(_rdm.getFaculty())
							.degree(_rdm.getDegree())
							.status(Status.valueOf(_rdm.getStatus()))	
							.isActive(_rdm.getIsActive())
							.build();
		return r;
	}		
}
