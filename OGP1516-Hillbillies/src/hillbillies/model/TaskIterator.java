package hillbillies.model;

import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

public class TaskIterator implements Iterator<Task> {
	
	private PriorityQueue<Task> queue;
	
	public TaskIterator (Set<Task> set) {
		for (Task task : set) {
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
