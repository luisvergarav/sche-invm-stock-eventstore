package rtl.tot.corp.sche.invm.eventstore.stockeventstore.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonRawValue;

import lombok.Data;

@Data
public class DomainEvents {

    private String boardUuid;

    @JsonRawValue
    private List<String> domainEvents = new ArrayList<String>();

}