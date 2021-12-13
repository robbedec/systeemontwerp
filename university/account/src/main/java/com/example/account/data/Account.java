package com.example.account.data;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
	@Id
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
	private String accountId;
	private String firstName;
	private String lastName;
	private String username;
	private String email;
	private String password;
	private String address;
	private LocalDate dateOfBirth;
	private AccountType type;
	
	@ElementCollection
	@CollectionTable(name="account_comments", joinColumns = @JoinColumn(name="account_id"))
	private List<String> comments;
}
