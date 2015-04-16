package com.Junit.tests;

import java.sql.Timestamp;

import org.junit.Test;

import com.shofuku.accsystem.controllers.DisbursementManager;
import com.shofuku.accsystem.domain.disbursements.CashPayment;
import com.shofuku.accsystem.domain.disbursements.CheckPayments;
import com.shofuku.accsystem.domain.disbursements.PettyCash;
import com.shofuku.accsystem.utils.DateFormatHelper;

public class DisbursementTest {

	private DisbursementManager dm = new DisbursementManager();

	@Test
	public void insertSupplier() {
		PettyCash pettyCash = new PettyCash();
		CashPayment cashPayment = new CashPayment();
		CheckPayments checkPayments = new CheckPayments();
		DateFormatHelper dfh = new DateFormatHelper();

		String checkVoucherNumber = "Test011";
		Timestamp checkVoucherDate = dfh.parseStringToTimestamp("1986-04-30");
		String payee = "Test011";
		String description = "Test011";
		String particulars = "Test011";
		double amount = 1.111;
		String checkNo = "Test011";
		String bankName = "Test011";
		String bankAccountNumber = "Test011";
		String releasedBy = "Test011";
		String approvedBy = "Test011";
		String debitTitle = "Test011";
		double debitAmount = 1.111;
		String creditTitle = "Test011";
		double creditAmount = 1.111;

		checkPayments.setCheckVoucherNumber(checkVoucherNumber);
		checkPayments.setCheckVoucherDate(checkVoucherDate);
		checkPayments.setPayee(payee);
		checkPayments.setDescription(description);
		checkPayments.setParticulars(particulars);
		checkPayments.setAmount(amount);
		checkPayments.setCheckNo(checkNo);
		checkPayments.setBankName(bankName);
		checkPayments.setBankAccountNumber(bankAccountNumber);
		checkPayments.setReleasedBy(releasedBy);
		checkPayments.setApprovedBy(approvedBy);
		checkPayments.setDebitTitle(debitTitle);
		checkPayments.setDebitAmount(debitAmount);
		checkPayments.setCreditTitle(creditTitle);
		checkPayments.setCreditAmount(creditAmount);

		cashPayment.setCashVoucherNumber(checkVoucherNumber);
		cashPayment.setCashVoucherDate(checkVoucherDate);
		cashPayment.setPayee(payee);
		cashPayment.setDescription(description);
		cashPayment.setParticulars(particulars);
		cashPayment.setAmount(amount);
		cashPayment.setReleasedBy(releasedBy);
		cashPayment.setApprovedBy(approvedBy);
		cashPayment.setDebitTitle(debitTitle);
		cashPayment.setDebitAmount(debitAmount);
		cashPayment.setCreditTitle(creditTitle);
		cashPayment.setCreditAmount(creditAmount);

		pettyCash.setPcVoucherNumber(checkVoucherNumber);
		pettyCash.setPcVoucherDate(checkVoucherDate);
		pettyCash.setPayee(payee);
		pettyCash.setDescription(description);
		pettyCash.setParticulars(particulars);
		pettyCash.setAmount(amount);
		pettyCash.setReleasedBy(releasedBy);
		pettyCash.setApprovedBy(approvedBy);
		pettyCash.setDebitTitle(debitTitle);
		pettyCash.setDebitAmount(debitAmount);
		pettyCash.setCreditTitle(creditTitle);
		pettyCash.setCreditAmount(creditAmount);

		
	}

}
