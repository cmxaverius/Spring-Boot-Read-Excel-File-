package com.task.importExcel.controller;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.ArrayUtils;

import com.task.importExcel.dao.InspectRepository;
import com.task.importExcel.dao.SamplingDataRepositoy;
import com.task.importExcel.model.Inspect;
import com.task.importExcel.model.SamplingData;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@Controller
public class ImportExcelController {

	@Autowired
	SamplingDataRepositoy samplingDataRepositoy;
	@Autowired
	InspectRepository inspectRepository;
	
	@RequestMapping("/upload-file")
    public ModelAndView uploadFile() {
		return new ModelAndView("upload");
    }
	
	@RequestMapping("/table")
    public String table(Model model) {
		model.addAttribute("sampling",samplingDataRepositoy.findAll());
		model.addAttribute("inspect",inspectRepository.findAll());
		return "table";
    }
	
	
	  @PostMapping("/upload")
	  //@RequestMapping("/upload")
	  //@ResponseBody
	  public String uploadDDZJH( MultipartFile file) throws Exception {
		  if (file.isEmpty()) {
		      return "文件为空";
		  }
		  // 获取文件名
		    String fileName = file.getOriginalFilename();
		   
		    InputStream is = file.getInputStream();
		 
			XSSFWorkbook wb = new XSSFWorkbook(is); 	         
	        Excle2007(wb);
		    try {
		     			
		      //return "上传成功";
		      return "redirect:/table";
		    } catch (IllegalStateException e) {
		      e.printStackTrace();
		    }	    
		    return "上传失败";
		   
	  }
	
	
	  public void Excle2007(XSSFWorkbook workbook) throws Exception{
		  List<SamplingData> SamplingList = new ArrayList<SamplingData>();  
		  List<Inspect> InspectList = new ArrayList<Inspect>();
		  
		  String[] List1 = new String[] {"入磨矿石","原矿浆","脱硅首槽","脱硅末槽","溶出矿浆",
				  						"赤泥","稀释后矿浆","沉降末洗底流","赤泥1L","赤泥有碱","三洗底流"};
		  String[] List2 = new String[] {"入磨石灰"};
		  String[] List3 = new String[] {"平盘滤饼I","首槽I","首槽II"};
		  String[] List4 = new String[] {"焙烧","出库氧化铝3#"};
		  
		  XSSFSheet xsheet = workbook.getSheetAt(0);
		  int rowNum = xsheet.getLastRowNum();
		  

		  
		  Inspect inspect = new Inspect();
		  
	      for(int i = 0; i <= rowNum ; i++)
	      {
	    	  XSSFRow xhrow = xsheet.getRow(i);  
	    	  	  
		    if(i == 1){	
		    	inspect.setSamplingDate(getCellFormatValueXSSF(xhrow.getCell(1)));
		    	inspect.setReportDate(getCellFormatValueXSSF(xhrow.getCell(9)));
		    }
		    if (i == 2 ){
		    	inspect.setInspectorName(getCellFormatValueXSSF(xhrow.getCell(1)));
		    	inspect.setReviewerName(getCellFormatValueXSSF(xhrow.getCell(9)));
		    }
		    if(i==3){
		    	inspect.setAdditionalData(getCellFormatValueXSSF(xhrow.getCell(1)));
		    }
		    
		    
	    	  String checkName = getCellFormatValueXSSF(xhrow.getCell(0)).toString();  
	    	  //System.out.println(checkName);
	    	  
//	    	  Row row = xsheet.getRow(i);
//	    	  Cell cell = row.getCell(0);

	    	  
	    	  //if(checkName.equals("溶出矿浆")) 
	    	  if ( ArrayUtils.contains( List1, checkName )) {
			    	SamplingData sd = new SamplingData();    
			    	sd.setMaterialName(getCellFormatValueXSSF(xhrow.getCell(0)));
			    	sd.setSamplingDate(getCellFormatValueXSSF(xhrow.getCell(1)));
			    	sd.setAI203(getCellFormatValueXSSF(xhrow.getCell(2)));
			    	sd.setFe203(getCellFormatValueXSSF(xhrow.getCell(3)));
			    	sd.setTiO2(getCellFormatValueXSSF(xhrow.getCell(4)));
			    	sd.setCaO(getCellFormatValueXSSF(xhrow.getCell(5)));
			    	sd.setMgO(getCellFormatValueXSSF(xhrow.getCell(6)));
			    	sd.setAI203(getCellFormatValueXSSF(xhrow.getCell(7)));
			    	sd.setAdivideS(getCellFormatValueXSSF(xhrow.getCell(8)));
			    	sd.setNa2O(getCellFormatValueXSSF(xhrow.getCell(9)));
			    	sd.setCadivideS(getCellFormatValueXSSF(xhrow.getCell(10)));
			    	sd.setNdivideS(getCellFormatValueXSSF(xhrow.getCell(11)));
			    	sd.setAcidSilicon(getCellFormatValueXSSF(xhrow.getCell(12)));
			    	sd.setK2O(getCellFormatValueXSSF(xhrow.getCell(13)));
			    	sd.setC(getCellFormatValueXSSF(xhrow.getCell(14)));
			    	sd.setS(getCellFormatValueXSSF(xhrow.getCell(15)));
			    	sd.setWater(getCellFormatValueXSSF(xhrow.getCell(16)));
			    	
			    	SamplingList.add(sd);
	    	  } 
	    	  
	    	  if ( ArrayUtils.contains( List2, checkName )) {
			    	SamplingData sd = new SamplingData();    
			    	sd.setMaterialName(getCellFormatValueXSSF(xhrow.getCell(0)));
			    	sd.setSamplingDate(getCellFormatValueXSSF(xhrow.getCell(1)));
			    	sd.setCaOT(getCellFormatValueXSSF(xhrow.getCell(2)));
			    	sd.setMgO(getCellFormatValueXSSF(xhrow.getCell(3)));
			    	sd.setSiO2(getCellFormatValueXSSF(xhrow.getCell(4)));
			    	sd.setFe203(getCellFormatValueXSSF(xhrow.getCell(5)));
			    	sd.setAI203(getCellFormatValueXSSF(xhrow.getCell(6)));
			    	sd.setCaOf(getCellFormatValueXSSF(xhrow.getCell(7)));
			    	sd.setCO2(getCellFormatValueXSSF(xhrow.getCell(8)));
			    	sd.setRemarks(getCellFormatValueXSSF(xhrow.getCell(9)));
			    	
			    	SamplingList.add(sd);
	    	  } 
	    	  
	    	  if ( ArrayUtils.contains( List3, checkName )) {
			    	SamplingData sd = new SamplingData();    
			    	sd.setMaterialName(getCellFormatValueXSSF(xhrow.getCell(0)));
			    	sd.setSamplingDate(getCellFormatValueXSSF(xhrow.getCell(1)));
			    	sd.setSiO2(getCellFormatValueXSSF(xhrow.getCell(2)));
			    	sd.setFe203(getCellFormatValueXSSF(xhrow.getCell(3)));
			    	sd.setNa2O(getCellFormatValueXSSF(xhrow.getCell(4)));
			    	sd.setRemarks(getCellFormatValueXSSF(xhrow.getCell(5)));
			    	
			    	SamplingList.add(sd);
	    	  }  
	    	  
	    	  
	    	  if ( ArrayUtils.contains( List4, checkName )) {
			    	SamplingData sd = new SamplingData();    
			    	sd.setMaterialName(getCellFormatValueXSSF(xhrow.getCell(0)));
			    	sd.setSamplingDate(getCellFormatValueXSSF(xhrow.getCell(1)));
			    	sd.setAI203(getCellFormatValueXSSF(xhrow.getCell(2)));
			    	sd.setSiO2(getCellFormatValueXSSF(xhrow.getCell(3)));
			    	sd.setFe203(getCellFormatValueXSSF(xhrow.getCell(4)));
			    	sd.setNa2O(getCellFormatValueXSSF(xhrow.getCell(5)));
			    	sd.setCausticSoda(getCellFormatValueXSSF(xhrow.getCell(6)));
			    	sd.setRemarks(getCellFormatValueXSSF(xhrow.getCell(7)));
			    	
			    	SamplingList.add(sd);
	    	  } 
	    	  
	      	InspectList.add(inspect); 	      	
	      	}	   
	      inspectRepository.saveAll(InspectList);
	      samplingDataRepositoy.saveAll(SamplingList);
	  }
	  
