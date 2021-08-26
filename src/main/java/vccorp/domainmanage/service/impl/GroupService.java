package vccorp.domainmanage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vccorp.domainmanage.converter.Converter;
import vccorp.domainmanage.dto.request.NewGroupRequest;
import vccorp.domainmanage.dto.request.UpdateGroupRequest;
import vccorp.domainmanage.dto.response.GroupResponse;
import vccorp.domainmanage.enumerates.Status;
import vccorp.domainmanage.exceptions.AppException;
import vccorp.domainmanage.exceptions.ErrorCode;
import vccorp.domainmanage.factory.ResponseFactory;
import vccorp.domainmanage.repository.GroupRepository;
import vccorp.domainmanage.repository.entity.GroupEntity;
import vccorp.domainmanage.service.GroupInterface;
import vccorp.domainmanage.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;


@Service
public class GroupService implements GroupInterface {
    private static final Logger logger = LoggerFactory.getLogger(GroupService.class);

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private ResponseFactory factory;

    @Override
    @Transactional
    public ResponseEntity addGroup(NewGroupRequest request) {
        GroupEntity entity = new GroupEntity();
        AppUtils.copyPropertiesIgnoreNull(request,entity);
        if (request.getStatus() == null) {
            entity.setStatus(Status.DRAFF);
        }

        entity = groupRepository.save(entity);
        GroupResponse data = Converter.toModel(entity, GroupResponse.class);
        return factory.success(data, GroupResponse.class);
    }

    @Override
    @Transactional
    public ResponseEntity updateGroup(Long groupId, UpdateGroupRequest request) {
        GroupEntity entity = findById(groupId);
        AppUtils.copyPropertiesIgnoreNull(request,entity);
        entity = groupRepository.save(entity);
        GroupResponse data = Converter.toModel(entity, GroupResponse.class);
        return factory.success(data, GroupResponse.class);
    }

    @Override
    @Transactional
    public ResponseEntity deleteGroup(Long groupId) {
        GroupEntity entity = findById(groupId);
        entity.setStatus(Status.DELETED);
        groupRepository.save(entity);
        return factory.success("Deleted", String.class);
    }

    @Override
    public ResponseEntity getAllGroup() {
        List<GroupEntity> listGroup = groupRepository.findAllByStatusNot(Status.DELETED);
        List<GroupResponse> data = Converter.toList(listGroup, GroupResponse.class);

        return factory.success(data, List.class);
    }

    public GroupEntity findById(Long id) {
        GroupEntity entity = groupRepository.findByIdAndStatusNot(id, Status.DELETED);
        if (entity == null) {
            logger.info("Không tìm thấy group id {}", id);
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }
        return entity;
    }
}
