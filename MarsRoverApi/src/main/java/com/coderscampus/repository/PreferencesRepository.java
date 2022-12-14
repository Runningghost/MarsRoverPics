package com.coderscampus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.dto.HomeDto;

public interface PreferencesRepository extends JpaRepository<HomeDto, Long> {
	// I'm leaving the below code commented out, it can be used as a reference for how to create 
	//custom queries... but in this case we don't need a custom query because we're using the 
	// built in  "findBy...." naming convention.
	//@Query("select dto from HomeDto dto where userId = :userId")
	//@Query(value="select dto from HomeDto dto where userId = :userId", nativeQuery=true)
	HomeDto findByUserId(Long userID);

}
