package rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

import corp.falabella.arq.event.provider.EventPublisher;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.infraestructure.adapters.db.cosmos.StockRepository;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.service.converters.DomainEventService;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.service.converters.JsonStringToTupleConverter;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.service.converters.TupleToJsonStringConverter;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.infraestructure.adapters.output.asb.EventPublisherService;

@Configuration
public class EventStoreConfig {

    @Bean
    @Primary
    @ConditionalOnMissingBean
    public DomainEventService domainEventService(
            final StockRepository domainEventsRepository, 
            final EventPublisherService publisher,
            final TupleToJsonStringConverter tupleToJsonStringConverter, 
            final JsonStringToTupleConverter jsonStringToTupleConverter
    ) {

        return new DomainEventService( domainEventsRepository, 
        		publisher,
        		tupleToJsonStringConverter, 
        		jsonStringToTupleConverter 
        		);
    }

}
