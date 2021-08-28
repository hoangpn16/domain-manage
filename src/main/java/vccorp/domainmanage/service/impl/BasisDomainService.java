package vccorp.domainmanage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vccorp.domainmanage.converter.Converter;
import vccorp.domainmanage.dto.request.NewBasisDomainRequest;
import vccorp.domainmanage.dto.request.UpdateBasisDomainRequest;
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
import vccorp.domainmanage.utils.AppUtils;

import java.util.ArrayList;
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
    @Transactional
    public ResponseEntity addNewBasisDomain(NewBasisDomainRequest request) {
        DomainEntity domainEntity = domainRepository.findByIdAndStatus(request.getDomainId(), Status.ACTIVE);
        if (domainEntity == null) {
            logger.info("Không tìm thấy domain id{}", request.getDomainId());
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        BasisEntity basisEntity = basisRepository.findByIdAndStatus(request.getBasisId(), Status.ACTIVE);
        if (basisEntity == null) {
            logger.info("Không tìm thấy basis id{}", request.getBasisId());
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }
        BasisDomainEntity entity = basisDomainRepository.findByDomainIdAndBasisId(request.getDomainId(), request.getBasisId());
        if (entity != null) {
            logger.info("Thực thể đã tồn tại");
            throw new AppException(ErrorCode.ENTITY_EXISTS);
        }
        entity = new BasisDomainEntity();


        ModeEntity modeEntity = modeRepository.findByIdAndStatus(request.getModeId(), Status.ACTIVE);
        if (modeEntity == null) {
            logger.info("Không tìm thấy mode id {}", request.getModeId());
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }

        entity.setBasis(basisEntity);
        entity.setDomains(domainEntity);
        entity.setModes(modeEntity);
        entity = basisDomainRepository.save(entity);

        BasisDomainResponse data = Converter.toModel(entity, BasisDomainResponse.class);
        return factory.success(data, BasisDomainResponse.class);
    }

    @Override
    @Transactional
    public ResponseEntity updateBasisDomain(Long basisDomainId, UpdateBasisDomainRequest request) {
        ModeEntity modeEntity = modeRepository.findByIdAndStatus(request.getModeId(), Status.ACTIVE);
        if (modeEntity == null) {
            logger.info("Không tìm thấy mode id{}", request.getModeId());
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }
        BasisDomainEntity entity = getById(basisDomainId);

        BasisDomainEntity oldEntity = new BasisDomainEntity();
        oldEntity.setBasis(entity.getBasis());
        oldEntity.setDomains(entity.getDomains());
        oldEntity.setModes(entity.getModes());
        oldEntity.setIsLive(false);

        entity.setModes(modeEntity);


        List<BasisDomainEntity> listBasisDomain = new ArrayList<>();
        listBasisDomain.add(entity);
        listBasisDomain.add(oldEntity);
        basisDomainRepository.saveAll(listBasisDomain);

        BasisDomainResponse data = Converter.toModel(entity, BasisDomainResponse.class);
        return factory.success(data, BasisDomainResponse.class);
    }


    @Override
    @Transactional
    public ResponseEntity deleteBasisDomain(Long basisDomainId) {
        BasisDomainEntity entity = getById(basisDomainId);
        basisDomainRepository.delete(entity);
        return factory.success("Deleted", String.class);
    }

    @Override
    public ResponseEntity getAllByDomainId(Long domainId) {
        List<BasisDomainEntity> listBasisDomain = basisDomainRepository.findAllByDomainId(domainId);

        List<BasisDomainResponse> data = Converter.toList(listBasisDomain, BasisDomainResponse.class);
        return factory.success(data, List.class);
    }

    @Override
    public ResponseEntity getAllHistoryMode(Long domainId) {
        List<BasisDomainEntity> listBasisDomain = basisDomainRepository.finnAllHistoryByDomainId(domainId);

        List<BasisDomainResponse> data = Converter.toList(listBasisDomain, BasisDomainResponse.class);
        return factory.success(data, List.class);
    }

    public BasisDomainEntity getById(Long basisDomainId) {
        BasisDomainEntity entity = basisDomainRepository.findOneById(basisDomainId);
        if (entity == null) {
            logger.info("Không tìm thấy basis domain id {}", basisDomainId);
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }
        return entity;
    }
}
