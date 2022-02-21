package test.restapi.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;

import test.restapi.models.Works;
import test.restapi.repository.PaginationRepository;
import test.restapi.repository.WorkRepository;
import test.restapi.webservice.WorkWebService;

@RestController
@RequestMapping("/api/")
public class WorkController {

	@Autowired
	private WorkWebService service;
	private WorkRepository workRepository;

	@Autowired
	public void setWork(WorkRepository workRepository) {
		this.workRepository = workRepository;
	}
	
	@GetMapping("/works")
	public List<Works> getAllWorks() {
		return workRepository.findAll();
	}

	/**
	 * getWorkById
	 * @param id
	 * @return
	 */
	@GetMapping("/works/{id}")
	public ResponseEntity<Works> getWorkById(@PathVariable Integer id) {
		try {
			Works work = service.getWorkById(id);
			return new ResponseEntity<Works>(work, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<Works>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * add work
	 */
	@PostMapping("/works")
	public ResponseEntity<Works> addWork(@RequestBody Works work) {
		service.addWork(work);
		return new ResponseEntity<Works>(work, HttpStatus.OK);
	}

	/**
	 * update, edit
	 */
	@PutMapping("/works/{id}")
	public ResponseEntity<Works> updateWork(@RequestBody Works workDetails, @PathVariable Integer id) {
		try {
			Works work = service.getWorkById(id);
			work.setWorkName(workDetails.getWorkName());
			work.setStartDate(workDetails.getStartDate());
			work.setEndDate(workDetails.getEndDate());
			work.setStatus(workDetails.getStatus());
			service.addWork(work);
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * delete work
	 */
	@DeleteMapping("/works/{id}")
	public ResponseEntity<Works> deleteWork(@PathVariable Integer id) {
		try {
			Works workDel = service.getWorkById(id);
			service.deleteWork(workDel.getId());
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	/**
	 * paging
	 */
	@GetMapping("/page")
	public Page<Works> listAllPage(Optional<String> sortBy, Optional<Integer> pageNum) {
		return service.listAllPage(sortBy, pageNum);
	}
	

}
