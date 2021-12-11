package be.ugent.timgeldof.learning_platform.domain.course;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import be.ugent.timgeldof.learning_platform.domain.seedwork.DomainEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PublishedCourseMaterialVisibleDomainEvent extends DomainEvent{
	public String courseName;
	public String fileName;
}
