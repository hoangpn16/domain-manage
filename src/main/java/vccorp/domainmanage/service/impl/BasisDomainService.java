package vccorp.domainmanage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vccorp.domainmanage.converter.Converter;
import vccorp.domainmanage.dto.request.NewBasisDomainRequest;
import vccorp.domainmanage.dto.request.UpdateBasisDomainRepository;
import vccorp.domainmanage.dto.response.BasisDomainResponse;
import vccorp.domainmanage.enumerates.Status;
import vccorp.domainmanage.exceptions.AppException;
import vccorp.domainmanage.exceptions.ErrorCode;
import vccorp.domainmanage.factory.ResponseFactory;
import vccorp.domainmanage.repository.BasisDomainRepository;
import vccorp.domainmanage.repository.BasisRepository;
import vccorp.domainmanage.repository.DomainRepository;
import vccorp.domainmanage.repository.ModeRepository;
import vccorp.domainmanage.repository.entity.BasisDomainEntity;
import vccorp.domainmanage.repository.entity.BasisEntity;
import vccorp.domainmanage.repository.entity.DomainEntity;
import vccorp.domainmanage.repository.entity.ModeEntity;
import vccorp.domainmanage.service.BasisDomainInterface;

import java.util.List;

@Service
public class BasisDomainService implements BasisDomainInterface {
    private static final Logger logger = LoggerFactory.getLogger(BasisDomainService.class);
    @Autowired
    BasisDomainRepository basisDomainRepository;
    @Autowired
    DomainRepository domainRepository;
    @Autowired
    BasisRepository basisRepository;
    @Autowired
    ModeRepository modeRepository;
    @Autowired
    ResponseFactory factory;

    @Override
    public ResponseEntity addNewBasisDomain(NewBasisDomainRequest request) {

        BasisDomainEntity entity = basisDomainRepository.findByDomainsAndBasis(request.getDomainId(), request.getBasisId());
        if(entity != null){
            logger.info("Thực thể đã tồn tại");
            throw new AppException(ErrorCode.ENTITY_EXISTS);
        }
        entity = new BasisDomainEntity();

        DomainEntity domainEntity = domainRepository.findByIdAndStatusNot(request.getDomainId(), Status.DELETED);
        if(domainEntity == null){
            logger.info("Không tìm thấy domain id {}",request.getDomainId());
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }
        BasisEntity basisEntity = basisRepository.findByIdAndStatusNot(request.getBasisId(),Status.DELETED);
        if(basisEntity == null){
            logger.info("Không tìm thấy basis id {}",request.getBasisId());
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }
        ModeEntity modeEntity = modeRepository.findByIdAndStatusNot(request.getModeId(), Status.DELETED);
        if(modeEntity == null){
            logger.info("Không tìm thấy mode id {}",request.getModeId());
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        entity.setBasis(basisEntity);
        entity.setDomains(domainEntity);
        entity.setModes(modeEntity);
        entity = basisDomainRepository.save(entity);

        BasisDomainResponse data = Converter.toModel(entity,BasisDomainResponse.class);
        return factory.success(data,BasisDomainResponse.class);
    }

    @Override
    public ResponseEntity updateBasisDomain(Long basisDomainId, UpdateBasisDomainRepository request) {
        return null;
    }


    @Override
    public ResponseEntity deleteBasisDomain(Long basisDomainId) {
        return null;
    }

    @Override
    public ResponseEntity getAllByDomainId(Long domainId) {
        List<BasisDomainEntity> listBasisDomain = basisDomainRepository.findAllByDomains(domainId);
        return factory.success(listBasisDomain,List.class);
    }
}
