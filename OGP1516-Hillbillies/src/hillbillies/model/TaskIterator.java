package hillbillies.model;

import java.util.Iterator;
import java.util.PriorityQueue;

public class TaskIterator implements Iterator<Task> {
	
	private PriorityQueue<Task> queue;
	
	public TaskIterator (PriorityQueue<Task> queue) {
		for (Task task : queue) {
			this.queue.add(task);
		}
	}
	
	@Override
	public boolean hasNext() {
		return !this.queue.isEmpty();
	}

	@Override
	public Task next() {
		return this.queue.poll();
	}

}
