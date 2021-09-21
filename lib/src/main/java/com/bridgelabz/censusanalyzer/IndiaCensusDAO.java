package com.bridgelabz.censusanalyzer;

public class IndiaCensusDAO {
    public String state;
    public int densityPerSqKm;
    public int areaInSqKm;
    public int population;

    public IndiaCensusDAO(CSVStateCensus indiaCensusCSV) {
        densityPerSqKm = indiaCensusCSV.densityPerSqKm;
        areaInSqKm = indiaCensusCSV.areaInSqKm;
        population = indiaCensusCSV.population;
        state = indiaCensusCSV.state;
    }
}