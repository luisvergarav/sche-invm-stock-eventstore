package rtl.tot.corp.sche.invm.eventstore.stockeventstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.tuple.Tuple;
import org.springframework.tuple.TupleBuilder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.events.EventType;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.events.StockCreatedIntegrationEvent;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.service.converters.DomainEventService;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.infraestructure.adapters.output.asb.EventPublisherService;

import java.time.Instant;
import java.util.UUID;

@RestController
@Slf4j
public class EventStoreController {

	private final DomainEventService service;
	@Autowired
	EventPublisherService publisher;
	
	public EventStoreController(final DomainEventService service) {

		this.service = service;

	}

	@PostMapping("/")
	public ResponseEntity saveEvent(@RequestBody String json) {

		log.info("Processing event " + json );
		
		Tuple event = TupleBuilder.fromString(json);

		Assert.isTrue(event.hasFieldName("eventType"), "eventType is required");
		Assert.isTrue(event.hasFieldName("eventId"), "boardUuid is required");
		Assert.isTrue(event.hasFieldName("occurredOn"), "occurredOn is required");

		this.service.processDomainEvent(event);

		if (event.getString("eventType").equals(EventType.STOCK_CREATED.toString())) {

			StockCreatedIntegrationEvent integrationEvent = new StockCreatedIntegrationEvent();

			
			publisher.publish(EventType.STOCK_CREATED, integrationEvent);
			log.info("Published Stock Created" );

		}

		return ResponseEntity.accepted().build();
	}

	@GetMapping("/{sku}")
	public ResponseEntity domainEvents(@PathVariable("sku") String sku) {

		return ResponseEntity.ok(this.service.getDomainEvents(sku.toString()));
	}
	
	
	@GetMapping("/check/{sku}")
	public ResponseEntity check(@PathVariable("sku") String sku) {

		
		 if (this.service.check(sku.toString())) 
			 return ResponseEntity.accepted().build();
		 else
			 return ResponseEntity.notFound().build();
		 
	}
}
