package rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.infraestructure.adapters.db.cosmos;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.microsoft.azure.spring.data.cosmosdb.repository.DocumentDbRepository;

import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.model.DomainEventEntity;
import rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.model.DomainEventsEntity;


@Repository
public interface StockRepository extends DocumentDbRepository<DomainEventEntity, String> {
	 List<DomainEventEntity> findBySku(String sku);
}
