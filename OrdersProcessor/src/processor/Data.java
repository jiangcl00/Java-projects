package processor;

public class Data {
	private double cost;
	private int quantity;

	public Data(double cost, int quantity) {
		this.cost = cost;
		this.quantity = quantity;
	}

	public double getCost() {
		return cost;
	}

	public int getQuantity() {
		return quantity;
	}

	public void increase() {
		this.quantity++;
	}
}
