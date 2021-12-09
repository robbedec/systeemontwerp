package be.ugent.timgeldof.learning_platform;

import org.springframework.boot.CommandLineRunner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import be.ugent.timgeldof.learning_platform.API.messaging.Channels;
import be.ugent.timgeldof.learning_platform.domain.course.Course;
import be.ugent.timgeldof.learning_platform.domain.course.CourseAnnouncement;
import be.ugent.timgeldof.learning_platform.domain.course.CourseMaterial;
import be.ugent.timgeldof.learning_platform.domain.course.CourseRepository;
import be.ugent.timgeldof.learning_platform.domain.course_access.CourseAccess;
import be.ugent.timgeldof.learning_platform.domain.course_access.CourseAccessRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@EnableBinding(Channels.class)
@EnableAsync
@SpringBootApplication
public class LearningPlatformApplication {
	private static final Logger log = LoggerFactory.getLogger(LearningPlatformApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LearningPlatformApplication.class, args);
	}

	
	/*
	@Bean
	public CommandLineRunner testCourseAnnouncements(CourseRepository courseRepo, CourseAccessRepository accessRepo) {
		return (args) -> {
			log.info("start: findall test course announcements.");
			courseRepo.findAll().forEach( c -> 
					c.getCourseAnnouncements().stream().forEach(c_a -> log.info(c_a.getMessage())));
			log.info("end: test course announcements");
			
		};
	}
	
	
	@Bean
	public CommandLineRunner testDomainEventForCourseMaterialVisibility(CourseRepository courseRepo, CourseAccessRepository accessRepo) {
		return (args) -> {
			Course c = courseRepo.findOne(1);
			c.changeCourseMaterialVisibility(true);
			courseRepo.save(c);
		};
	}
	*/
	
}
