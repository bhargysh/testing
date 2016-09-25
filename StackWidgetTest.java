package test;

import static org.junit.Assert.*;
import main.StackWidget;
import main.WidgetElementType;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StackWidgetTest {

	StackWidget myStackWidgetTester;
	
	@Before
	public void setUp() throws Exception {
		myStackWidgetTester = new StackWidget();
	}

	@After
	public void tearDown() throws Exception {
	}

	
//--- This section tests adding widgets with valid parameterization to WidgetStack
	
	@Test
	// *Test 1*
	public void testAddFirstWellParametrizedWidgetSucceeds() {
		assertTrue("Adding a widget element should succeed", myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 0, 100, 20, 50));
		assertTrue("Widget with id=1234 should be in list", myStackWidgetTester.isWidgetElementInList("1234"));
		assertEquals("There should be 1 widget in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 1);
	}
	
	@Test
	// *Test 2*
	public void testAddMaxAmountWidgetInStack() {
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1111", 50, 500, 60, 300);
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "2222", 50, 500, 60, 300);
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "3333", 50, 500, 60, 300);
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "4444", 50, 500, 60, 300);
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "5555", 50, 500, 60, 300);
		assertFalse("Widget 6666 should not be added successfully as StackWidget limit is exceeded", myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "6666", 50, 500, 60, 300));
		assertFalse("Widget with id=6666 should not be in list", myStackWidgetTester.isWidgetElementInList("6666"));
		assertEquals("There should be 5 widgets in StackWidget", myStackWidgetTester.getNumWidgetElements(), 5);
	}
	
	@Test
	// *Test 3*
	public void testAddExistingWidgetID(){
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 50, 500, 60, 300);
		assertFalse("Widget 1234 exists in StackWidget already", myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 50, 500, 60, 300));
		assertTrue("Widget with id=1234 should be in list", myStackWidgetTester.isWidgetElementInList("1234"));
		assertEquals("There should be 1 widget in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 1);
	}
	
	@Test
	// *Test 4*
	public void testAddingWidgetTopOfStack(){
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1111", 50, 500, 60, 300);
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "2222", 50, 500, 60, 300);
		assertTrue("Widget with id=2222 is added on top of stack", (myStackWidgetTester.getWidgetElementIndex("2222") > myStackWidgetTester.getWidgetElementIndex("1111")));
		assertTrue("Widget with id=1111 should be in list", myStackWidgetTester.isWidgetElementInList("1111"));
		assertTrue("Widget with id=2222 should be in list", myStackWidgetTester.isWidgetElementInList("2222"));
		assertEquals("There should be 2 widgets in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 2);
	}
	
