package com.task.importExcel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.Data;

@Data
@Entity
public class SamplingData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
    @Column
    private String MaterialName;   
    
    @Column
    private String SamplingDate;
    
    @Column
	private String AI203;
    
    @Column
	private String SiO2;
    
    @Column
	private String Fe203;
    
    @Column
 	private String TiO2;
    
    @Column
    private String CaO;
    
    @Column
    private String MgO;
    
    @Column
    private String AdivideS;
    
    @Column
    private String Na2O;
    
    @Column
    private String CadivideS;
    
    @Column
    private String NdivideS;
    
    @Column
    private String AcidSilicon;
    
    @Column
    private String K2O;
    
    @Column
    private String C;
    
    @Column
    private String S;
    
    @Column
    private String Water;
    
    @Column
    private String CaOT; 
    
    @Column
    private String CaOf;
    
    @Column
    private String CO2;
    
    @Column
    private String CausticSoda;
    
    @Column
    private String Remarks;
}




