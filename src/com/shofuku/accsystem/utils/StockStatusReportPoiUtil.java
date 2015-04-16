package com.shofuku.accsystem.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFBorderFormatting;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;

import com.shofuku.accsystem.domain.inventory.StockStatusReport;

public class StockStatusReportPoiUtil {

	
	String maxDate;
	String minDate;
	HSSFCellStyle lastItemStyle;
	
	
	public HSSFWorkbook generateSummary(ServletContext servletContext,
			String subModule, List list) throws IOException {

		String filePath = servletContext
				.getRealPath(SASConstants.REPORT_SUMMARY_TEMPLATE_PATH
						+ subModule
						+ SASConstants.REPORT_SUMMARY_TEMPLATE_TYPE);

		FileInputStream fileInputStream = new FileInputStream(filePath);
		POIFSFileSystem fsFileSystem = new POIFSFileSystem(fileInputStream);
		HSSFWorkbook wb = new HSSFWorkbook(fsFileSystem);

		populateSummary(wb, list, subModule);

		return wb;
	}

	private void populateSummary(HSSFWorkbook wb, List list, String subModule) {

		if (subModule.equals("StockStatusReport")) {
			setSummaryHeaders(wb,
					SASConstants.SUMMARY_TEMPLATE_HEADER_STOCK_STATUS_REPORT,
					SASConstants.SUMMARY_TEMPLATE_FIRST_COL);
			populateSummaryForStockStatus(wb, list, subModule);

		} 
	
	}
	
	private void setSummaryHeaders(HSSFWorkbook wb, String headerLabel,
			int columnStart) {
		HSSFSheet sheet = wb.getSheetAt(0);

		HSSFRow rowCompanyHeader = sheet.getRow(0);
		HSSFCell cellCompanyHeader = rowCompanyHeader.getCell(columnStart);
		cellCompanyHeader
				.setCellValue(SASConstants.SUMMARY_TEMPLATE_COMPANY_HEADER);

		HSSFRow rowHeader = sheet.getRow(1);
		HSSFCell cellHeader = rowHeader.getCell(columnStart);
		cellHeader.setCellValue(headerLabel);

		DateFormatHelper dfh = new DateFormatHelper();
		HSSFRow rowDate = sheet.getRow(2);
		HSSFCell cellDate = rowDate.getCell(columnStart);
		if (maxDate != null && !maxDate.equalsIgnoreCase("") && minDate != null
				&& !minDate.equalsIgnoreCase(""))
			cellDate.setCellValue(SASConstants.FROM_PERIOD + minDate
					+ SASConstants.TO + maxDate);
		else
			cellDate.setCellValue(dfh.getDateToday());
	}
	
	
	private void populateSummaryForStockStatus(HSSFWorkbook wb, List list,
			String subModule) {

		HSSFSheet sheet = wb.getSheetAt(0);
		int currentRow = 0;
		lastItemStyle = wb.createCellStyle();
		lastItemStyle.cloneStyleFrom(sheet.getRow(5).getCell(0).getCellStyle());
		lastItemStyle.setBorderBottom(HSSFBorderFormatting.BORDER_DASHED);
		
		try {
			for (int counter = 0; counter < list.size(); counter++) {
				int maxRow = 0;
				HSSFRow row = sheet.getRow(currentRow
						+ SASConstants.SUMMARY_TEMPLATE_ROW_START) == null ? sheet
						.createRow(currentRow
								+ SASConstants.SUMMARY_TEMPLATE_ROW_START)
						: sheet.getRow(currentRow
								+ SASConstants.SUMMARY_TEMPLATE_ROW_START);

				if (subModule.equals("StockStatusReport")) {
					StockStatusReport ssReport = (StockStatusReport) list.get(counter);
					putStockStatusValues(sheet, row, ssReport);
					maxRow += 1;
				}
				currentRow += maxRow;
			}
			
			Set keys = categorySummarySet.keySet();
			Iterator setItr = keys.iterator();
			sheet=wb.getSheetAt(1);
			currentRow = 0;
			while(setItr.hasNext()) {
				String classification = (String)setItr.next();
				int maxRow = 0;
				HSSFRow row = sheet.getRow(currentRow
						+ SASConstants.SUMMARY_TEMPLATE_ROW_START) == null ? sheet
						.createRow(currentRow
								+ SASConstants.SUMMARY_TEMPLATE_ROW_START)
						: sheet.getRow(currentRow
								+ SASConstants.SUMMARY_TEMPLATE_ROW_START);

					putCategorySummaryValues(sheet, row, categorySummarySet.get(classification));
					maxRow += 1;
					currentRow += maxRow;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void putCategorySummaryValues(HSSFSheet sheet, HSSFRow row,
			Category category) {
		int col = SASConstants.SUMMARY_TEMPLATE_FIRST_COL;

		HSSFCell cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);

		cell.setCellValue(parseNullString(category.getCategoryName()));
		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		
		cell.setCellValue(parseNullDouble(category.getUnitCost()));
		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		
		cell.setCellValue(parseNullDouble(category.getTotalReceiptsAmount()));
		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		
		cell.setCellValue(parseNullDouble(category.getTotalReceiptsQty()));
		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		
		cell.setCellValue(parseNullDouble(category.getTotalIssuancesAmount()));
		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		
		cell.setCellValue(parseNullDouble(category.getTotalIssuancesQty()));
	}

	private Map<String,Category> categorySummarySet = new HashMap<String,Category>();
	
	private void putStockStatusValues(HSSFSheet sheet, HSSFRow row,
			StockStatusReport ssReport) {
		int col = SASConstants.SUMMARY_TEMPLATE_FIRST_COL;

		HSSFCell cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);

		cell.setCellValue(parseNullString(ssReport.getItemCode()));
		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		cell.setCellValue(parseNullString(ssReport.getClassification()));
		
		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		cell.setCellValue(parseNullString(ssReport.getSubClassification()));
		
		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		cell.setCellValue(parseNullString(ssReport.getItemDescription()));
		
		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		cell.setCellValue(parseNullString(ssReport.getUnitOfMeasurement()));
		
		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		cell.setCellValue(parseNullDouble(ssReport.getUnitCost()));
		
		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		cell.setCellValue(parseNullDouble(ssReport.getBeginningBalanceQty()));

		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		cell.setCellValue(parseNullDouble(ssReport.getBeginningBalanceAmount()));

		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		cell.setCellValue(parseNullDouble(ssReport.getReceiptsQty()));
		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		cell.setCellValue(parseNullDouble(ssReport.getReceiptsAmount()));

		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		cell.setCellValue(parseNullDouble(ssReport.getIssuancesQuantity()));
		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		cell.setCellValue(parseNullDouble(ssReport.getIssuancesAmount()));

		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		cell.setCellValue(parseNullDouble(ssReport.getEndingBalanceQty()));
		cell = row.getCell(col++, Row.CREATE_NULL_AS_BLANK);
		cell.setCellValue(parseNullDouble(ssReport.getEndingBalanceAmount()));
		
		updateCategorySummary(ssReport);
		}

	private void updateCategorySummary(StockStatusReport ssReport) {

		if(categorySummarySet.get(ssReport.getSubClassification()) ==null){
			Category category = new Category(ssReport.getSubClassification(),ssReport.getUnitCost(),
					ssReport.getReceiptsAmount(),ssReport.getReceiptsQty(),ssReport.getIssuancesAmount(),ssReport.getIssuancesQuantity());
			categorySummarySet.put(ssReport.getSubClassification(), category);
		}else {
			Category category = categorySummarySet.get(ssReport.getSubClassification());
			category.addIssuancesAmount(ssReport.getIssuancesAmount());
			category.addIssuancesQty(ssReport.getIssuancesQuantity());
			category.addReceiptsAmount(ssReport.getReceiptsAmount());
			category.addReceiptsQty(ssReport.getReceiptsQty());
		
			categorySummarySet.put(ssReport.getClassification(), category);
			
		}
		
	}

	private String parseNullString(String value) {
		return null == value ? "" : value;
	}

	private String parseNullDouble(Double value) {
		DecimalFormat df = new DecimalFormat("###,###,###.00");
		return null == value ? "0.0" : df.format(value);
	}
	
	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	public String getMinDate() {
		return minDate;
	}

	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}
	
