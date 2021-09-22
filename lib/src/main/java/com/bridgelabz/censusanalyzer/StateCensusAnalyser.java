package com.bridgelabz.censusanalyzer;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StateCensusAnalyser {
	
	List<CSVStateCensus> csvFileList;

    public StateCensusAnalyser() {
        this.csvFileList = new ArrayList<CSVStateCensus>();
    }

	public int loadIndiaCensusData(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICSVBuilder csvBuilder = new OpenCSVBuilder();
            Iterator<CSVStateCensus> csvIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCensus.class);
            while (csvIterator.hasNext()) {
                this.csvFileList.add(csvIterator.next());
            }
            return csvFileList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }
	
	public int loadStateCode(String indiaCensusCSVFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(indiaCensusCSVFilePath))) {
            ICSVBuilder csvBuilder = new OpenCSVBuilder();
            List<CSVStates> statesCSVFileList = csvBuilder.getCSVFileList(reader, CSVStates.class);
            return statesCSVFileList.size();
        } catch (IOException e) {
            throw new CensusAnalyserException(e.getMessage(),
                    CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (CSVBuilderException e) {
            throw new CensusAnalyserException(e.getMessage(), e.type.name());
        }
    }
	
}
