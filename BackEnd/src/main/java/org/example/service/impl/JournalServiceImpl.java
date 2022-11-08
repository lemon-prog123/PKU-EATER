package org.example.service.impl;

import org.example.dao.JournalDOMapper;
import org.example.dataobject.JournalDO;
import org.example.error.BusinessException;
import org.example.error.EmBusinessError;
import org.example.service.JournalService;
import org.example.service.model.JournalModel;
import org.example.validator.ValidationResult;
import org.example.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private ValidatorImpl validator;

    @Autowired
    private JournalDOMapper journalDOMapper;

    @Override
    public List<JournalModel> ListJournalByUsrId(Integer uid) {
            List<JournalDO> journalDOList = journalDOMapper.ListJournalByUsrId(uid);
            List<JournalModel> journalModelList = journalDOList.stream().map(journalDO -> {

                JournalModel journalModel = new JournalModel();
                BeanUtils.copyProperties(journalDO,journalModel);
                return journalModel;
            }).collect(Collectors.toList());
            return journalModelList;
    }

    @Override
    @Transactional
    public JournalModel createJournal(JournalModel journalModel) throws BusinessException {
        //校验入参
        ValidationResult result = validator.validate(journalModel);
        if(result.isHasErrors()){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,result.getErrMsg());
        }
        //转化journalmodel->dataobject
        JournalDO journalDO = this.convertJournalDOFromJournalModel(journalModel);

        //写入数据库
        journalDOMapper.insertSelective(journalDO);
        journalModel.setId(journalDO.getId());

        //返回创建完成的对象
        return this.getJournalById(journalModel.getId());
    }

    @Override
    public JournalModel getJournalById(Integer id) {
        JournalDO journalDO = journalDOMapper.selectByPrimaryKey(id);
        if (journalDO == null) {
            return null;
        }
        //将dataobject->Model
        JournalModel journalModel = new JournalModel();
        BeanUtils.copyProperties(journalDO, journalModel);
        return journalModel;
    }

    private JournalDO convertJournalDOFromJournalModel(JournalModel journalModel) {
        if (journalModel == null) {
            return null;
        }
        JournalDO journalDO = new JournalDO();
        BeanUtils.copyProperties(journalModel, journalDO);
        return journalDO;
    }
}