	protected class Category{
		private String categoryName;
		private double unitCost;
		private double totalReceiptsAmount;
		private double totalReceiptsQty;
		private double totalIssuancesAmount;
		private double totalIssuancesQty;
		
		Category(){
			
		}
	
		public Category(String categoryName, double unitCost,
				double totalReceiptsAmount, double totalReceiptsQty,
				double totalIssuancesAmount, double totalIssuancesQty) {
			this.categoryName = categoryName;
			this.unitCost = unitCost;
			this.totalReceiptsAmount = totalReceiptsAmount;
			this.totalReceiptsQty = totalReceiptsQty;
			this.totalIssuancesAmount = totalIssuancesAmount;
			this.totalIssuancesQty = totalIssuancesQty;
		}
		public void setCategoryName(String categoryName) {this.categoryName = categoryName;}
		public void setUnitCost(double unitCost) {this.unitCost = unitCost;}
		public void setTotalReceiptsAmount(double totalReceiptsAmount) {this.totalReceiptsAmount = totalReceiptsAmount;}
		public void setTotalReceiptsQty(double totalReceiptsQty) {this.totalReceiptsQty = totalReceiptsQty;}
		public void setTotalIssuancesAmount(double totalIssuancesAmount) {this.totalIssuancesAmount = totalIssuancesAmount;}
		public void setTotalIssuancesQty(double totalIssuancesQty) {this.totalIssuancesQty = totalIssuancesQty;}

		public void addReceiptsAmount(double amount) {totalReceiptsAmount = totalReceiptsAmount + amount;}
		public void addReceiptsQty(double amount) {totalReceiptsQty = totalReceiptsQty + amount;}
		public void addIssuancesAmount(double amount) {totalIssuancesAmount = totalIssuancesAmount + amount;}
		public void addIssuancesQty(double amount) {totalIssuancesQty = totalIssuancesQty + amount;}
		
		public String getCategoryName() {return categoryName;	}
		public double getUnitCost() {return unitCost;	}
		public double getTotalReceiptsAmount() {return totalReceiptsAmount;}
		public double getTotalReceiptsQty() {return totalReceiptsQty;}
		public double getTotalIssuancesAmount() {return totalIssuancesAmount;	}
		public double getTotalIssuancesQty() {return totalIssuancesQty;	}
		
		
		
	}
}