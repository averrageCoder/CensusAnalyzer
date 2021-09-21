package com.bridgelabz.censusanalyzer;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder implements ICSVBuilder{
   public  <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass) throws CSVBuilderException {
	   try {
		   return this.getCSVBean(reader,csvClass).iterator();
	   }
	   catch (RuntimeException e) {
		   throw new CSVBuilderException(e.getMessage(), CSVBuilderException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
	   }
    }

    @Override
    public <E> List<E> getCSVFileList(Reader reader, Class<E> csvClass) throws CSVBuilderException {
    	try {
    		return this.getCSVBean(reader,csvClass).parse();
    	}
    	catch (RuntimeException e) {
    		throw new CSVBuilderException(e.getMessage(), CSVBuilderException.ExceptionType.CSV_FILE_INTERNAL_ISSUES);
    	}

    }

    private <E> CsvToBean<E> getCSVBean(Reader reader, Class<E> csvClass) throws CSVBuilderException {
        try {
            CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<>(reader);
            csvToBeanBuilder.withType(csvClass);
            csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
            return csvToBeanBuilder.build();
        } catch (IllegalStateException e) {
            throw new CSVBuilderException(e.getMessage(), CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}