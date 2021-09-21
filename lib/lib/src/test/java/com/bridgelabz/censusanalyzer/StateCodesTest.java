package com.bridgelabz.censusanalyzer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class StateCodesTest {
	
	private static final String INDIA_STATES_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
	
	@Test
    public void givenIndianStatesCSVFileReturnsCorrectRecords() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadStateCode(INDIA_STATES_CSV_FILE_PATH);
            assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }
	
}
