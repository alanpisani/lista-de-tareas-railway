package com.lista.app.rest.repositories;

import com.lista.app.rest.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TodoRepository extends JpaRepository <Task, Long>{

}
