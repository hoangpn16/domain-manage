package vccorp.domainmanage.service.impl;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vccorp.domainmanage.converter.Converter;
import vccorp.domainmanage.dto.request.NewGroupDomainRequest;
import vccorp.domainmanage.dto.response.GroupDomainResponse;
import vccorp.domainmanage.dto.response.GroupResponse;
import vccorp.domainmanage.enumerates.Status;
import vccorp.domainmanage.exceptions.AppException;
import vccorp.domainmanage.exceptions.ErrorCode;
import vccorp.domainmanage.factory.ResponseFactory;
import vccorp.domainmanage.repository.DomainRepository;
import vccorp.domainmanage.repository.GroupDomainRepository;
import vccorp.domainmanage.repository.GroupRepository;
import vccorp.domainmanage.repository.entity.DomainEntity;
import vccorp.domainmanage.repository.entity.GroupDomainEntity;
import vccorp.domainmanage.repository.entity.GroupEntity;
import vccorp.domainmanage.service.GroupDomainInterface;

import java.util.List;

@Service
public class GroupDomainService implements GroupDomainInterface {
    private static final Logger logger = LoggerFactory.getLogger(GroupDomainService.class);

    @Autowired
    private GroupDomainRepository groupDomainRepository;
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private DomainRepository domainRepository;

    @Autowired
    private ResponseFactory factory;


    @Override
    @Transactional
    public ResponseEntity addNew(NewGroupDomainRequest request) {
        GroupEntity groupEntity = groupRepository.findByIdAndStatus(request.getGroupId(), Status.ACTIVE);
        if(groupEntity == null){
            logger.info("Không tìm thấy group id {}",request.getGroupId());
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        DomainEntity domainEntity = domainRepository.findByIdAndStatus(request.getDomainId(), Status.ACTIVE);
        if(groupEntity == null){
            logger.info("Không tìm thấy domain id{}",request.getDomainId());
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        GroupDomainEntity entity = groupDomainRepository.findByDomainIdAndGroupId(request.getDomainId(), request.getGroupId());
        if(entity != null){
            logger.info("Quan hệ đã tồn tại với domain id {} và group id {}",request.getDomainId(),request.getGroupId());
            throw new AppException(ErrorCode.ENTITY_EXISTS);
        }

        entity = new GroupDomainEntity();
        entity.setGroup(groupEntity);
        entity.setDomain(domainEntity);

        entity = groupDomainRepository.save(entity);
        GroupDomainResponse data = Converter.toModel(entity,GroupDomainResponse.class);

        return factory.success(data,GroupDomainResponse.class);
    }

    @Override
    @Transactional
    public ResponseEntity delete(Long groupDomainId) {
        GroupDomainEntity entity = groupDomainRepository.findById(groupDomainId);
        if(entity == null){
            logger.info("Không tìm thấy group domain id {}",groupDomainId);
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }
        groupDomainRepository.delete(entity);
        return factory.success("Deleted",String.class);
    }

    @Override
    public ResponseEntity getAllByDomainId(Long domainId) {
        List<GroupDomainEntity> listGroupDomain = groupDomainRepository.findAllByDomainId(domainId);
        List<GroupDomainResponse> data = Converter.toList(listGroupDomain,GroupDomainResponse.class);
        return factory.success(data,List.class);
    }

    @Override
    public ResponseEntity getAllByGroupId(Long groupId) {
        List<GroupDomainEntity> listGroupDomain = groupDomainRepository.findAllByDomainId(groupId);
        List<GroupDomainResponse> data = Converter.toList(listGroupDomain,GroupDomainResponse.class);
        return factory.success(data,List.class);
    }

}
