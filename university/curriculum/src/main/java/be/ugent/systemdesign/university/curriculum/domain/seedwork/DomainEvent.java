package be.ugent.systemdesign.university.curriculum.domain.seedwork;

import java.time.LocalDateTime;

import lombok.Getter;

public abstract class DomainEvent {
	
	@Getter
    private final LocalDateTime createdTime;
    
    public DomainEvent() {
    	this.createdTime = LocalDateTime.now();
    }
}
