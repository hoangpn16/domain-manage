package vccorp.domainmanage.dto.request;

import lombok.Getter;
import lombok.Setter;
import vccorp.domainmanage.enumerates.Status;


@Getter @Setter
public class NewGroupRequest {
    private String groupName;
    private String description;
    private Status status;
}
