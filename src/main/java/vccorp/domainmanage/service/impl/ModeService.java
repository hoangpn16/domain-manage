package vccorp.domainmanage.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vccorp.domainmanage.converter.Converter;
import vccorp.domainmanage.dto.request.NewModeRequest;
import vccorp.domainmanage.dto.request.UpdateModeRequest;
import vccorp.domainmanage.dto.response.ModeResponse;
import vccorp.domainmanage.enumerates.Status;
import vccorp.domainmanage.exceptions.AppException;
import vccorp.domainmanage.exceptions.ErrorCode;
import vccorp.domainmanage.factory.ResponseFactory;
import vccorp.domainmanage.repository.ModeRepository;
import vccorp.domainmanage.repository.entity.ModeEntity;
import vccorp.domainmanage.service.ModeInterface;
import vccorp.domainmanage.utils.AppUtils;

import java.util.List;

@Service
public class ModeService implements ModeInterface {
    private static final Logger logger = LoggerFactory.getLogger(ModeService.class);
    @Autowired
    private ModeRepository modeRepository;
    @Autowired
    private ResponseFactory factory;


    @Override
    @Transactional
    public ResponseEntity addMode(NewModeRequest request) {
        ModeEntity entity = new ModeEntity();
        AppUtils.copyPropertiesIgnoreNull(request, entity);
        if (request.getStatus() == null) {
            entity.setStatus(Status.DRAFF);
        }
        entity = modeRepository.save(entity);
        ModeResponse data = Converter.toModel(entity, ModeResponse.class);
        return factory.success(data, ModeResponse.class);
    }

    @Override
    @Transactional
    public ResponseEntity updateMode(Long modeId, UpdateModeRequest request) {
        ModeEntity entity = findById(modeId);
        AppUtils.copyPropertiesIgnoreNull(request, entity);
        entity = modeRepository.save(entity);
        ModeResponse data = Converter.toModel(entity, ModeResponse.class);
        return factory.success(data, ModeResponse.class);
    }

    @Override
    @Transactional
    public ResponseEntity deleteMode(Long modeId) {
        ModeEntity entity = findById(modeId);
        modeRepository.delete(entity);
        return factory.success("Deleted", String.class);

    }

    @Override
    public ResponseEntity getAllMode() {
        List<ModeEntity> listMode = modeRepository.findAllByStatus(Status.ACTIVE);
        List<ModeResponse> data = Converter.toList(listMode, ModeResponse.class);
        return factory.success(data, List.class);
    }

    public ModeEntity findById(Long id) {
        ModeEntity entity = modeRepository.findByIdAndStatus(id, Status.ACTIVE);
        if (entity == null) {
            logger.info("Không tìm thấy mode id {}", id);
            throw new AppException(ErrorCode.ENTITY_NOT_FOUND);
        }
        return entity;
    }

}
