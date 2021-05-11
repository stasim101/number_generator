package com.vmware.poc.classifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

public class RecordRangePartioner implements Partitioner {

	private static final Logger log = LoggerFactory.getLogger(RecordRangePartioner.class);
	private String filepath;

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	
	public String getFilepath() {
		return filepath;
	}

	// gridSize = number of threads
	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {

		int partitionNumber = 0;
		int initialRecord = 1;
		int numberOfRecord = 10000;
		int endRecord = numberOfRecord;

		Map<String, ExecutionContext> result = new HashMap<>();
		File file = new File(filepath);
		Scanner scanner = null;

		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error("FileNotFoundException");
		}

		if (scanner != null) {
			while (scanner.hasNext()) {

				ExecutionContext value = new ExecutionContext();
				result.put("partition" + partitionNumber, value);

				value.put("minValue", initialRecord);
				value.put("maxValue", numberOfRecord);

				initialRecord += numberOfRecord;
				endRecord += numberOfRecord;

				partitionNumber++;
			}
		}

		return result;
	}

}
