package com.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.entity.Notes;

/**
 * https://www.callicoder.com/spring-boot-rest-api-tutorial-with-mysql-jpa-hibernate/
 * 
 * You don’t need to implement JpaRepository's methods. They are already implemented by
 * Spring Data JPA’s SimpleJpaRepository. This implementation is plugged in by
 * Spring automatically at runtime.
 * 
 * @author SabatiniJa
 *
 */
@Repository
public interface NotesRepository extends JpaRepository<Notes, Long> {
}
