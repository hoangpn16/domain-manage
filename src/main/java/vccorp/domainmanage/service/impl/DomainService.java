package vccorp.domainmanage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vccorp.domainmanage.converter.Converter;
import vccorp.domainmanage.dto.request.NewDomainRequest;

import vccorp.domainmanage.dto.response.DomainResponse;
import vccorp.domainmanage.enumerates.Status;
import vccorp.domainmanage.exceptions.AppException;
import vccorp.domainmanage.exceptions.ErrorCode;
import vccorp.domainmanage.factory.ResponseFactory;
import vccorp.domainmanage.repository.*;
import vccorp.domainmanage.repository.entity.*;
import vccorp.domainmanage.service.DomainInterface;

@Service
public class DomainService implements DomainInterface {
    private static final Logger logger = LoggerFactory.getLogger(DomainService.class);

    @Autowired
    DomainRepository domainRepository;
    @Autowired
    BasisDomainRepository basisDomainRepository;
    @Autowired
    BasisDomainModeRepository basisDomainModeRepository;
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
        ModeEntity modeEntity = modeRepository.findByIdAndStatusNot(request.getModeId(),Status.DELETED);
        if(modeEntity == null){
            logger.info("Không tìm thấy mode id {}",request.getModeId());
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }
        domainRepository.save(domainEntity);
        for (Long basisId : request.getListBasisId()) {
            BasisEntity basisEntity = basisRepository.findByIdAndStatusNot(basisId, Status.DELETED);
            if(basisEntity == null){
                logger.info("Không tìm thấy basis id {}",basisId);
                throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
            }

            BasisDomainEntity basisDomainEntity = new BasisDomainEntity();
            basisDomainEntity.setDomain(domainEntity);
            basisDomainEntity.setBasis(basisEntity);
            basisDomainEntity.setCurrentMode(modeEntity);

            basisDomainRepository.save(basisDomainEntity);

            BasisDomainModeEntity basisDomainModeEntity = new BasisDomainModeEntity();
            basisDomainModeEntity.setBasisDomain(basisDomainEntity);
            basisDomainModeEntity.setMode(modeEntity);
            basisDomainModeRepository.save(basisDomainModeEntity);
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


}
