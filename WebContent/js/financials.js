function showRequiredFieldst(reportType){
	/*LEGEND:
	 * 	01-	Ledger account
		02-	Trial balance
		03-	Income Statement
		04-	Balance Sheet
		05-	Statement of Cost of Goods Manufactured
		06-	Statement of Cost of Goods Sold.
		07-	Statement of Changes in Shareholders' Equity
		08-	Journal Entries
		09-	VAT Report
		10-	Check Encashment
	 * */
	
	var generalReportParameters = document.getElementById("generalReportParameters");
	var ledgerAccountReportParameters = document.getElementById("ledgerAccountReportParameters");
	var trialBalanceReportParameters = document.getElementById("trialBalanceReportParameters");
	var incomeStatementReportParameters = document.getElementById("incomeStatementReportParameters");
	var balanceSheetReportParameters = document.getElementById("balanceSheetReportParameters");
	var statementOfCostOfGoodsMfgReportParameters = document.getElementById("statementOfCostOfGoodsMfgReportParameters");
	var statementOfCostOfGoodsSold = document.getElementById("statementOfCostOfGoodsSold");
	var statementOfChangesInShareholdersEquityReportParameters = document.getElementById("statementOfChangesInShareholdersEquityReportParameters");
	var journalEntriesReportParameters = document.getElementById("journalEntriesReportParameters");
	var vatReportReportParameters = document.getElementById("vatReportReportParameters");
	var checkEncashmentReportParameters = document.getElementById("checkEncashmentReportParameters");
	

	if(reportType.value =='01'){
		generalReportParameters.style.display  = 'block';
		ledgerAccountReportParameters.style.display  = 'block';
		trialBalanceReportParameters.style.display= 'none';
		incomeStatementReportParameters.style.display= 'none';
		balanceSheetReportParameters.style.display= 'none';
		statementOfCostOfGoodsMfgReportParameters.style.display= 'none';
		statementOfCostOfGoodsSold.style.display= 'none';
		statementOfChangesInShareholdersEquityReportParameters.style.display= 'none';
		journalEntriesReportParameters.style.display= 'none';
		vatReportReportParameters.style.display= 'none';
		checkEncashmentReportParameters.style.display= 'none';
		
	}else if(reportType.value =='02'){
		generalReportParameters.style.display  = 'block';
		ledgerAccountReportParameters.style.display  = 'none';
		trialBalanceReportParameters.style.display= 'none';
		incomeStatementReportParameters.style.display= 'none';
		balanceSheetReportParameters.style.display= 'none';
		statementOfCostOfGoodsMfgReportParameters.style.display= 'none';
		statementOfCostOfGoodsSold.style.display= 'none';
		statementOfChangesInShareholdersEquityReportParameters.style.display= 'none';
		journalEntriesReportParameters.style.display= 'none';
		vatReportReportParameters.style.display= 'block';
		checkEncashmentReportParameters.style.display= 'none';
		
	}else if(reportType.value =='03'){
		generalReportParameters.style.display  = 'block';
		ledgerAccountReportParameters.style.display  = 'none';
		trialBalanceReportParameters.style.display= 'none';
		incomeStatementReportParameters.style.display= 'block';
		balanceSheetReportParameters.style.display= 'none';
		statementOfCostOfGoodsMfgReportParameters.style.display= 'none';
		statementOfCostOfGoodsSold.style.display= 'none';
		statementOfChangesInShareholdersEquityReportParameters.style.display= 'none';
		journalEntriesReportParameters.style.display= 'none';
		vatReportReportParameters.style.display= 'none';
		checkEncashmentReportParameters.style.display= 'none';
		
	}else if(reportType.value =='04'){
		generalReportParameters.style.display  = 'block';
		ledgerAccountReportParameters.style.display  = 'none';
		trialBalanceReportParameters.style.display= 'none';
		incomeStatementReportParameters.style.display= 'none';
		balanceSheetReportParameters.style.display= 'block';
		statementOfCostOfGoodsMfgReportParameters.style.display= 'none';
		statementOfCostOfGoodsSold.style.display= 'none';
		statementOfChangesInShareholdersEquityReportParameters.style.display= 'none';
		journalEntriesReportParameters.style.display= 'none';
		vatReportReportParameters.style.display= 'none';
		checkEncashmentReportParameters.style.display= 'none';
		
	}else if(reportType.value =='05'){
		generalReportParameters.style.display  = 'block';
		ledgerAccountReportParameters.style.display  = 'none';
		trialBalanceReportParameters.style.display= 'none';
		incomeStatementReportParameters.style.display= 'none';
		balanceSheetReportParameters.style.display= 'none';
		statementOfCostOfGoodsMfgReportParameters.style.display= 'block';
		statementOfCostOfGoodsSold.style.display= 'none';
		statementOfChangesInShareholdersEquityReportParameters.style.display= 'none';
		journalEntriesReportParameters.style.display= 'none';
		vatReportReportParameters.style.display= 'none';
		checkEncashmentReportParameters.style.display= 'none';
		
	}else if(reportType.value =='06'){
		generalReportParameters.style.display  = 'block';
		ledgerAccountReportParameters.style.display  = 'none';
		trialBalanceReportParameters.style.display= 'none';
		incomeStatementReportParameters.style.display= 'none';
		balanceSheetReportParameters.style.display= 'none';
		statementOfCostOfGoodsMfgReportParameters.style.display= 'none';
		statementOfCostOfGoodsSold.style.display= 'block';
		statementOfChangesInShareholdersEquityReportParameters.style.display= 'none';
		journalEntriesReportParameters.style.display= 'none';
		vatReportReportParameters.style.display= 'none';
		checkEncashmentReportParameters.style.display= 'none';
		
	}else if(reportType.value =='07'){
		generalReportParameters.style.display  = 'block';
		ledgerAccountReportParameters.style.display  = 'none';
		trialBalanceReportParameters.style.display= 'none';
		incomeStatementReportParameters.style.display= 'none';
		balanceSheetReportParameters.style.display= 'none';
		statementOfCostOfGoodsMfgReportParameters.style.display= 'none';
		statementOfCostOfGoodsSold.style.display= 'none';
		statementOfChangesInShareholdersEquityReportParameters.style.display= 'block';
		journalEntriesReportParameters.style.display= 'none';
		vatReportReportParameters.style.display= 'none';
		checkEncashmentReportParameters.style.display= 'none';
		
	}else if(reportType.value =='08'){
		generalReportParameters.style.display  = 'block';
		ledgerAccountReportParameters.style.display  = 'none';
		trialBalanceReportParameters.style.display= 'none';
		incomeStatementReportParameters.style.display= 'none';
		balanceSheetReportParameters.style.display= 'none';
		statementOfCostOfGoodsMfgReportParameters.style.display= 'none';
		statementOfCostOfGoodsSold.style.display= 'none';
		statementOfChangesInShareholdersEquityReportParameters.style.display= 'none';
		journalEntriesReportParameters.style.display= 'block';
		vatReportReportParameters.style.display= 'none';
		checkEncashmentReportParameters.style.display= 'none';
		
	}else if(reportType.value =='09'){
		generalReportParameters.style.display  = 'block';
		ledgerAccountReportParameters.style.display  = 'none';
		trialBalanceReportParameters.style.display= 'none';
		incomeStatementReportParameters.style.display= 'none';
		balanceSheetReportParameters.style.display= 'none';
		statementOfCostOfGoodsMfgReportParameters.style.display= 'none';
		statementOfCostOfGoodsSold.style.display= 'none';
		statementOfChangesInShareholdersEquityReportParameters.style.display= 'none';
		journalEntriesReportParameters.style.display= 'none';
		vatReportReportParameters.style.display= 'block';
		checkEncashmentReportParameters.style.display= 'none';
		
	}else if(reportType.value =='10'){
	}else{
		//selected none
	}
}
function showLedgerParams(ledgerType){
	var ledgerSupplierListsDiv = document.getElementById("ledgerSupplierListsDiv");
	var ledgerCustomersListsDiv = document.getElementById("ledgerCustomersListsDiv");
	if(ledgerType.value =='supplier'){
		ledgerSupplierListsDiv.style.display  = 'block';
		ledgerCustomersListsDiv.style.display  = 'none';
	}else if(ledgerType.value =='customer'){
		ledgerSupplierListsDiv.style.display  = 'none';
		ledgerCustomersListsDiv.style.display  = 'block';
	}else{
		ledgerSupplierListsDiv.style.display  = 'none';
		ledgerCustomersListsDiv.style.display  = 'none';
	}
	
}