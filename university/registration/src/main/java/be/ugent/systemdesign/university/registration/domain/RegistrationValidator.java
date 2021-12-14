package be.ugent.systemdesign.university.registration.domain;

public class RegistrationValidator {

	public static boolean isValid(Registration oldReg, Registration newReg) {
		return oldReg.isPaid() && !oldReg.getIsActive() && !oldReg.hasOpenViolations();		
	}
}
