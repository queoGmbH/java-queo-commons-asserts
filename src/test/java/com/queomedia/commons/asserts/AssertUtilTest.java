package com.queomedia.commons.asserts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import junit.framework.ComparisonFailure;

import org.junit.Ignore;
import org.junit.Test;

public class AssertUtilTest {

    @Test
    public void testEqualsWithoutWhitespace() {
        AssertUtil.equalsWithoutWhitespace(" hallo", "hallo ");
    }

    @Test(expected = ComparisonFailure.class)
    public void testEqualsWithoutWhitespace_notEqual() {
        AssertUtil.equalsWithoutWhitespace(" haallo", "hallo");
    }

    @Test
    public void testContainsExact() {
        AssertUtil.containsExact(Integer.valueOf(15), Arrays.asList(Integer.valueOf(15)));
    }

    @Test(expected = ComparisonFailure.class)
    public void testContainsExact_empty() {

        AssertUtil.containsExact(Integer.valueOf(15), new ArrayList<Integer>());
    }

    @Test(expected = ComparisonFailure.class)
    public void testContainsExact_wrongItem() {

        AssertUtil.containsExact(Integer.valueOf(15), Arrays.asList(Integer.valueOf(14)));
    }

    @Test(expected = ComparisonFailure.class)
    public void testContainsExact_tooMutchItems() {

        AssertUtil.containsExact(Integer.valueOf(15), Arrays.asList(Integer.valueOf(15), Integer.valueOf(14)));
    }
    
    /**
     * given two equals lists: with the following character
     * - both have the same size
     * - "found" contains ALL objects that are in "expected"
     * - the items are in different order (this is legal for containsExact)
     * - they contains the same elements
     * 
     * expected: 1,1,2
     * found:    1,1,2
     */
    @Test
    public void testContainsExact_withSameDoubleItems() {
        AssertUtil.containsExact(Arrays.asList(1, 1, 2), Arrays.asList(1, 1, 2));
    }

    /**
     * given two different lists: with the following character
     * - both have the same size
     * - "found" contains ALL objects that are in "expected"
     * - but they are different!
     * 
     * expected: 1,1,2
     * found:    1,2,2
     */
    @Ignore
    @Test(expected = ComparisonFailure.class)
    public void testContainsExact_withDifferntDoubleItems() {
        AssertUtil.containsExact(Arrays.asList(1, 1, 2), Arrays.asList(1, 2, 2));
    }
    
    private static class RefletionObject {
        @SuppressWarnings("unused")
        private String content;

        public RefletionObject(String content) {
            this.content = content;
        }
    }
  

    /**
     * Test assert reflectiv equals.
     */
    @Test
    public void testAssertReflectivEquals() {
        AssertUtil.assertReflectivEquals(null, new RefletionObject("a"), new RefletionObject("a"));
    }
    
    /**
     * Test assert not reflectiv equals.
     */
    @Test(expected = ComparisonFailure.class)
    public void testAssertReflectivEqualsWithNotEquals() {
        AssertUtil.assertReflectivEquals(null, new RefletionObject("a"), new RefletionObject("b"));
    }
    
    
    
    @Test
    public void testAssertIsEmptyOrNull() {
        AssertUtil.isEmptyOrNull(null);
        AssertUtil.isEmptyOrNull(Collections.emptyList()); 
    }

    
    @Test(expected = ComparisonFailure.class)
    public void testAssertIsEmptyOrNullWithFilledList() {        
        AssertUtil.isEmptyOrNull(Arrays.asList(1)); 
    }
}
