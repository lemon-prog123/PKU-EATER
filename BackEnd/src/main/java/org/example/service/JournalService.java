package org.example.service;

import org.example.error.BusinessException;
import org.example.service.model.CanteenModel;
import org.example.service.model.FoodModel;
import org.example.service.model.JournalModel;

import java.util.List;

public interface JournalService {
    List<JournalModel> ListJournalByUsrId(Integer uid);

    void createJournal(JournalModel journalModel) throws BusinessException;

    void deleteJournal(Integer id, Integer uid) throws BusinessException;

    JournalModel getJournalById(Integer id);
}
