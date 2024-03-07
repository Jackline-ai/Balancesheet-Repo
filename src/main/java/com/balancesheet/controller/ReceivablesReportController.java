package com.balancesheet.controller;

import java.io.FileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.balancesheet.service.BalanceSheetReportService;
import com.balancesheet.model.ApiResponse;
import com.balancesheet.model.BalanceSheetReport;

import net.sf.jasperreports.engine.JRException;
@RestController
@RequestMapping("/api")
public class ReceivablesReportController {
	@Autowired
	BalanceSheetReportService balanceSheetReportService;
	
	@PostMapping("/balancesheet-report")
	public ResponseEntity<ApiResponse> saveEtimsData(@RequestBody BalanceSheetReport balanceSheetReport) {
		ApiResponse response = new ApiResponse("Invoice printed successfully", 200);
		
		try {
			balanceSheetReportService.exportReport("pdf", balanceSheetReport);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.ok(response);
	}
}
