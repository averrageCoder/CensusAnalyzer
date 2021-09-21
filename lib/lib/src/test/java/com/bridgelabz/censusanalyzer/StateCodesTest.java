package com.bridgelabz.censusanalyzer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

public class StateCodesTest {
	
	private static final String INDIA_STATES_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
	private static final String WRONG_CSV_FILE_PATH = "./src/test/resources/IndiaStateCensusData.csv";
	private static final String INDIAN_CENSUS_EMPTY_FILE = "./src/test/resources/EmptyFile.csv";
	
	@Test
    public void givenIndianStatesCSVFileReturnsCorrectRecords() {
        try {
            StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadStateCode(INDIA_STATES_CSV_FILE_PATH);
            assertEquals(37, numOfRecords);
        } catch (CensusAnalyserException e) {
        }
    }
	
	@Test
    public void givenIndiaStatesData_WithWrongFile_ShouldThrowException() {
        try {
        	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            ExpectedException exceptionRule = ExpectedException.none();
            exceptionRule.expect(CensusAnalyserException.class);
            censusAnalyser.loadStateCode(WRONG_CSV_FILE_PATH);
        } catch (CensusAnalyserException e) {
            assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
        }
    }
	
	@Test
    public void givenEmptyCsvFile_ShouldReturnCustomExceptionType() {
        try {
        	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadIndiaCensusData(INDIAN_CENSUS_EMPTY_FILE);
        } catch (CensusAnalyserException e) {
            assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
        }
    }
	
}
