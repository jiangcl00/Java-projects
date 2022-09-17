package processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.*;

public class OrdersProcessor {
	private String baseName;
	private StringBuilder[] orderDetail;
	private TreeMap<String, Data> itemList = new TreeMap<String, Data>();

	public OrdersProcessor(String baseName, String itemName, int numOrders) {
		this.baseName = baseName;
		this.orderDetail = new StringBuilder[numOrders + 1];

		try {
			BufferedReader itemFile = new BufferedReader(
					new FileReader(itemName));
			String line;// put all items from the file into the TreeMap itemList
			while ((line = itemFile.readLine()) != null) {
				this.itemList.put(line.replaceAll("\\s+\\d+.*", ""), new Data(
						Double.parseDouble(line.replaceAll("[^\\d+.]", "")),
						0));
			}
			itemFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void processOrder(int orderNum) {
		BufferedReader file = null;
		try {
			file = new BufferedReader(
					new FileReader(baseName + orderNum + ".txt"));
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		String clientId = "";
		try {
			clientId = file.readLine().replaceAll("[^\\d.]", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Reading order for client with id: " + clientId);
		TreeMap<String, Data> clientItem = new TreeMap<String, Data>();
		String line;
		try {
			while ((line = file.readLine()) != null) {
				String itemName = line.replaceAll("\\s+\\d+.*", "");
				Data current = clientItem.get(itemName);
				if (current != null) {
					current.increase();
				} else {
					clientItem.put(itemName,
							new Data(itemList.get(itemName).getCost(), 1));
				}
				synchronized (itemList) {
					itemList.get(itemName).increase();
				}
			}
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		StringBuilder clientOrder = new StringBuilder();
		clientOrder.append("----- Order details for client with Id: " + clientId
				+ " -----" + "\n");

		double total = 0.0;
		while (!clientItem.isEmpty()) {
			Map.Entry<String, Data> curr = clientItem.pollFirstEntry();
			String item = curr.getKey();
			double cost = curr.getValue().getCost();
			int quantity = curr.getValue().getQuantity();
			total += cost * quantity;
			clientOrder.append("Item's name: " + item + ", Cost per item: "
					+ NumberFormat.getCurrencyInstance().format(cost)
					+ ", Quantity: " + quantity + ", Cost: "
					+ NumberFormat.getCurrencyInstance().format(cost * quantity)
					+ "\n");

		}
		clientOrder.append("Order Total: "
				+ NumberFormat.getCurrencyInstance().format(total) + "\n");
		orderDetail[orderNum - 1] = clientOrder;

	}

	public void generateSummary() {
		double total = 0.0;
		StringBuilder summary = new StringBuilder();
		summary.append("***** Summary of all orders *****" + "\n");
		while (!itemList.isEmpty()) {
			Map.Entry<String, Data> curr = itemList.pollFirstEntry();
			String item = curr.getKey();
			double cost = curr.getValue().getCost();
			int quantity = curr.getValue().getQuantity();
			total += cost * quantity;
			summary.append("Summary - Item's name: " + item
					+ ", Cost per item: "
					+ NumberFormat.getCurrencyInstance().format(cost)
					+ ", Number sold: " + quantity + ", Item's Total: "
					+ NumberFormat.getCurrencyInstance().format(cost * quantity)
					+ "\n");
		}
		summary.append("Summary Grand Total: "
				+ NumberFormat.getCurrencyInstance().format(total) + "\n");
		orderDetail[orderDetail.length - 1] = summary;
	}

	public StringBuilder[] getOrderDetail() {
		return orderDetail;
	}

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter item's data file name:");
		String itemName = input.nextLine();

		System.out.println(
				"Enter 'y' for multiple threads, any other character otherwise:");
		String yThread = input.nextLine();

		System.out.println("Enter number of orders to process:");
		int numOrders = input.nextInt();

		System.out.println("Enter order's base filename:");
		input.nextLine();
		String baseName = input.nextLine();

		System.out.println("Enter result's filename:");
		String resultName = input.nextLine();

		input.close();

		OrdersProcessor order = new OrdersProcessor(baseName, itemName,
				numOrders);
		long startTime = System.currentTimeMillis();
		if (yThread.equals("y")) {
			ArrayList<Thread> threadList = new ArrayList<Thread>();
			for (int i = 1; i <= numOrders; i++) {
				Thread thread = new Thread(new ProcessorThread(order, i));
				threadList.add(thread);
				thread.start();
			}
			for (Thread element : threadList) {
				try {
					element.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			order.generateSummary();
		} else {
			for (int i = 1; i <= numOrders; i++) {
				order.processOrder(i);
			}
			order.generateSummary();
		}

		StringBuilder result = new StringBuilder();
		StringBuilder[] resultArray = order.getOrderDetail();
		for (int i = 0; i < resultArray.length; i++) {
			result.append(resultArray[i]);
		}
		try {
			BufferedWriter resultFile = new BufferedWriter(
					new FileWriter(resultName));
			resultFile.write(result.toString());
			resultFile.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Processing time (msec): " + (endTime - startTime));
		System.out.println("Results can be found in the file: " + resultName);
	}
}
