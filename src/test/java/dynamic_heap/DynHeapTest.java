package dynamic_heap;
/**
 * 
 */

//import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.and;
//import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.feature;
//import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.given;
//import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.scenario;
//import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.then;
//import static com.greghaskins.spectrum.dsl.gherkin.Gherkin.when;
//import static com.greghaskins.spectrum.dsl.specification.Specification.afterAll;
//import static com.greghaskins.spectrum.dsl.specification.Specification.afterEach;
//import static com.greghaskins.spectrum.dsl.specification.Specification.beforeAll;
//import static com.greghaskins.spectrum.dsl.specification.Specification.beforeEach;
//import static com.greghaskins.spectrum.dsl.specification.Specification.context;
//import static com.greghaskins.spectrum.dsl.specification.Specification.describe;
//import static com.greghaskins.spectrum.dsl.specification.Specification.fcontext;
//import static com.greghaskins.spectrum.dsl.specification.Specification.it;
//import static com.greghaskins.spectrum.dsl.specification.Specification.let;
//import static com.greghaskins.spectrum.dsl.specification.Specification.xcontext;

import static com.greghaskins.spectrum.dsl.specification.Specification.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

//import static org.hamcrest.Matchers.contains;
//import static org.hamcrest.Matchers.empty;
//import static org.hamcrest.Matchers.equalTo;
//import static org.hamcrest.Matchers.is;
//import static org.hamcrest.Matchers.not;
//import static org.junit.Assert.assertEquals;

import com.greghaskins.spectrum.*;
//import com.greghaskins.spectrum.dsl.gherkin.Gherkin;

//import org.hamcrest.core.Is;
//import org.junit.Assert;
//import org.junit.runner.Result;
import org.junit.runner.RunWith;

import java.util.ArrayList;
//import java.util.List;
//import java.util.function.Supplier;
//import java.util.*;

import com.gaaiat.ml.dynamicheap.*;

/**
 * @author Ali Ismael
 *
 */
@RunWith(Spectrum.class)
public class DynHeapTest {
	DynamicHeap<TestNode> dynHeap;
	{

		describe("DynamicHeap", () -> {
			int initHeapSize = 10000;

			beforeAll(() -> {
				ArrayList<TestNode> initialArray = new ArrayList<TestNode>(initHeapSize);
				TestNode tempNode;

				for (int i = 0; i < initHeapSize; i++) {
					initialArray.add(new TestNode(Math.random()));
				}

				dynHeap = new DynamicHeap<TestNode>(initialArray, new TestNode.NodeComparator());

				tempNode = initialArray.get(11);
				tempNode.value = Math.random();
				dynHeap.updateNode(tempNode);
			});

			// afterEach(list::clear);

			it("should contain " + initHeapSize + " elements", () -> {
				assertThat(dynHeap.size(), is(initHeapSize));
			});

			it("should be able to add nodes", () -> {
				dynHeap.add(new TestNode(Math.random()));
				assertThat(dynHeap.size(), is(initHeapSize + 1));
				// assertThat(list, contains("foo", "bar"));
			});

			it("should have sorted nodes", () -> {
				double tempValue = dynHeap.pull().value;

				while (dynHeap.size() > 0) {
					double heapRootValue = dynHeap.pull().value;
					assertThat(tempValue, lessThanOrEqualTo(heapRootValue));

					tempValue = heapRootValue;
				}

				assertThat(dynHeap.size(), is(0));
			});
		});
	}

}
