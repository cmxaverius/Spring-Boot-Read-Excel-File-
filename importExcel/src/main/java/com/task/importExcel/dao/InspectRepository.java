package com.task.importExcel.dao;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.task.importExcel.model.Inspect;

@Repository
public interface InspectRepository extends CrudRepository<Inspect,Integer>{ 

}
