package rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.service.converters;

import lombok.extern.slf4j.Slf4j;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.infraestructure.adapters.db.cosmos.StockRepository;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.model.DomainEventEntity;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.model.DomainEvents;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.model.Stock;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.infraestructure.adapters.output.asb.EventPublisherService;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.tuple.Tuple;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Transactional(readOnly = true)
public class DomainEventService {

	private final StockRepository domainEventsRepository;
	private final EventPublisherService publisher;
	private final TupleToJsonStringConverter toJsonStringConverter;
	private final JsonStringToTupleConverter toTupleConverter;

	public DomainEventService(final StockRepository domainEventsRepository, final EventPublisherService publisher,
			final TupleToJsonStringConverter toJsonStringConverter, final JsonStringToTupleConverter toTupleConverter) {

		this.domainEventsRepository = domainEventsRepository;
		this.publisher = publisher;
		this.toJsonStringConverter = toJsonStringConverter;
		this.toTupleConverter = toTupleConverter;

	}


	public boolean check(final String sku) {
		log.info("Getting DomainEvent : sku=" + sku);
		DomainEvents de = new DomainEvents();
		for (DomainEventEntity domainEventEntity : domainEventsRepository.findBySku(sku)) {
			de.getDomainEvents().add(domainEventEntity.getMetadata());

		}
		
		return !de.getDomainEvents().isEmpty();

	}
	
	public DomainEvents getDomainEvents(final String sku) {
		log.info("Getting DomainEvent : sku=" + sku);
		DomainEvents de = new DomainEvents();
		for (DomainEventEntity domainEventEntity : domainEventsRepository.findBySku(sku)) {
			de.getDomainEvents().add(domainEventEntity.getMetadata());

		}
		return de;

	}

	
	public void processDomainEvent(final Tuple event) {
		log.info("ProcessStockEvent sku=", event.toString());

		String sku = event.getString("sku");

		String eventId = event.getString("eventId");
		
		//this.domainEventsRepository.findById( sku )
		// .ifPresent( found -> {

		DomainEventEntity domainEventEntity = new DomainEventEntity();
		domainEventEntity.setId(eventId);
		domainEventEntity.setSku(sku);
		
		Instant occurredOn = Instant.parse( event.getString( "occurredOn" ) );
		domainEventEntity.setOccurredOn(LocalDateTime.ofInstant(occurredOn, ZoneOffset.UTC));

		domainEventEntity.setData(toJsonStringConverter.convert( event ));

		// found.getDomainEvents().add( domainEventEntity );
		this.domainEventsRepository.save(domainEventEntity);
		// });
	}

	

}
