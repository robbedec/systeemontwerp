package be.ugent.systemdesign.university.registration.application.query;

import java.util.List;

public interface RegistrationQuery {

	public List<RegistrationReadModel> giveRegistrations(String accountId);
	public RegistrationReadModel getRegistration(String registrationId);
}
