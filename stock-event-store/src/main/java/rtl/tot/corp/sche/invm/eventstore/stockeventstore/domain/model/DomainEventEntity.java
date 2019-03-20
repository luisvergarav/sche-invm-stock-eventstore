package rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.model;

import static java.util.stream.Collectors.toList;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( exclude = { "id", "occurredOn" })
@JsonIgnoreProperties( ignoreUnknown = true )

public class DomainEventEntity {

    @Id
    private String id;

    private String sku;
    private LocalDateTime occurredOn;


    private String data;

    private String boardUuid;
    
	
	
	
	
	@JsonIgnore
	public String getMetadata() {
		String jsonValue;
		ObjectMapper mapper = new ObjectMapper();
        try {
            jsonValue = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            jsonValue = super.toString();
        }
        return jsonValue;
	}
    

}