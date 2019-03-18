package rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.Data;
@Data
public class Stock {
	@Id
	@NotNull
	String  sku;
	@NotNull
	Integer store;
	@NotNull
	Float stockAvailable;
	@NotNull
	Float stockOnLine;
	@NotNull
	Float purchaseOrden;
	@NotNull
	Float transferOrden;
	@NotNull
	Float averageCost;
	
}

