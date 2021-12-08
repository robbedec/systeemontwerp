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
	public List<RegistrationReadModel> giveRegistrations(Boolean isAccepted) {
		// TODO Auto-generated method stub
		return repo.findAll().stream().map(el -> mapToRegistrationReadModel(el)).collect(Collectors.toList());
	}
	
	private RegistrationReadModel mapToRegistrationReadModel(RegistrationDataModel _r) {
		RegistrationReadModel r = new RegistrationReadModel(
				_r.getEmail(),
				_r.getName(),
				_r.getFirstName(),
				_r.getDateOfBirth(),
				_r.getCourse(),
				_r.isAccepted()
		);
		return r;
	}

}
