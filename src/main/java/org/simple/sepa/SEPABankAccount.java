package org.simple.sepa;

import org.simple.sepa.format.SEPAFormatFilter;

public class SEPABankAccount {

	private String IBAN;
	private String BIC;
	private String Name;

	public String getIBAN() {
		return SEPAFormatFilter.filter(IBAN);
	}
	
	public String getBIC() {
		return SEPAFormatFilter.filterBIC(BIC);
	}

	public String getName() {
		return Name;
	}

	public SEPABankAccount(String iBAN, String bIC, String name) {
		this.IBAN = iBAN;
		this.BIC = bIC;
		this.Name = name;
	}
}
