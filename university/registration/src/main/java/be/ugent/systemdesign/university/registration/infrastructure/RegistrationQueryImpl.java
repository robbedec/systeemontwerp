package be.ugent.systemdesign.university.registration.infrastructure;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import be.ugent.systemdesign.university.registration.application.query.RegistrationQuery;
import be.ugent.systemdesign.university.registration.application.query.RegistrationReadModel;

@Component
public class RegistrationQueryImpl implements RegistrationQuery {

	@Autowired
	RegistrationDataModelRepository repo;
	
	@Override
	public List<RegistrationReadModel> giveRegistrations(String status) {		// 
		return repo.findByStatus(status).stream().map(el -> mapToRegistrationReadModel(el)).collect(Collectors.toList());		
	}	

	@Override
	public RegistrationReadModel getRegistration(String registrationId) {
		return mapToRegistrationReadModel(repo.getById(Integer.parseInt(registrationId)));
	}

	private RegistrationReadModel mapToRegistrationReadModel(RegistrationDataModel _r) {
		RegistrationReadModel r = new RegistrationReadModel(
				_r.getEmail(),
				_r.getName(),
				_r.getFirstName(),
				_r.getDateOfBirth(),
				_r.getFaculty(),
				_r.getDegree(),
				_r.getStatus()
		);
		return r;
	}
}
