package unisa.dse.a2.students;

import java.util.HashMap;
import java.util.Scanner;

import unisa.dse.a2.interfaces.ListGeneric;

public class SecuritiesExchange {

	/**
	 * Exchange name
	 */
	private String name;
	
	public String getName() {
		return name;
	}
	
	/**
	 * List of brokers on this exchange
	 */
	public ListGeneric<StockBroker> brokers;
	
	/**
	 * List of announcements of each trade processed
	 */
	public ListGeneric<String> announcements;
	
	/**
	 * HashMap storing the companies, stored based on their company code as the key
	 */
	public HashMap<String, ListedCompany> companies;

	/**
	 * Initialises the exchange ready to handle brokers, announcements, and companies
	 * @param name
	 */
	public SecuritiesExchange(String name)
	{	// Name null check
		if (name == null) {
			throw new NullPointerException("Security Exchange name cannot be null");
		}
		this.name = name;
		this.brokers = new DSEListGeneric<StockBroker>();
		this.announcements = new DSEListGeneric<String>();
		this.companies = new HashMap<String, ListedCompany>();
	}
	
	/**
	 * Adds the given company to the list of listed companies on the exchange
	 * @param company
	 * @return true if the company was added, false if it was not
	 */
	public boolean addCompany(ListedCompany company)
	{
		if (company == null || companies.containsKey(company.getCode())) {
			return false;
		}
		// adds to companies maps
		companies.put(company.getCode(), company);
		return true;
	}

	/**
	 * Adds the given broke to the list of brokers on the exchange
	 * @param company
	 */
	public boolean addBroker(StockBroker broker)
	{
		// Check nulls and if it already exist
		if (broker == null || brokers.contains(broker)) {
			return false;
		}
		brokers.add(broker);
		return true;
	}
	
	/**
	 * Process the next trade provided by each broker, processing brokers starting from index 0 through to the end
	 * 
	 * If the exchange has three brokers, each with trades in their queue, then three trades will processed, one from each broker.
	 * 
	 * If a broker has no pending trades, that broker is skipped
	 * 
	 * Each processed trade should also make a formal announcement of the trade to the announcements list in the form a string:
	 * "Trade: QUANTITY COMPANY_CODE @ PRICE_BEFORE_TRADE via BROKERNAME", 
	 * e.g. "Trade: 100 DALL @ 99 via Honest Harry Broking" for a sale of 100 DALL shares if they were valued at $99
	 * Price shown should be the price of the trade BEFORE it's processed. Each trade should add its announcement at 
	 * the end of the announcements list
	 * 
	 * @return The number of successful trades completed across all brokers
	 * @throws UntradedCompanyException when traded company is not listed on this exchange
	 */
	public int processTradeRound() throws UntradedCompanyException
	{
		int countProcess = 0;
		
		// Iterate through each broker, one at a time
		for (int i = 0; i < brokers.size(); i ++) {
			StockBroker broker = brokers.get(i);
			
			// Gets the next trade is exist
			Trade trade = broker.getNextTrade();
			
			// Skip broker with no trade and move on
			if (trade == null) {
				continue;
			}
			
			// Find company based of company code
			// Throws exception if it doesn't exist
			String companyCode = trade.getCompanyCode();
			ListedCompany company = companies.get(companyCode);
			
			if (companyCode == null) {
				throw new UntradedCompanyException(companyCode);
			}
			
			// Stores current price in currentPrice
			int CurrentPrice = company.getCurrentPrice();
			
			// Adjust the price based on how many shares bought/sold
			int sharesQuantity = trade.getShareQuantity();
			company.processTrade(sharesQuantity);
			
			// Create a formal announcement of trade
			String announcement = "Trade: " + sharesQuantity + " " + companyCode + " @ " + CurrentPrice + " via " + broker.getName();
			
			// Stores the announcement
			announcements.add(announcement);
			
			// Increment the process or the successfull process
			countProcess ++;
		}
		// Returns the number of process made to caller
		return countProcess;
	}
	
//	public int runCommandLineExchange(Scanner sc)
//	{
//		
//	}
}
