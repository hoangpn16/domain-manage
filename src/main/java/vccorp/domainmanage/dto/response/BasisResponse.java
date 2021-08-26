package vccorp.domainmanage.dto.response;

import lombok.Getter;
import lombok.Setter;
import vccorp.domainmanage.enumerates.Status;

import java.sql.Timestamp;

@Getter @Setter
public class BasisResponse {
    private Long id;
    private String name;
    private Status status;
    private Timestamp createdTime;
    private Timestamp updatedTime;

}
