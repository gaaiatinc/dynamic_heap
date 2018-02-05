/**
 * This module includes a java implementation of a dynamic heap.  With a dynamic heap,
 * the value of an element in the heap can be changed, and the position of the respective 
 * element in the dynamic heap will be automatically adjusted to maintain the heap property.
 * 
 * <p> The implementation provides two constructors. One constructor 
 * {@link com.gaaiat.ml.dynamicheap.DynamicHeap#DynamicHeap(ArrayList, Comparator) DynamicHeap(ArrayList&lt;T extends DynamicHeap.Node&gt;, Comparator&lt;T extends DynamicHeap.Node&gt;)} 
 * takes a ArrayList of Nodes,  
 * and builds a heap in <strong>O(N)</strong> time complexity using the ArrayList without copying it.  This constructor is
 * provided for applications where space/memory complexity requires an in-place heap.    
 * The resulting DynamicHeap will utilize the ArrayList passed to the constructor, and the heap 
 * property and function are not guaranteed if the ArrayList is modified externally.
 * 
 * <p>
 * The second constructor 
 * {@link com.gaaiat.ml.dynamicheap.DynamicHeap#DynamicHeap(Collection, Comparator) DynamicHeap(Collection&lt;T extends DynamicHeap.Node&gt;, Comparator&lt;T extends DynamicHeap.Node&gt;)}
 * takes a Collection of Nodes,  
 * and builds a heap in <strong>O(N)</strong> time complexity using a copy of the Collection.  This constructor is
 * provided for multi-threaded applications where maintaining the heap property and function are critical, 
 * and memory complexity of <strong>O(N)</strong> can be tolerated.    
 * The resulting DynamicHeap will utilize its own shallow copy of the Collection passed to the constructor, 
 * and the heap property and function are guaranteed since external code can't access the copied collection.
 * 
 * <p> Both of the constructors described above require an instance of a comparator
 * {@link java.util.Comparator Comparator&lt;T extends DynamicHeap.Node&gt;}
 * which is used to define the order of the heap (max heap or min heap).  The implementation of the comparator 
 * must satisfy the condition and requirements described in the documentation of the <code>java.util.comparator</code>.
 *   
 * <p> Nodes passed to the constructor, and ones added later, can be updated (their ordering values changed), 
 * and the the heap will dynamically re-order the nodes when the method  <code>updateNode(T aNode)</code> is called. 
 * 
 * 
 *  
 */
package com.gaaiat.ml.dynamicheap;

