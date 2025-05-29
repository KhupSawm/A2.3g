package unisa.dse.a2.students;

public class UntradedCompanyException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8308062303527910238L; // Used Eclips to generate serial No.

	public UntradedCompanyException(String companyCode)
	{
		super(companyCode + " is not a listed company on this exchange");
	}
}
