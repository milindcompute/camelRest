package com.example.JavaFileCopier;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.restlet.data.MediaType;
import org.springframework.stereotype.Component;

@Component
public class MyRouteBuilder extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		 restConfiguration()
	        .component("restlet")
	        .host("localhost").port("8080");
		rest("/rest").
			get("/test").to("direct:gettest").produces(MediaType.TEXT_PLAIN.toString())
			.post("/test").to("direct:posttest").produces(MediaType.TEXT_PLAIN.toString());
		
		from("direct:gettest").process(new Processor() {
			
			public void process(Exchange exchange) throws Exception {
				System.out.println("get test");
				exchange.getIn().setBody("Successful get test hit");
			}
		});
		
		from("direct:posttest").process(new Processor() {
			
			public void process(Exchange exchange) throws Exception {
				System.out.println("post test");
				exchange.getIn().setBody("Successful post test hit");
			}
		});
	}
}
