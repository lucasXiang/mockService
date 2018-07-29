package com.ideacome.mock.dto;

import java.util.ArrayList;
import java.util.List;

public class ItemVO {
	private int rowMax;
	private int colMax;
	private List<ItemVO> totalItems = new ArrayList<>();
	private String itemName;
	private int rowStart;
	private int rowEnd;
	private int colStart;
	private int colEnd;
	
	private List<ItemVO> nextItemVOList = new ArrayList<>();
	private ItemVO preItemVO;

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getRowStart() {
		return rowStart;
	}

	public void setRowStart(int rowStart) {
		this.rowStart = rowStart;
	}

	public int getRowEnd() {
		return rowEnd;
	}

	public void setRowEnd(int rowEnd) {
		this.rowEnd = rowEnd;
	}

	public int getColStart() {
		return colStart;
	}

	public void setColStart(int colStart) {
		this.colStart = colStart;
	}

	public int getColEnd() {
		return colEnd;
	}

	public void setColEnd(int colEnd) {
		this.colEnd = colEnd;
	}

	public ItemVO getPreItemVO() {
		return preItemVO;
	}

	public void setPreItemVO(ItemVO preItemVO) {
		this.preItemVO = preItemVO;
	}

	public void addOneItem(ItemVO nextItemVO) {

		nextItemVOList.add(nextItemVO);
	}
	
	public void increColEnd() {
		if(preItemVO == null) {
			return;
		}
		colEnd ++;
		this.preItemVO.increColEnd();
	}

	public void increRowEnd() {
		rowEnd ++;
	}

	public int getRowMax() {
		return rowMax;
	}

	public void setRowMax(int rowMax) {
		this.rowMax = rowMax;
	}

	public int getColMax() {
		return colMax;
	}

	public void setColMax(int colMax) {
		this.colMax = colMax;
	}
	public int findMaxRow(){
		rowMax = 0;
		getNextRowItemVO(this,totalItems);
		for(ItemVO item : totalItems){
			if(item.getRowEnd() > rowMax){
				rowMax = item.getRowEnd();
			}
		}
		return rowMax;
	}

	public List<ItemVO> getTotalItems() {
		return totalItems;
	}

	public List<ItemVO> getNextItemVOList() {
		return nextItemVOList;
	}

	public void getNextRowItemVO(ItemVO itemVO,List<ItemVO> list){
		List<ItemVO> itemVOList = itemVO.getNextItemVOList();
		for(ItemVO item : itemVOList){
			list.add(item);
			if(item.nextItemVOList.size()>0){
				getNextRowItemVO(item,list);
			}
		}
	}


	public int findMaxCol(){
		colMax = 0;
		for(ItemVO itemVO : nextItemVOList){
			if(itemVO.getColEnd() > colMax){
				colMax = itemVO.getColEnd();
			}
		}
		return colMax;
	}
}
