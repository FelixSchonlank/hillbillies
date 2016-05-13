package hillbillies.model;

import java.util.Comparator;

public class TaskPriorityComparator implements Comparator<Task> {

	@Override
	public int compare(Task t1, Task t2) {
		return (t1.getPriority() < t2.getPriority()) ? (1) : (t1.getPriority() == t2.getPriority() ? 0 : -1);
	}

}
