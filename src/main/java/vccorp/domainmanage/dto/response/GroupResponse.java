package vccorp.domainmanage.dto.response;

import lombok.Getter;
import lombok.Setter;
import vccorp.domainmanage.enumerates.Status;
import vccorp.domainmanage.repository.entity.GroupDomainEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@Getter @Setter
public class GroupResponse{
    private Long id;
    private String groupName;
    private String description;
    private Status status;
    private Timestamp createdTime;
    private Timestamp updatedTime;
    private List<GroupDomainEntity> listGroupDomains = new ArrayList<>();
}
