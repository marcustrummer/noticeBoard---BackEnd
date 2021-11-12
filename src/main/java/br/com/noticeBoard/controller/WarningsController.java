package br.com.noticeBoard.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import br.com.noticeBoard.model.Warning;
import br.com.noticeBoard.repository.WarningsRepository;

@RestController
@RequestMapping("/warnings")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class WarningsController {

	@Autowired
	private WarningsRepository warningRepository;

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
	public ResponseEntity<Warning> postWarning(@RequestBody Warning warning) {
		return ResponseEntity.status(HttpStatus.CREATED).body(warningRepository.save(warning));
	}

	@PutMapping
	public ResponseEntity<Warning> putWarning(@RequestBody Warning warning) {
		return ResponseEntity.status(HttpStatus.OK).body(warningRepository.save(warning));
	}
	
	@DeleteMapping("/{id}")
	public void deleteWarning(@PathVariable long id) {
		warningRepository.deleteById(id);
	}

}
