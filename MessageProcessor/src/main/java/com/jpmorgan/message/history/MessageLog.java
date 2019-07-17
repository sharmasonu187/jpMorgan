package com.jpmorgan.message.history;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.jpmorgan.message.type.Adjustment;
import com.jpmorgan.message.type.Sale;

public class MessageLog {

	private Map<String, Collection<Sale>> salesMap = new HashMap<>();

	public void addSale(Sale sale) {
		Collection<Sale> sales = salesMap.get(sale.getProductType());
		if (sales == null) {
			sales = new ArrayList<>();
			salesMap.put(sale.getProductType(), sales);
		}
		sales.add(sale);
	}

	public Map<String, Collection<Sale>> getSalesMap() {
		return salesMap;
	}

	public void addAdjustment(Adjustment adjustment) {
		Collection<Sale> sales = salesMap.get(adjustment.getProductType());
		if (sales == null) {
			new RuntimeException("Adjustment can not be made without making any sale");
		}
		sales.stream().forEach(sale -> sale.addAdjustment(adjustment));
	}

	public void printSales() {
		salesMap.entrySet().forEach(entry -> {
			double TotalPrice = entry.getValue().stream().map(Sale::getTotalPrice).mapToDouble(Double::valueOf).sum();
			System.out.println("Sales for " + entry.getKey() + ": " + TotalPrice);
		});
	}

	public void printAdjustments() {

		salesMap.entrySet().forEach(entry -> {
			System.out.println("Adjustments for " + entry.getKey());
			entry.getValue().stream().forEach(sale -> {
				System.out.print("Original Price: " + sale.getPrice() + ", Adjusted Price: " + sale.getAdjustedPrice());
				String saleAdjustments = sale.getAdjustments().stream()
						.map(adjustment -> adjustment.getOperation() + ":" + adjustment.getAdjustment())
						.collect(Collectors.joining(",", " (", ")"));
				System.out.println(saleAdjustments);
			});
		});

	}

}
