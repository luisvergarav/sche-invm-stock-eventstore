package rtl.tot.corp.sche.invm.eventstore.stockeventstore.infraestructure.adapter.input.asb;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import corp.falabella.arq.event.Event;
import corp.falabella.arq.event.EventHandler;
import lombok.extern.slf4j.Slf4j;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.events.EventType;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.events.StockCreatedIntegrationEvent;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.events.StockCreatedNotifiedEvent;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.model.Stock;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.service.converters.DomainEventService;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.infraestructure.adapters.output.asb.EventPublisherService;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.tuple.Tuple;
import org.springframework.tuple.TupleBuilder;
import org.springframework.util.Assert;


@Slf4j
@Component
public class ASBToCosmosDBEventHandler implements EventHandler {

	@Autowired
	EventPublisherService publisher;
	private final DomainEventService service;
	//private final RestTemplate restTemplate;
	//private final String productsApiUrl;

	public ASBToCosmosDBEventHandler(//final RestTemplate restTemplate, 

			final DomainEventService service
			//,@Value("${api.products.endpoint}") final String productsApiUrl
			) {
		//this.productsApiUrl = productsApiUrl;
		this.service = service;
		//this.restTemplate = restTemplate;
	}

	@Override
	public void processEvent(Event event) {

		try {
			
					
					
				
		    if (event.getEventType().equals(EventType.STOCK_CREATED.toString())) {
		    	
		    log.info("Persisting Stock " );
		    
		    //Tuple tuple = TupleBuilder.fromString( event.toString() );

	        //Assert.isTrue( tuple.hasFieldName( "eventType" ), "eventType is required" );

	        //this.service.processDomainEvent( event.asMap() );

	        
				
			StockCreatedIntegrationEvent integrationEvent = new StockCreatedIntegrationEvent();
		        
		    integrationEvent.setSku(event.getEntityId());
		    
		    
		    	//publisher.publish(EventType.STOCK_CREATED, integrationEvent);
				log.info("Published Stock Created" + event.getMetadata());
				
			}
			if (event.getEventType().equals(EventType.STOCK_UPDATED.toString())) {
				
				log.info("Updating Stock " );
				
//				Optional<Product> productObject = productRepository.findById(Long.valueOf(product.getSku()));
//				
//				
//				
//				if (productObject.isPresent()) {
//					Product productFromDB = productObject.get();
//			
//					productFromDB.setBrand(product.getBrand());
//					productFromDB.setCodeSUNAT(product.getCodeSUNAT());
//					productFromDB.setCodeSupplier(product.getCodeSupplier());
//					productFromDB.setDescription(product.getDescription());
//					productFromDB.setEan(product.getEan());
//					productFromDB.setFlejeDescription(product.getFlejeDescription());
//					productFromDB.setLevelId(product.getLevelId());
//					productFromDB.setModel(product.getModel());
//					productFromDB.setNameCasePack(product.getNameCasePack());
//					productFromDB.setNameSupplier(product.getNameSupplier());
//					productFromDB.setPosDescription(product.getPosDescription());
//					productFromDB.setProductType(product.getProductType());
//					productFromDB.setQtyCasePack(product.getQtyCasePack());
//					productFromDB.setSaleUnit(product.getSaleUnit());
//					productFromDB.setSku(product.getSku());
//					productFromDB.setStatus(product.getStatus());
//					productFromDB.setUnitMeasure(product.getUnitMeasure());
//					
//		    		
//					productRepository.save(productFromDB);
//				
//					ProductUpdatedNotifiedEvent integrationEvent = new ProductUpdatedNotifiedEvent();
//			        
//					  integrationEvent.setSku(event.getEntityId());
//					    integrationEvent.setBrand(product.getBrand());
//					    integrationEvent.setCodeSupplier(product.getCodeSupplier());
//					    integrationEvent.setDescription(product.getDescription());
//					    integrationEvent.setEan(product.getEan());
//					    integrationEvent.setFlejeDescription(product.getFlejeDescription());
//					    integrationEvent.setLevelId(product.getLevelId());
//					    integrationEvent.setModel(product.getModel());
//					    integrationEvent.setNameSupplier(product.getNameSupplier());
//					    integrationEvent.setPosDescription(product.getPosDescription());
//					    integrationEvent.setProductType(product.getProductType());
//					    integrationEvent.setSaleUnit(product.getSaleUnit());
//					    integrationEvent.setStatus(product.getStatus());
//					 				
					      
				    
					//publisher.publish(EventType.STOCK_UPDATED, integrationEvent);
					log.info("Published Stock updated" + event.getMetadata());
			
				
				}
				else
						log.info("Stock not found to update" + event.getEntityId());
			
				
						
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		// Always evict cache first
//		try {
//			// This call needs to be jailed because the CosmoDB throws an exception
//			// when the target resource doesn't exist.
//			// productsRepository.deleteById(event.getEntityId());
//		} catch (final Exception ex) {
//			log.warn("Resource to be deleted does not exist: " + ex.getMessage());
//		}
//
//		if (!event.getEventType().equals(EventType.PRODUCT_DELETED.toString())) {
//			final String url = productsApiUrl + "/" + event.getEntityId();
//
//			// When an event arrives, ask the Core API for full domain information
//			// And for demonstration purposes, lets assume that the event payload
//			// doesn't have all the information we actually need.
//			// final Product product = restTemplate.getForObject(url, Product.class);
//			// if (product != null ) {
//
//			ObjectMapper mapper = new ObjectMapper();
//
//			Product product;
//			try {
//				product = mapper.readValue(event.getMetadata(), Product.class);
//				log.info("Persisting product " + product);
//				productRepository.save(product);
//
//			} catch (JsonParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (JsonMappingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
	}

}
