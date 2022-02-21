package test.restapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import test.restapi.models.Works;

public interface PaginationRepository extends PagingAndSortingRepository<Works, Integer>{

}
