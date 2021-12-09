package be.ugent.systemdesign.university.registration.infrastructure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Repository;

import be.ugent.systemdesign.university.registration.domain.PayementStatus;
import be.ugent.systemdesign.university.registration.domain.Registration;
import be.ugent.systemdesign.university.registration.domain.RegistrationRepository;

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
	public void save(Registration _r) {
		RegistrationDataModel dataModel = mapToRegistrationDataModel(_r);
		registrationRepo.save(dataModel);
		
		_r.getDomainEvents().forEach(domainEvent -> eventPublisher.publishEvent(domainEvent));
		_r.clearDomainEvents();		
	}
	
	public void removeRegistration(int registrationId) {
		registrationRepo.deleteById(registrationId);
	}
	
	private RegistrationDataModel mapToRegistrationDataModel(Registration _r) {
		return new RegistrationDataModel(_r.getRegistrationId(), _r.getRegistrationDate(), _r.getEmail(), _r.getName(), _r.getFirstName(), _r.getDateOfBirth(), _r.getCourse(), _r.isAccepted(), _r.getPayementStatus());
	}
	
	private Registration mapToRegistration(RegistrationDataModel _rdm) {
		Registration r = Registration.builder()
							.registrationId(_rdm.getRegistrationId())
							.registrationDate(_rdm.getRegistrationDate())
							.email(_rdm.getEmail())
							.name(_rdm.getName())
							.firstName(_rdm.getFirstName())
							.dateOfBirth(_rdm.getDateOfBirth())
							.course(_rdm.getCourse())
							.isAccepted(_rdm.isAccepted())
							.payementStatus(PayementStatus.valueOf(_rdm.getPayementStatus()))
							.build();
		return r;
	}
	
	
	
	
	
}
