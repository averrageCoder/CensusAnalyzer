package com.bridgelabz.censusanalyzer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

public class StateCodesTest {
	
	private static final String INDIA_STATES_CSV_FILE_PATH = "./src/test/resources/IndiaStateCode.csv";
	private static final String WRONG_CSV_FILE_PATH = "./src/main/resources/IndiaStateCensusData.csv";
	private static final String INDIAN_CENSUS_EMPTY_FILE = "./src/test/resources/EmptyFile.csv";
	private static final String INDIAN_STATES_CSV_WRONG_DELIMITER = "./src/test/resources/IndiaStateCode_WrongDelimiter.csv";
	
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
            assertEquals(true,false);
        } catch (CensusAnalyserException e) {
            assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, e.type);
        }
    }
	
	@Test
    public void givenEmptyCsvFile_ShouldReturnCustomExceptionType() {
        try {
        	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadStateCode(INDIAN_CENSUS_EMPTY_FILE);
            assertEquals(true,false);
        } catch (CensusAnalyserException e) {
            assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
        }
    }
	
	@Test
    public void givenWrongDelimiter_InIndiaCensusData_ShouldReturnCustomExceptionType() {
        try {
        	StateCensusAnalyser censusAnalyser = new StateCensusAnalyser();
            int numOfRecords = censusAnalyser.loadStateCode(INDIAN_STATES_CSV_WRONG_DELIMITER);
            assertEquals(true,false);
        } catch (CensusAnalyserException e) {
            assertEquals(CensusAnalyserException.ExceptionType.CSV_FILE_INTERNAL_ISSUES, e.type);
        }
    }
	
}
