package be.ugent.timgeldof.notification.infrastructure;

public interface CommunicationProvider {
	void send(String target, String content);
}
