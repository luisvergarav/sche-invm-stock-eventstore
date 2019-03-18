package rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.model;

import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.stream.Collectors.toList;
import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( exclude = { "domainEvents" })
public class DomainEventsEntity {

    @Id
    private String boardUuid;

    private Set<DomainEventEntity> domainEvents;

    public DomainEventsEntity() {

        this.domainEvents = new LinkedHashSet<>();

    }

    public DomainEventsEntity( final String boardUuid ) {
        this();

        this.boardUuid = boardUuid;

    }

    public DomainEvents toModel() {

        DomainEvents model = new DomainEvents();
        model.setBoardUuid( boardUuid );
        model.setDomainEvents( domainEvents.stream()
                .map( DomainEventEntity::getData )
                .collect( toList() ) );

        return model;
    }

}
