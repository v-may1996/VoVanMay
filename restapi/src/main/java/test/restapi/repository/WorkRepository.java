package test.restapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import test.restapi.models.Works;

public interface WorkRepository extends JpaRepository<Works, Integer>{

}
