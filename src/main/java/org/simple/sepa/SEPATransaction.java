package org.simple.sepa;

import java.math.BigDecimal;
import java.util.Date;

public class SEPATransaction {

	public enum Currency {
		BGN, HRK, CZK, DKK,
		EUR, GIP, HUF, ISK,
		NOK, PLN, RON, SEK,
		CHF, GBP
	}

	private SEPABankAccount bankAccount;

	private String id;
	private String subject;
	private BigDecimal value;
	private Date date;
	private String endToEndId;

	private String mandatReference;
	private Date mandatReferenceDate;

	private Currency currency = Currency.EUR;
	private String remittance;

	public SEPATransaction(
		SEPABankAccount bankAccount,
		BigDecimal value,
		String subject,
		Date date,
		String mandatReference,
		Date mandatReferenceDate,
		Currency currency,
		String remittance
	) {
		this.bankAccount = bankAccount;
		this.subject = subject;
		this.value = value;
		this.date = date;
		this.mandatReference = mandatReference;
		this.mandatReferenceDate = mandatReferenceDate;
		this.currency = currency;
		this.remittance = remittance;
	}

	public SEPATransaction(
			SEPABankAccount bankAccount,
			BigDecimal value,
			String subject,
			Date date,
			String mandatReference,
			Date mandatReferenceDate
	) {
		this(bankAccount, value, subject, date, mandatReference, mandatReferenceDate, Currency.EUR, null);
	}

	public SEPATransaction(SEPABankAccount bankAccount, BigDecimal value, String subject) {
		this(bankAccount, value, subject, new Date(), null, null, Currency.EUR, null);
	}

	public SEPATransaction(SEPABankAccount bankAccount, BigDecimal value, String subject, Currency currency) {
		this(bankAccount, value, subject, new Date(), null, null, currency, null);
	}

	public String getId() {
		return id;
	}

	public SEPATransaction setId(String id) {
		this.id = id;
		return this;
	}

	public String getEndToEndId() {
		return endToEndId;
	}

	public SEPATransaction setEndToEndId(String endToEndId) {
		this.endToEndId = endToEndId;
		return this;
	}

	public SEPABankAccount getBankAccount() {
		return bankAccount;
	}

	public BigDecimal getValue() {
		return value;
	}

	public String getSubject() {
		return subject;
	}

	public String getMandatReference() {
		return mandatReference;
	}
	
	public Date getMandatReferenceDate() {
		return mandatReferenceDate;
	}

	public Date getDate() {
		return date;
	}

	public Currency getCurrency() {
		return currency;
	}

	public String getRemittance() { return remittance; }
}
