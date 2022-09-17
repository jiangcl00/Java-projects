package processor;

public class ProcessorThread implements Runnable{
	private OrdersProcessor order;
	private int numOrders;
	
	public ProcessorThread(OrdersProcessor order, int numOrders) {
		this.order = order;
		this.numOrders = numOrders;
	}
	
	public void run() {
		order.processOrder(numOrders);
	}
}
