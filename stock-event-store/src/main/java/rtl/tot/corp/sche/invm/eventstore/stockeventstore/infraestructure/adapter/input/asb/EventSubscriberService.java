package rtl.tot.corp.sche.invm.eventstore.stockeventstore.infraestructure.adapter.input.asb;

import org.springframework.stereotype.Service;

import corp.falabella.arq.event.provider.EventSubscriber;



@Service
public class EventSubscriberService {

    public EventSubscriberService(final EventSubscriber eventSubscriber,
                                  final ASBToCosmosDBEventHandler asbToCosmosDBEventHandler) {
        eventSubscriber.registerEventHandler(asbToCosmosDBEventHandler);
    }

}
