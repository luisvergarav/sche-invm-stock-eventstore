package rtl.tot.corp.sche.invm.eventstore.stockeventstore.infraestructure.adapters.output.asb;

import corp.falabella.arq.event.Event;
import corp.falabella.arq.event.EventBuilder;
import corp.falabella.arq.event.provider.EventPublisher;
import corp.falabella.arq.infra.exception.InvalidParameterException;
import lombok.extern.slf4j.Slf4j;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.events.EventDomain;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.events.EventProperties;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.events.EventType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class EventPublisherService {

    private final EventPublisher eventPublisher;
    private final EventProperties eventProperties;

    @Autowired
    public EventPublisherService(final EventPublisher eventPublisher,
                                 final EventProperties eventProperties) {
        this.eventPublisher = eventPublisher;
        this.eventProperties = eventProperties;
    }

    public boolean publish(final EventType eventType, final EventDomain eventDomain) {
        boolean eventSent = false;
        try {
            final Event event = EventBuilder.newBuilder()
                    .generateEventId()
                    .eventType(eventType.toString())
                    .entityId(eventDomain.getEntityId())
                    .entityType(eventDomain.getEntityType())
                    .dateTime(LocalDateTime.now())
                    .version(eventProperties.getVersion())
                    .country(eventProperties.getCountry())
                    .commerce(eventProperties.getCommerce())
                    .channel(eventProperties.getChannel())
                    .mimeType(eventProperties.getMimeType())
                    .metadata(eventDomain.getMetadata())
                    .build();

            eventSent = eventPublisher.publish(event);
            log.info("Event published: " + event.getMetadata());
        } catch (InvalidParameterException e) {
            log.error(eventType.toString() + " event could not be send. Cause: " + e.getMessage());
        }
        return eventSent;
    }

}
