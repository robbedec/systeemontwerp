package be.ugent.timgeldof.learning_platform.domain.course;

import java.util.List;

import be.ugent.timgeldof.learning_platform.domain.seedwork.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddedCourseAnnouncementDomainEvent extends DomainEvent{
	public String courseName;
	public String announcementMessage;
}
