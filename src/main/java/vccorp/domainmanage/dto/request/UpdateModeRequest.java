package vccorp.domainmanage.dto.request;

import lombok.Getter;
import lombok.Setter;
import vccorp.domainmanage.enumerates.Status;

@Getter @Setter
public class UpdateModeRequest {
    private String modeName;
    private String levelPriority;
    private Status status;
}
