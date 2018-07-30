package com.ideacome.mock.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ideacome.mock.dto.ItemVO;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ExcelOperateUtil {
	
	private ExcelOperateUtil() {
	}
	
	public static ExcelOperateUtil createInstance() {
		return new ExcelOperateUtil();
	}
	
	public JSONArray getRightFormat(String jsonStr) {
		JSONArray jsonArray = null;
		try {
			jsonArray = JSON.parseArray(jsonStr);
			Iterator<Object> itObj = jsonArray.iterator();
			while(itObj.hasNext()) {
				JSONObject jsonObj = (JSONObject)itObj.next();
				if(!(jsonObj.containsKey("sheetName") &&
					jsonObj.containsKey("rows") &&
					jsonObj.containsKey("cols") )) {
					return null;
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return jsonArray;
	}
	
	public HSSFWorkbook createTemplateExcel(String jsonStr) {
		JSONArray jsonArray = getRightFormat(jsonStr);
		if(jsonArray == null) {
			return null;
		}
		//创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		Iterator<Object> itObj = jsonArray.iterator();
		while(itObj.hasNext()) {
			JSONObject jsonObj = (JSONObject)itObj.next();
			//创建工作表sheet
			HSSFSheet sheet=workbook.createSheet(jsonObj.getString("sheetName"));
			JSONObject rows = jsonObj.getJSONObject("rows");
			ItemVO rowItemVO = new ItemVO();
			colObj = 1;
			getItemList(rows,0,rowItemVO);
			System.out.println("rows:maxRow-"+rowItemVO.findMaxRow()+",maxCol:"+rowItemVO.findMaxCol());
			JSONObject cols = jsonObj.getJSONObject("cols");
			ItemVO colItemVO = new ItemVO();
			colObj = 1;
			getItemList(cols,0,colItemVO);
			System.out.println("rows:maxRow-"+colItemVO.findMaxRow()+",maxCol:"+colItemVO.findMaxCol());
			System.out.println("================================");

			for(int i=0;i<rowItemVO.getRowMax();i++){
				List<ItemVO> nextItemVOList = rowItemVO.getTotalItems();
				HSSFRow hssfRow = sheet.createRow(i);
				for(ItemVO item : nextItemVOList){
					if(item.getRowStart()-1 == i){
						CellRangeAddress celRangeAddress = new CellRangeAddress(item.getRowStart()-1,item.getRowEnd()-1,
								colItemVO.getRowMax()+item.getColStart()-1,colItemVO.getRowMax()+item.getColEnd()-1);
						sheet.addMergedRegion(celRangeAddress);

						HSSFCell cell = hssfRow.createCell(colItemVO.getRowMax()+item.getColStart() - 1);

						cell.setCellValue(item.getItemName());
					}
				}
			}
			for(int i=rowItemVO.getRowMax();i<rowItemVO.getRowMax()+colItemVO.getColMax();i++){
				List<ItemVO> totalItems = colItemVO.getTotalItems();
				HSSFRow hssfRow = sheet.createRow(i);
				for(ItemVO item : totalItems){
					if(item.getColStart()+rowItemVO.getRowMax()-1 == i){
						CellRangeAddress celRangeAddress = new CellRangeAddress(rowItemVO.getRowMax()+item.getColStart()-1,rowItemVO.getRowMax()+item.getColEnd()-1,
								item.getRowStart()-1,item.getRowEnd()-1
						);
						sheet.addMergedRegion(celRangeAddress);
						HSSFCell cell = hssfRow.createCell(item.getRowStart() - 1);
						cell.setCellValue(item.getItemName());
					}
				}
			}
		}
		
		return workbook;
	}
	
	private Integer colObj = 1;
	public void getItemList(JSONObject jsonObj,int row,ItemVO itemVO) {
		row++;
		int col = 0;
		Set<String> keys = jsonObj.keySet();
		for(String key : keys ) {
			col = colObj.intValue();
			ItemVO keyItemVO = new ItemVO();
			keyItemVO.setRowStart(row);
			keyItemVO.setRowEnd(row);
			keyItemVO.setColStart(col);
			keyItemVO.setColEnd(col-1);
			keyItemVO.setItemName(key);

			keyItemVO.setPreItemVO(itemVO);
			
			itemVO.addOneItem(keyItemVO);
			
			Object obj = jsonObj.get(key);
			if(obj instanceof JSONObject) {//当子元素为json对象时，再往下层走
				getItemList((JSONObject)obj,row,keyItemVO);
			}else if(obj instanceof List){
				List list = (List)obj;
				int rowNum = row + 1;
				for(Object object : list) {
					ItemVO childItem = new ItemVO();
					childItem.setItemName((String)object);
					childItem.setRowEnd(rowNum);
					childItem.setRowStart(rowNum);
					childItem.setColStart(col);
					childItem.setColEnd(col);
					keyItemVO.addOneItem(childItem);
					keyItemVO.increColEnd();
					col++;
				}
				colObj = Integer.valueOf(col);
			}else if(obj instanceof String) {
				if("".equals(obj)){
					obj = key;
				}
				int rowNum = row + 1;
				ItemVO childItem = new ItemVO();
				childItem.setItemName((String)obj);
				childItem.setRowStart(rowNum);
				childItem.setRowEnd(rowNum);
				childItem.setColStart(col);
				childItem.setColEnd(col);
				keyItemVO.addOneItem(childItem);
				keyItemVO.increColEnd();
				col ++ ;
				colObj = Integer.valueOf(col);
			}
		}
	}

	public void readDataInExcel(String filePath){

	}
	
	public static void main(String[] args) throws Exception {
		HSSFWorkbook templateExcel = ExcelOperateUtil.createInstance().createTemplateExcel(FileOperateUtil.defaultJsonFile2String());
		FileOutputStream fos = new FileOutputStream("D:\\22.xls");
		templateExcel.write(fos);

		fos.close();
	}
}
