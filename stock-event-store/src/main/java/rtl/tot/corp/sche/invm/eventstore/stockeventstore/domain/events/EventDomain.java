package rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.events;

public interface EventDomain {

    String getEntityId();
    String getMetadata();
    String getEntityType();

}
