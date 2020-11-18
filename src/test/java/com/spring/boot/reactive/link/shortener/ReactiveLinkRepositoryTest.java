package com.spring.boot.reactive.link.shortener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import reactor.test.StepVerifier;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReactiveLinkRepositoryTest 
{
	@Autowired
	private ReactiveLinkRepository reactiveLinkRepository;
	
	@Test
	public void returnsSameLinkAsArgument()
	{
		Link link = new Link("https://spring.io","aaaa22");
		StepVerifier.create(reactiveLinkRepository.save(link))
		.expectNext(link)
		.verifyComplete();
		
	}
	
	@Test
	public void saveInRedis()
	{
		Link link = new Link("https://spring.io","aaaa22");
		StepVerifier.create(reactiveLinkRepository.save(link)
		.flatMap(result -> reactiveLinkRepository.findByKey(result.getKey())))
		.expectNext(link)
		.verifyComplete();
		
	}
	
}
