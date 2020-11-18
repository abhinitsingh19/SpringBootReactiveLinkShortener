package com.spring.boot.reactive.link.shortener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
public class LinkController 
{

	@Autowired
	private  LinkService linkService;
	@PostMapping("/link")
	Mono<CreateLinkResponse> create(@RequestBody createLinkRequest request)
	{
		return linkService.shortenLink(request.getLink())
		.map(CreateLinkResponse::new);
	}
	
	@GetMapping("/{key}")
	Mono<ResponseEntity<Object>> getLink(@PathVariable String key)
	{
		return linkService.getOriginalLink(key)
				.map(result ->  ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
						.header("location",result.getOiginalLink())
						.build())
						.defaultIfEmpty(ResponseEntity.notFound().build());
		
	}
	
}
