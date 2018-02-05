## Description
This module includes a java implementation of a dynamic heap.  With a dynamic heap,
the ordering value of an element in the heap can be changed, and the position of the respective 
element in the dynamic heap will be automatically adjusted to maintain the heap property.

## Constructors 
The implementation provides two constructors:

1. One constructor
<code>DynamicHeap(ArrayList<T extends DynamicHeap.Node>, Comparator<T extends DynamicHeap.Node>)</code> 
takes a ArrayList of Nodes, and builds a heap in <strong>O(N)</strong> time complexity using the ArrayList without copying it.  This constructor is provided for applications where space/memory complexity requires an in-place heap. The resulting DynamicHeap will utilize the ArrayList passed to the constructor, and the heap property and function are not guaranteed if the ArrayList is modified externally.

2. The second constructor 
<code>DynamicHeap(Collection<T extends DynamicHeap.Node>, Comparator<T extends DynamicHeap.Node>)</code>
takes a Collection of Nodes, and builds a heap in <strong>O(N)</strong> time complexity using a copy of the Collection.  This constructor is provided for multi-threaded applications where maintaining the heap property and function are critical, and memory complexity of <strong>O(N)</strong> can be tolerated. The resulting DynamicHeap will utilize its own shallow copy of the Collection passed to the constructor, and the heap property and function are guaranteed since external code can't access the copied collection.
 
Both of the constructors described above require an instance of a comparator
<code>Comparator<T extends DynamicHeap.Node></code> which is used to define the order of the heap (max heap or min heap).  The implementation of the comparator must satisfy the condition and requirements described in the documentation of the <code>java.util.comparator</code>.
 
Nodes passed to the constructor, and ones added later, can be updated (their ordering values changed), 
and the the heap will dynamically re-order the nodes when the method <code>updateNode(T aNode)</code> is called. 

