package com.danielks.crud_project.repositories;

import com.danielks.crud_project.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<ContactEntity, Long> {}
