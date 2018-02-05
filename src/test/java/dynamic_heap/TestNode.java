/**
 * 
 */
package dynamic_heap;

import com.gaaiat.ml.dynamicheap.DynamicHeap.Node;

/**
 * @author Ali Ismael
 *
 */
public class TestNode extends Node {

	public static class NodeComparator implements java.util.Comparator<TestNode> {
		@Override
		public int compare(TestNode o1, TestNode o2) {
			if((o1 == null) || (o2 == null)) {
				return -1;
			}
			return (int) Math.signum(o1.value - o2.value);
		}
	}

	double value;
	/**
	 * @param heapIndex
	 */
	public TestNode(double value) {
		super();
		this.value = value;
	}	
	
	public void printNode( ) {
		System.out.println(" node value: " + value);
	}
}
