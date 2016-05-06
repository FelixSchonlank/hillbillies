package hillbillies.model;

import java.util.Comparator;

public class Priority implements Comparator<Task> {
	
	@Override
	public int compare(Task first, Task second){
		if(first.getPriority() < second.getPriority()){
			return -1;
		}else if (first.getPriority() > second.getPriority()){
			return 1;
		}else{
			return 0;
		}
	}
}
