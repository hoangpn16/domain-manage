package vccorp.domainmanage.dto.request;


import lombok.Getter;
import lombok.Setter;
import vccorp.domainmanage.enumerates.Status;

@Getter @Setter
public class NewBasisRequest {
    private String name;
    private Status status;
}
