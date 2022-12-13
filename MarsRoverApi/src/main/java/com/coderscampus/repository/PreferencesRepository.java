package com.coderscampus.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.Dto.HomeDto;

public interface PreferencesRepository extends JpaRepository<HomeDto, Long> {

}
