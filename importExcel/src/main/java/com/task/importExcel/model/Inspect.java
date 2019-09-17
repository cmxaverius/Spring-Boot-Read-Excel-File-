package com.task.importExcel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Inspect {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
    @Column
    private String SamplingDate;
    
    @Column
    private String ReportDate;
    
    @Column
    private String InspectorName;
    
    @Column
    private String ReviewerName;
    
    @Column
    private String AdditionalData;
    
}
