package com.spring.boot.reactive.link.shortener;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class LinkServiceTest 
{
	
	private LinkRepository LinkRepository = mock(LinkRepository.class);
	private LinkService linkService = new LinkService("https://some-domain.com", LinkRepository);
	
	
	@Before
	public void setUp()
	{
		when(LinkRepository.save(any())).then(new Answer<Mono<Link>>() {

			@Override
			public Mono<Link> answer(InvocationOnMock invocation) throws Throwable 
			{
				return Mono.just((Link) invocation.getArguments()[0]);
			}
		});
	}
	@Test
	public void shortenLink()
	{
		StepVerifier.create(linkService.shortenLink("https://spring.io"))
		.expectNextMatches(result -> result != null && !result.isEmpty()
		&& result.startsWith("https://some-domain.com"))
		.verifyComplete();
	}

}
