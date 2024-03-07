package com.balancesheet.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.balancesheet.model.BalanceSheetReport;
import  com.balancesheet.utils.Utilities;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
@Service
public class  BalanceSheetReportService{
    	
      
	public String exportReport(String reportFormat,BalanceSheetReport balanceSheetReport) throws FileNotFoundException, JRException {

		String path = "/home/jackline/Desktop/Reports/";

		// Load the report template

		List<BalanceSheetReport> balanceSheetReports = new ArrayList<BalanceSheetReport>();
		balanceSheetReports.add(balanceSheetReport);

		File file = ResourceUtils.getFile("classpath:Balansheet.jrxml");
		JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
		JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(balanceSheetReports);
		Map<String, Object> parameters = new HashMap<>();
		
		JRBeanCollectionDataSource dataSourceHeader = new JRBeanCollectionDataSource(balanceSheetReports);
		parameters.put("HEADER_DATASOURCE", dataSourceHeader);
		
		JRBeanCollectionDataSource dataSourceAssets = new JRBeanCollectionDataSource(balanceSheetReports);
		parameters.put("BALANCESHEET_ASSETS_DATASOURCE", dataSourceAssets);
		
		JRBeanCollectionDataSource dataSourceLiabilities = new JRBeanCollectionDataSource(balanceSheetReports);
		parameters.put("BALANCESHEET_LIABILITIES_DATASOURCE", dataSourceLiabilities);
		
		JRBeanCollectionDataSource dataSourceEquity = new JRBeanCollectionDataSource(balanceSheetReports);
		parameters.put("BALANCESHEET_EQUITY_DATASOURCE", dataSourceEquity);
		
		parameters.put("createdBy", "Jackline");
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
		if (reportFormat.equalsIgnoreCase("xml")) {
			return JasperExportManager.exportReportToXml(jasperPrint);

		}

		else if (reportFormat.equalsIgnoreCase("pdf")) {
			JasperExportManager.exportReportToPdfFile(jasperPrint, path + Utilities.getCurrentDateTime());

		}

		return "report generated in path: " + path;
	}


}