		private String getCellFormatValueXSSF(XSSFCell cell) {
		        String cellvalue = "";
		        if (cell != null) {
		            // 判断当前Cell的Type
		            switch (cell.getCellType()) {
		            // 如果当前Cell的Type为NUMERIC
		            case XSSFCell.CELL_TYPE_NUMERIC:
		            case XSSFCell.CELL_TYPE_FORMULA: {
		                // 判断当前的cell是否为Date
		                if (DateUtil.isCellDateFormatted(cell)) {
		                    // 如果是Date类型则，转化为Data格式
		                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
		                    //cellvalue = cell.getDateCellValue().toLocaleString();                    
		                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
		                    Date date = cell.getDateCellValue();
		                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		                    cellvalue = sdf.format(date);	                    
		                }
		                // 如果是纯数字
		                else {
		                    // 取得当前Cell的数值
		                    cellvalue =(String.valueOf(cell.getNumericCellValue()));
		                }
		                break;
		            }
		            // 如果当前Cell的Type为STRIN
		            case XSSFCell.CELL_TYPE_STRING:
		                // 取得当前的Cell字符串
		                cellvalue = cell.getRichStringCellValue().getString();
		                if (cellvalue.equals("/")) {
							cellvalue = null;
						}
		                break;
		            // 默认的Cell值
		            default:
		                cellvalue = " ";
		            }
		        
		        } else {
		            cellvalue = "";
		        }
		        return cellvalue; 
		    }	
}
