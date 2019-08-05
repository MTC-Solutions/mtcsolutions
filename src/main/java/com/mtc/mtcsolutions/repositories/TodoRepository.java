package com.mtc.mtcsolutions.repositories;

import com.mtc.mtcsolutions.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
