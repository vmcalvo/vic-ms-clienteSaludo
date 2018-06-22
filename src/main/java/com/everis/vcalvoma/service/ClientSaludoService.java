package com.everis.vcalvoma.service;

import java.util.concurrent.Future;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.everis.vcalvoma.domain.Saludo;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;


@Service
public class ClientSaludoService {

	public static final String SERVICE_URL = "http://vic-ms-saludo";
	
	@Autowired
	@LoadBalanced
	protected RestTemplate restTemplate;

	protected Saludo saludo;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(ClientSaludoService.class.getName());

	public ClientSaludoService() {
		this.serviceUrl = SERVICE_URL.startsWith("http") ? SERVICE_URL : "http://" + SERVICE_URL;
	}

	@HystrixCommand(fallbackMethod = "saludoDefault")
	public Saludo saludo(String name) {
		return restTemplate.getForObject(serviceUrl + "/saludo/{name}", Saludo.class, name);
	}

	@HystrixCommand(fallbackMethod = "saludoFutureDefault")
	public Future<Saludo> saludoFuture(final String name) {
		return new AsyncResult<Saludo>() {
			public Saludo invoke() {
				return restTemplate.getForObject(serviceUrl + "/saludo/{name}", Saludo.class, name);
			}
		};
	}
	
	public Saludo saludoDefault(String name) {
		return new Saludo("Hello World thanks to Circuit Breaker (Hystrix) - SYNCHRONOUS");
	}
	
	public Saludo saludoFutureDefault(String name) {
		return new Saludo("Hello World thanks to Circuit Breaker (Hystrix) - FUTURE (ASYNCHRONOUS");
	}

}
