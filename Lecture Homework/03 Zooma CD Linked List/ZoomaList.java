
public class ZoomaList extends CDLinkedList {
	int score = 0;

	public ZoomaList() {
		super();
	}

	public ZoomaList(CDLinkedList l) {
		header = l.header;
		size = l.size;
	}

	public void insert(int value, Iterator p) throws Exception {
		//fill code 
		super.insert(value,p);
		
		DListIterator itrleft = (DListIterator) p;
		DListIterator itrright = new DListIterator(itrleft.currentNode.nextNode);
		
		int consecutive=0;
		
		if(itrright.currentNode.data == value) {
			consecutive++;
			while(itrright.next() == value) {
				consecutive++;
			}
		}
		
		if(itrleft.previous() == value) { //previous() returns current value before iterator moves
			consecutive++;
			while (itrleft.previous() == value) {
				consecutive++;
			}
		}
		itrleft.next(); // although if condition not met, ITRLEFT already shifted back
		
		if(consecutive >= 3) {
			removeBetween(itrleft,itrright,consecutive);
			score += consecutive;
		} else {
			return;
		}
		
		//CHECK COLLISION
		while(itrleft.currentNode.data == itrright.currentNode.data) {

			value = itrleft.currentNode.data;
			consecutive = 1; // only count for ITRRIGHT
			
			while(itrright.next() == value) {
				consecutive++;
			}
			
			while(itrleft.previous() == value) {
				consecutive++;
			}
			itrleft.next();
			
			if(consecutive >=3) {
				removeBetween(itrleft,itrright,consecutive);
				score += consecutive;
				
			}
			
		}

	}
		

	public void removeBetween(DListIterator left, DListIterator right, int inc) {
		//fill code
		
		if(left.currentNode == null || right.currentNode == null || left.currentNode == right.currentNode || left.currentNode.nextNode == right.currentNode ) {
			return;
		}
		
		left.currentNode.nextNode = right.currentNode;
		right.currentNode.previousNode = left.currentNode;
		size -= inc;
	}

}