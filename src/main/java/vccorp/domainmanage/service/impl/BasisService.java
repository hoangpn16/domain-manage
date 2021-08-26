package vccorp.domainmanage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vccorp.domainmanage.converter.Converter;
import vccorp.domainmanage.dto.request.NewBasisRequest;
import vccorp.domainmanage.dto.request.UpdateBasisRequest;
import vccorp.domainmanage.dto.response.BasisResponse;
import vccorp.domainmanage.enumerates.Status;
import vccorp.domainmanage.exceptions.AppException;
import vccorp.domainmanage.exceptions.ErrorCode;
import vccorp.domainmanage.factory.ResponseFactory;
import vccorp.domainmanage.repository.BasisRepository;
import vccorp.domainmanage.repository.entity.BasisEntity;
import vccorp.domainmanage.service.BasisInterface;
import vccorp.domainmanage.utils.AppUtils;

import java.util.List;

@Service
public class BasisService implements BasisInterface {
    private static final Logger logger = LoggerFactory.getLogger(BasisService.class);

    @Autowired
    private BasisRepository basisRepository;
    @Autowired
    private ResponseFactory factory;

    @Override
    public ResponseEntity addBasis(NewBasisRequest request) {
        BasisEntity entity = new BasisEntity();
        AppUtils.copyPropertiesIgnoreNull(request, entity);
        if (request.getStatus() == null) {
            entity.setStatus(Status.DRAFF);
        }
        entity = basisRepository.save(entity);
        BasisResponse data = Converter.toModel(entity, BasisResponse.class);
        return factory.success(data, BasisResponse.class);
    }

    @Override
    public ResponseEntity updateBasis(Long basisId, UpdateBasisRequest request) {
        BasisEntity entity = findById(basisId);
        AppUtils.copyPropertiesIgnoreNull(request, entity);
        entity = basisRepository.save(entity);
        BasisResponse data = Converter.toModel(entity, BasisResponse.class);
        return factory.success(data, BasisResponse.class);
    }

    @Override
    public ResponseEntity deleteBasis(Long basisId) {
        BasisEntity entity = findById(basisId);
        entity.setStatus(Status.DELETED);
        basisRepository.save(entity);
        return factory.success("Deleted",String.class);
    }

    @Override
    public ResponseEntity getAllBasis() {
        List<BasisEntity> listBasis = basisRepository.findAllByStatusNot(Status.DELETED);
        List<BasisResponse> data = Converter.toList(listBasis,BasisResponse.class);
        return factory.success(data,List.class);
    }

    public BasisEntity findById(Long id) {
        BasisEntity entity = basisRepository.findByIdAndStatusNot(id, Status.DELETED);
        if (entity == null) {
            logger.info("Không tìm thấy basis id {}", id);
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }
        return entity;
    }
}
