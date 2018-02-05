/**
 * This is an implementation of a heap with dynamic node value update capability.
 */
package com.gaaiat.ml.dynamicheap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/**
 * 
 * @author Ali Ismael
 *
 * @param <T>
 *            A type which must extend the static inner class DynamicHeap.Node
 */
public class DynamicHeap<T extends DynamicHeap.Node> {

	/**
	 * 
	 * @author Ali Ismael
	 *
	 */
	public static class Node {
		int heapIndex = 0;

		public Node() {
			this.heapIndex = 0;
		}
	}

	private int heapSize = 0;
	private ArrayList<T> heapElements;
	private Comparator<T> comparator;

	/**
	 * A constructor which will shallow-copy the collection of initial elements.
	 * 
	 * @param initialHeapElements
	 *            the initial collection of node(s) from which the heap will be
	 *            initially built.
	 * @param comp
	 *            an instance of Comparator which defines whether this heap is a max
	 *            or min heap.
	 */
	public DynamicHeap(Collection<T> initialHeapElements, Comparator<T> comp) {
		this(new ArrayList<T>(initialHeapElements), comp);
	}

	/**
	 * A constructor which will use the same ArrayList without copying.
	 * 
	 * This constructor is provided for applications where space complexity is
	 * important. The heap properties will not be guaranteed if the content of the
	 * ArrayList is modified externally.
	 * 
	 * @param initialHeapElements
	 *            the initial ArrayList of node(s) from which the heap will be
	 *            initially built.
	 * @param comp
	 *            an instance of Comparator which defines whether this heap is a max
	 *            or min heap.
	 */
	public DynamicHeap(ArrayList<T> initialHeapElements, Comparator<T> comp) {
		this.heapElements = initialHeapElements;
		this.heapSize = heapElements.size();
		this.comparator = comp;

		buildHeap();
	}

	/**
	 * This is an implementation of O(N) algorithm to build the initial heap.
	 */
	private void buildHeap() {
		for (int idx = 0; idx < heapSize; idx++) {
			heapElements.get(idx).heapIndex = idx;
		}

		for (int idx = ((heapSize - 1) / 2); idx >= 0; idx--) {
			heapifyAt(heapElements.get(idx));
		}
	}

	/**
	 * 
	 * @param aNode
	 *            a Node instance for which to get the parent
	 * @return The parent of a aNode, or null if there is no parent (aNode is at the
	 *         top of the heap).
	 */
	private T parentOf(T aNode) {
		if (heapSize <= 1) {
			return null;
		}

		return heapElements.get((aNode.heapIndex - 1) / 2);
	}

	/**
	 * 
	 * @param aNode
	 *            a Node instance for which to get the left child.
	 * @return the left child of aNode if exists, otherwise null.
	 */
	private T leftChildOf(T aNode) {
		int tempIdx = aNode.heapIndex * 2 + 1;
		if (tempIdx >= heapSize) {
			return null;
		}

		return heapElements.get(tempIdx);
	}

	/**
	 * 
	 * @param aNode
	 *            The node for which to get the right child.
	 * @return The right child if exists, otherwise null.
	 */
	private T rightChildOf(T aNode) {
		int tempIdx = aNode.heapIndex * 2 + 2;
		if (tempIdx >= heapSize) {
			return null;
		}

		return heapElements.get(tempIdx);
	}

	/**
	 * 
	 * @param aNode
	 *            The node at which to start re-building the heap.
	 * 
	 *            The heap property will be checked and corrected for aNode and all
	 *            Nodes below it.
	 * 
	 */
	private void heapifyAt(T aNode) {
		T swapElement = aNode;

		T leftChild = leftChildOf(aNode);
		T rightChild = rightChildOf(aNode);

		if ((leftChild != null) && (comparator.compare(swapElement, leftChild) > 0)) {
			swapElement = leftChild;
		}
		if ((rightChild != null) && (comparator.compare(swapElement, rightChild) > 0)) {
			swapElement = rightChild;
		}

		if (swapElement != aNode) {
			int swapElementIdx = swapElement.heapIndex;
			int initialNodeIdx = aNode.heapIndex;
			swapElement.heapIndex = initialNodeIdx;
			aNode.heapIndex = swapElementIdx;

			heapElements.set(swapElementIdx, aNode);
			heapElements.set(initialNodeIdx, swapElement);
			heapifyAt(heapElements.get(swapElementIdx));
		}
	}

	/**
	 * 
	 * @param aNode
	 *            The heap property will be checked and corrected starting at aNode
	 *            and all of its parents.
	 */
	private void bubbleUpAt(T aNode) {
		T parent = parentOf(aNode);

		if (parent != null) {
			if (comparator.compare(parent, aNode) > 0) {
				int nodeIdx = aNode.heapIndex;
				int parentIdx = parent.heapIndex;

				parent.heapIndex = nodeIdx;
				aNode.heapIndex = parentIdx;

				heapElements.set(nodeIdx, parent);
				heapElements.set(parentIdx, aNode);

				bubbleUpAt(heapElements.get(parentIdx));
			}
		}
	}

	/**
	 * 
	 * @return The current size of the heap.
	 */
	public final synchronized int size() {
		return heapSize;
	}

	/**
	 * 
	 * @return Will remove and return the top element in the heap.
	 * 
	 */
	public final synchronized T pull() {
		if (heapSize == 0) {
			return null;
		}

		T retVal = heapElements.get(0);
		T rootVal = heapElements.get(heapSize - 1);
		rootVal.heapIndex = 0;

		heapElements.set(0, rootVal);
		heapSize -= 1;
		heapifyAt(heapElements.get(0));

		return retVal;
	}

	/**
	 * 
	 * @param aNode
	 *            Adds aNode to the heap and maintains the heap property.
	 */
	public synchronized void add(T aNode) {
		aNode.heapIndex = heapSize;
		heapSize++;
		heapElements.add(aNode);
		bubbleUpAt(aNode);
	}

	/**
	 * 
	 * @param aNode
	 *            Will recheck and correct the heap property starting at aNode and
	 *            all of its parents and children.
	 */
	public synchronized void updateNode(T aNode) {
		int initialNodeIdx = aNode.heapIndex;
		if (heapElements.get(initialNodeIdx) != aNode) {
			return;
		}

		bubbleUpAt(aNode);
		if (aNode.heapIndex == initialNodeIdx) {
			heapifyAt(aNode);
		}
	}
}