//--- This section tests adding widgets with invalid parameterization to WidgetStack
	
	@Test(expected = IllegalArgumentException.class)
	// *Test 1*
	public void testAddWidgetIDThreeDigits() {
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "182", 50, 500, 60, 300);
		assertFalse("Widget with id=182 should not be in list", myStackWidgetTester.isWidgetElementInList("182"));
		assertEquals("There should be no widgets in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	// *Test 2*
	public void testAddWidgetIDFiveDigits() {
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "87654", 50, 500, 60, 300);
		assertFalse("Widget with id=87654 should not be in list", myStackWidgetTester.isWidgetElementInList("87654"));
		assertEquals("There should be no widgets in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 0);
	}
	
	@Test(expected = NullPointerException.class)
	// *Test 3*
	public void testAddNullWidgetID() {
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, null, 50, 500, 60, 300);
		assertFalse("Widget with id=null should not be in list", myStackWidgetTester.isWidgetElementInList(null));
		assertEquals("There should be no widgets in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	// *Test 4*
	public void testAddNonNumericWidgetID() {
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "!jk$", 50, 500, 60, 300);
		assertFalse("Widget with id=!jk$ should not be in list", myStackWidgetTester.isWidgetElementInList("!jk$"));
		assertEquals("There should be no widgets in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 0);
	}
	
	@Test
	// *Test 5*
	public void testAddWidgetExceedingLegibleHeight(){
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 50, 500, 501, 300);
		assertFalse("Widget with id=1234 should not be in list", myStackWidgetTester.isWidgetElementInList("1234"));
		assertEquals("There should be no widgets in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	// *Test 6a*
	public void testAddWidgetNegativeMinHeight(){
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", -1, 500, 60, 300);
		assertFalse("Widget with id=1234 should not be in list", myStackWidgetTester.isWidgetElementInList("1234"));
		assertEquals("There should be no widgets in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	// *Test 6b*
	public void testAddWidgetNegativeMaxHeight(){
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 50, -1, 60, 300);
		assertFalse("Widget with id=1234 should not be in list", myStackWidgetTester.isWidgetElementInList("1234"));
		assertEquals("There should be no widgets in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	// *Test 7a*
	public void testAddWidgetOutsideRangeMinHeight(){	
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 50, 500, 49, 300);
		assertFalse("Widget with id=1234 should not be in list", myStackWidgetTester.isWidgetElementInList("1234"));
		assertEquals("There should be no widgets in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	// *Test 7b*
	public void testAddWidgetOutsideRangeMaxHeight(){
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 50, 500, 50, 501);
		assertFalse("Widget with id=1234 should not be in list", myStackWidgetTester.isWidgetElementInList("1234"));
		assertEquals("There should be no widgets in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 0);
	}
	
//--- This section tests removing widgets with valid parameterization to WidgetStack
	
	@Test
	// *Test 1*
	public void testRemoveWidgetValidID(){
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 50, 500, 60, 300);
		assertTrue("Widget 1234 has been removed from StackWidget", myStackWidgetTester.removeWidgetElement("1234"));
		assertFalse("Widget with id=1234 should not be in list", myStackWidgetTester.isWidgetElementInList("1234"));
		assertEquals("There should be 0 widgets in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 0);
	}
	
	@Test
	// *Test 2*
	public void testRemoveWidgetNonExistentID(){
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 50, 500, 60, 300);
		assertFalse("Widget ID is invalid", myStackWidgetTester.removeWidgetElement("1235"));
		assertTrue("Widget with id=1234 should be in list", myStackWidgetTester.isWidgetElementInList("1234"));
		assertEquals("There should be 1 widget in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 1);
	}
	
	@Test
	// *Test 3*
	public void testRemoveWidgetAnywhereFromStack(){
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1111", 50, 500, 60, 300);
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "2222", 50, 500, 60, 300);
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "3333", 50, 500, 60, 300);
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "4444", 50, 500, 60, 300);
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "5555", 50, 500, 60, 300);
		assertTrue("Widget 3333 is successfully removed from StackWidget", myStackWidgetTester.removeWidgetElement("3333"));
		assertFalse("Widget with id=3333 should not be in list", myStackWidgetTester.isWidgetElementInList("3333"));
		assertEquals("There should be 3 widgets in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 4);
	}

	@Test
	// *Test 4*
	public void testRemoveWidgetEmptyStack(){
		assertFalse("StackWidget is empty, cannot remove widget 1234", myStackWidgetTester.removeWidgetElement("1234"));
		assertEquals("There should be 0 widgets in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 0);
	}
	
//--- This section tests removing widgets with invalid parameterization to WidgetStack	
	
	@Test
	// *Test 1*
	public void testRemoveWidgetIDThreeDigits(){
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 50, 500, 60, 300);
		assertFalse("Widget ID is invalid", myStackWidgetTester.removeWidgetElement("182"));
		assertTrue("Widget with id=1234 should be in list", myStackWidgetTester.isWidgetElementInList("1234"));
		assertEquals("There should be 1 widget in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 1);
	}
	
	@Test
	// *Test 2*
	public void testRemoveWidgetIDFiveDigits(){
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 50, 500, 60, 300);
		assertFalse("Widget ID is invalid", myStackWidgetTester.removeWidgetElement("87654"));
		assertTrue("Widget with id=1234 should be in list", myStackWidgetTester.isWidgetElementInList("1234"));
		assertEquals("There should be 1 widget in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 1);
	}
	
	@Test(expected = NullPointerException.class)
	// *Test 3*
	public void testRemoveWidgetNullID(){
		myStackWidgetTester.addWidgetElement(WidgetElementType.WIDGETELEMENT_FUNNYTEXTFIELD, "1234", 50, 500, 60, 300);
		assertFalse("Widget ID is invalid", myStackWidgetTester.removeWidgetElement(null));
		assertTrue("Widget with id=1234 should be in list", myStackWidgetTester.isWidgetElementInList("1234"));
		assertEquals("There should be 1 widget in the StackWidget", myStackWidgetTester.getNumWidgetElements(), 1);
	}
	
}
