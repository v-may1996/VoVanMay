package test.restapi.webservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import test.restapi.models.Works;
import test.restapi.repository.PaginationRepository;
import test.restapi.repository.WorkRepository;

@Service
public class WorkWebService {
	
	@Autowired
	private WorkRepository workRepository;
	@Autowired
	private PaginationRepository pageRepository;
	
	/**
	 * add work
	 * @param work
	 */
	public void addWork(Works work) {
		workRepository.save(work);
	}
	
	/**
	 * find work by id
	 * @param id
	 * @return
	 */
	public Works getWorkById(Integer id) {
		return workRepository.findById(id).get();
	}
	
	/**
	 * delete work 
	 * @param id
	 */
	public void deleteWork(Integer id) {
		workRepository.deleteById(id);
	}
	
	/**
	 * pagination and sort
	 * @param sortBy
	 * @param page
	 * @return
	 */
	public Page<Works> listAllPage(@RequestParam Optional<String> sortBy,@RequestParam Optional<Integer> page) {
		return pageRepository.findAll(PageRequest.of(page.orElse(0), 10, Sort.Direction.ASC, sortBy.orElse("workName")));
	}
}
