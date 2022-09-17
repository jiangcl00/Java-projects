package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Set;

import org.junit.Test;

import junit.framework.TestCase;
import tree.PlaceKeysValuesInArrayLists;
import tree.PolymorphicBST;

public class StudentTests extends TestCase {

	@Test
	public void testPutGetSizeHeightMinMax() {
		PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();

		assertEquals(0, ptree.size());

		ptree.put(2, "Two");
		ptree.put(1, "One");
		ptree.put(3, "Three");
		ptree.put(1, "OneSecondTime");

		assertEquals(3, ptree.size());
		assertEquals(2, ptree.height());
		assertEquals("OneSecondTime", ptree.get(1));
		assertEquals("Two", ptree.get(2));
		assertEquals("Three", ptree.get(3));
		assertEquals(null, ptree.get(8));

		int max = ptree.getMax();
		int min = ptree.getMin();

		assertEquals(3, max);
		assertEquals(1, min);
	}

	@Test
	public void testRemoveClear() {
		PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();

		assertEquals(0, ptree.size());

		ptree.put(2, "Two");
		ptree.put(1, "One");
		ptree.put(3, "Three");

		assertEquals(3, ptree.size());
		assertEquals("One", ptree.get(1));
		assertEquals("Two", ptree.get(2));
		assertEquals("Three", ptree.get(3));

		ptree.remove(1);
		int afterMin = ptree.getMin();

		assertEquals(2, afterMin);
		assertEquals(2, ptree.size());
		assertEquals(null, ptree.get(1));

		ptree.clear();

		assertEquals(0, ptree.size());
		assertEquals(null, ptree.get(2));
		assertEquals(null, ptree.get(3));
		try {
			assertEquals(null, ptree.getMin());
			fail("Should have thrown NoSuchElementException");
		} catch (NoSuchElementException e) {
			assert true; // as intended
		}

	}

	@Test
	public void testKeySetSubMap() {
		PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();

		ptree.put(2, "Two");
		ptree.put(1, "One");
		ptree.put(3, "Three");

		Set<Integer> keySet = ptree.keySet();

		assertEquals("[1, 2, 3]", keySet.toString());

		PolymorphicBST<Integer, String> subMap = ptree.subMap(1, 2);

		assertEquals("One", subMap.get(1));
		assertEquals("Two", subMap.get(2));
		assertEquals(null, subMap.get(3));

		PolymorphicBST<Integer, String> subMap2 = ptree.subMap(1, 3);

		assertEquals("One", subMap2.get(1));
		assertEquals("Two", subMap2.get(2));
		assertEquals("Three", subMap2.get(3));
	}

	@Test
	public void testInOrderTraversal() {
		PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();

		assertEquals(0, ptree.size());

		ptree.put(2, "Two");
		ptree.put(1, "One");
		ptree.put(3, "Three");

		PlaceKeysValuesInArrayLists<Integer, String> InOrder = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.inorderTraversal(InOrder);

		assertEquals(InOrder.getKeys().toString(), "[1, 2, 3]");
		assertEquals(InOrder.getValues().toString(), "[One, Two, Three]");
	}

	@Test
	public void testRightRootLeftTraversal() {
		PolymorphicBST<Integer, String> ptree = new PolymorphicBST<Integer, String>();

		assertEquals(0, ptree.size());

		ptree.put(2, "Two");
		ptree.put(1, "One");
		ptree.put(3, "Three");

		PlaceKeysValuesInArrayLists<Integer, String> RightOrder = new PlaceKeysValuesInArrayLists<Integer, String>();
		ptree.rightRootLeftTraversal(RightOrder);

		assertEquals(RightOrder.getKeys().toString(), "[3, 2, 1]");
		assertEquals(RightOrder.getValues().toString(), "[Three, Two, One]");
	}
}