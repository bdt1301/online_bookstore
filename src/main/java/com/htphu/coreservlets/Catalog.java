package com.htphu.coreservlets;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class Catalog {
	private static CatalogItem[] items;

	private static void loadCsvFromURL(String urlString) {
	    List<String[]> records = new ArrayList<>();
	    try {
	        URL url = new URL(urlString);
	        InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8");
	        CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
	        records = csvReader.readAll();
	        csvReader.close();
	    } catch (IOException | CsvException e) {
	        e.printStackTrace();
	    }
	    items = new CatalogItem[records.size()];
	    int index = 0;
	    for (String[] record : records) {
	        String itemID = record[0];
	        String shortDescription = record[1];
	        String longDescription = record[2];
	        String image = record[3];
	        double cost = Double.parseDouble(record[4]);
	        items[index++] = new CatalogItem(itemID, shortDescription, longDescription, image, cost);
	    }
	}

	static {
		loadCsvFromURL("http://localhost:8080/OnlineBookstore/data/books.csv");
	}

	public static CatalogItem getItem(String itemID) {
		if (itemID == null || items == null) {
			return null;
		}
		for (CatalogItem item : items) {
			if (itemID.equals(item.getItemID())) {
				return item;
			}
		}
		return null;
	}
}
