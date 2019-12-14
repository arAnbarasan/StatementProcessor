package com.rabo.dto;

public class CustomerStatementDTO {

	private String Reference, AccountNumber, Description, StartBalance, Mutation, EndBalance;

	public String getReference() {
		return Reference;
	}

	public void setReference(String reference) {
		this.Reference = reference;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Reference == null) ? 0 : Reference.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerStatementDTO other = (CustomerStatementDTO) obj;
		if (Reference == null) {
			if (other.Reference != null)
				return false;
		} else if (!Reference.equals(other.Reference))
			return false;
		return true;
	}

	public String getAccountNumber() {
		return AccountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.AccountNumber = accountNumber;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		this.Description = description;
	}

	public String getStartBalance() {
		return StartBalance;
	}

	public void setStartBalance(String startBalance) {
		this.StartBalance = startBalance;
	}

	public String getMutation() {
		return Mutation;
	}

	public void setMutation(String mutation) {
		this.Mutation = mutation;
	}

	public String getEndBalance() {
		return EndBalance;
	}

	public void setEndBalance(String endBalance) {
		this.EndBalance = endBalance;
	}

	@Override
	public String toString() {
		return "CustomerStatementDTO [Reference=" + Reference + ", AccountNumber=" + AccountNumber + ", Description="
				+ Description + ", StartBalance=" + StartBalance + ", Mutation=" + Mutation + ", EndBalance="
				+ EndBalance + "]";
	}
	
	
	
}
