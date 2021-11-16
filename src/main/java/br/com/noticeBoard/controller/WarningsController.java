package br.com.noticeBoard.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import br.com.noticeBoard.model.Warning;
import br.com.noticeBoard.repository.WarningsRepository;

@RestController
@RequestMapping("/warnings")
public class WarningsController {

	@Autowired
	private WarningsRepository warningRepository;

	
	
	  @GetMapping("/warnings")
	  public ResponseEntity<Map<String, Object>> getAllTutorials(
	        @RequestParam(required = false) String title,
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size
	      ) {

	    try {
	      List<Warning> warnings = new ArrayList<Warning>();
	      Pageable paging = PageRequest.of(page, size);
	      
	      Page<Warning> pageTuts;
	      if (title == null)
	        pageTuts = warningRepository.findAll(paging);
	      else
	        pageTuts = warningRepository.findByTitleContaining(title, paging);

	      warnings = pageTuts.getContent();

	      Map<String, Object> response = new HashMap<>();
	      response.put("warnings", warnings);
	      response.put("currentPage", pageTuts.getNumber());
	      response.put("totalItems", pageTuts.getTotalElements());
	      response.put("totalPages", pageTuts.getTotalPages());

	      return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  
	  
	  
	  @GetMapping("/warnings/published")
	  public ResponseEntity<Map<String, Object>> findByPublished(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "3") int size
	      ) {
	    try {      
	      List<Warning> warnings = new ArrayList<Warning>();
	      Pageable paging = PageRequest.of(page, size);
	      
	      Page<Warning> pageTuts = warningRepository.findByPublished(true, paging);
	      warnings = pageTuts.getContent();
	            
	      Map<String, Object> response = new HashMap<>();
	      response.put("warnings", warnings);
	      response.put("currentPage", pageTuts.getNumber());
	      response.put("totalItems", pageTuts.getTotalElements());
	      response.put("totalPages", pageTuts.getTotalPages());
	      
	      return new ResponseEntity<>(response, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	
	
	@GetMapping("/all")
	public ResponseEntity<List<Warning>> getAll() {
		return ResponseEntity.ok(warningRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Warning> getById(@PathVariable long id) {
		return warningRepository.findById(id).map(Resp -> ResponseEntity.ok(Resp))
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/title/{title}")
	public ResponseEntity<List<Warning>> getByTitle(@PathVariable String title) {
		return ResponseEntity.ok(warningRepository.findByTitleContainingIgnoreCase(title));
	}

	 @PostMapping
	  public ResponseEntity<Warning> createTutorial(@RequestBody Warning warning) {
	    try {
	      Warning _warning = warningRepository.save(new Warning(warning.getTitle(), warning.getDescription(), false, warning.getPostDate(), warning.getViewedDate()));
	      return new ResponseEntity<>(_warning, HttpStatus.CREATED);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }


	@PutMapping("/{id}")
	public ResponseEntity<Warning> putWarning(@RequestBody Warning warning) {
		return ResponseEntity.status(HttpStatus.OK).body(warningRepository.save(warning));
	}
	
	@DeleteMapping("/{id}")
	public void deleteWarning(@PathVariable long id) {
		warningRepository.deleteById(id);
	}

}
