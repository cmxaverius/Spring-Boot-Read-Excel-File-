package com.task.importExcel.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.task.importExcel.model.SamplingData;

@Repository
public interface SamplingDataRepositoy extends CrudRepository<SamplingData, Integer>{

}
