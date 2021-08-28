package vccorp.domainmanage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vccorp.domainmanage.converter.Converter;
import vccorp.domainmanage.dto.request.NewDomainRequest;

import vccorp.domainmanage.dto.request.UpdateDomainRequest;
import vccorp.domainmanage.dto.response.DomainResponse;
import vccorp.domainmanage.enumerates.Status;
import vccorp.domainmanage.exceptions.AppException;
import vccorp.domainmanage.exceptions.ErrorCode;
import vccorp.domainmanage.factory.ResponseFactory;
import vccorp.domainmanage.repository.*;
import vccorp.domainmanage.repository.entity.*;
import vccorp.domainmanage.service.DomainInterface;
import vccorp.domainmanage.utils.AppUtils;

import java.util.List;

@Service
public class DomainService implements DomainInterface {
    private static final Logger logger = LoggerFactory.getLogger(DomainService.class);

    @Autowired
    DomainRepository domainRepository;
    @Autowired
    BasisDomainRepository basisDomainRepository;
    @Autowired
    BasisRepository basisRepository;
    @Autowired
    ModeRepository modeRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    GroupDomainRepository groupDomainRepository;
    @Autowired
    ResponseFactory factory;

    public ResponseEntity addDomain(NewDomainRequest request) {
        DomainEntity domainEntity = new DomainEntity();

        if (request.getDomainName() != null) {
            domainEntity.setDomainName(request.getDomainName());
        }
        if (request.getDomainType() != null) {
            domainEntity.setDomainType(request.getDomainType());
        }
        if (request.getLogType() != null) {
            domainEntity.setLogType(request.getLogType());
        }
        if(request.getStatus() != null){
            domainEntity.setStatus(request.getStatus());
        }
        ModeEntity modeEntity = modeRepository.findByIdAndStatusNot(request.getModeId(),Status.DELETED);
        if(modeEntity == null){
            logger.info("Không tìm thấy mode id {}",request.getModeId());
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }
        domainEntity=domainRepository.save(domainEntity);
        for (Long basisId : request.getListBasisId()) {
            BasisEntity basisEntity = basisRepository.findByIdAndStatusNot(basisId, Status.DELETED);
            if(basisEntity == null){
                logger.info("Không tìm thấy basis id {}",basisId);
                throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
            }

            BasisDomainEntity basisDomainEntity = new BasisDomainEntity();
            basisDomainEntity.setDomains(domainEntity);
            basisDomainEntity.setBasis(basisEntity);
            basisDomainEntity.setModes(modeEntity);
            basisDomainEntity.setIsLive(true);

            basisDomainRepository.save(basisDomainEntity);

        }
        for (Long groupId: request.getGroupId()) {
            GroupEntity groupEntity = groupRepository.findByIdAndStatusNot(groupId,Status.DELETED);


            GroupDomainEntity groupDomainEntity = new GroupDomainEntity();

            groupDomainEntity.setDomain(domainEntity);
            groupDomainEntity.setGroup(groupEntity);
            groupDomainRepository.save(groupDomainEntity);
        }

        DomainResponse data = Converter.toModel(domainEntity, DomainResponse.class);
        return factory.success(data, DomainResponse.class);
    }

    @Override
    public ResponseEntity updateDomain(Long domainId, UpdateDomainRequest request) {
        DomainEntity entity= findByDomainId(domainId);

        AppUtils.copyPropertiesIgnoreNull(request,entity);

        entity = domainRepository.save(entity);
        DomainResponse data = Converter.toModel(entity,DomainResponse.class);

        return factory.success(data,DomainResponse.class);
    }

    @Override
    public ResponseEntity deleteDomain(Long domainId) {
        DomainEntity entity = findByDomainId(domainId);

        entity.setStatus(Status.DELETED);
        // Xóa các thực thể liên quan trong bảng trung gian group_domain
        List<GroupDomainEntity> listGroupDomain = groupDomainRepository.findAllByDomainId(domainId);
        groupDomainRepository.deleteAll(listGroupDomain);
        //Xóa các thực thể liên quan trong bảng trung gian basis_domain
        List<BasisDomainEntity> listBasisDomain = basisDomainRepository.findAllByDomains(domainId);
        basisDomainRepository.deleteAll(listBasisDomain);

        return factory.success("Deleted",String.class);
    }

    @Override
    public ResponseEntity getAllDomains() {
        List<DomainEntity> listDomain = domainRepository.findAllByStatusNot(Status.DELETED);
        List<DomainResponse> data = Converter.toList(listDomain,DomainResponse.class);
        return factory.success(data,List.class);
    }

    public DomainEntity findByDomainId(Long domainId){
        DomainEntity entity= domainRepository.findByIdAndStatusNot(domainId,Status.DELETED);

        if(entity == null){
            logger.info("Không tìm thấy domain id {}",domainId);
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }
        return entity;
    }


}
