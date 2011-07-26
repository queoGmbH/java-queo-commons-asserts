package com.queomedia.commons.asserts;

import java.util.ArrayList;
import java.util.Arrays;

import junit.framework.ComparisonFailure;

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
    
}
