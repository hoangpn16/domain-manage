package vccorp.domainmanage.dto.request;

import lombok.Getter;
import lombok.Setter;
import vccorp.domainmanage.enumerates.Status;

@Getter @Setter
public class UpdateBasisRequest {
    private String name;
    private Status status;
}
