package com.queomedia.commons.asserts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;

import com.queomedia.commons.equals.EqualsChecker;

public class AssertUtilTest {

    @Test
    public void testEqualsWithoutWhitespace() {
        AssertUtil.equalsWithoutWhitespace(" hallo", "hallo ");
    }

    @Test
    public void testEqualsWithoutWhitespace_notEqual() {
        Assertions.assertThrows(AssertionFailedError.class, () -> {
            AssertUtil.equalsWithoutWhitespace(" haallo", "hallo");
        });
    }

    @Test
    public void testContainsExact() {
        AssertUtil.containsExact(15, Arrays.asList(15));
    }

    @Test
    public void testContainsExact_empty() {
        Assertions.assertThrows(AssertionFailedError.class, () -> {
            AssertUtil.containsExact(15, new ArrayList<Integer>());
        });
    }

    @Test
    public void testContainsExact_wrongItem() {
        Assertions.assertThrows(AssertionFailedError.class, () -> {
            AssertUtil.containsExact(15, Arrays.asList(14));
        });
    }

    @Test
    public void testContainsExact_tooMutchItems() {
        Assertions.assertThrows(AssertionFailedError.class, () -> {
            AssertUtil.containsExact(15, Arrays.asList(15, 14));
        });
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
    @Test
    public void testContainsExact_withDifferntDoubleItems() {
        Assertions.assertThrows(AssertionFailedError.class, () -> {
            AssertUtil.containsExact(Arrays.asList(1, 1, 2), Arrays.asList(1, 2, 2));
        });
    }

    @Test
    public void testContainsExact_nullElement() {
        AssertUtil.containsExact((Object) null, Arrays.asList((Object) null));
    }

    @Test
    public void testContainsExact_nullElementList() {
        AssertUtil.containsExact(Arrays.asList((Object) null), Arrays.asList((Object) null));
    }

    /**
     * given two equal lists 
     * expected: null, null, o
     * found:    null, null, o
     */
    @Test
    public void testContainsExact_doubleNullElementList() {
        Object o = new Object();
        AssertUtil.containsExact(Arrays.asList((Object) null, (Object) null, o),
                Arrays.asList((Object) null, (Object) null, o));
    }

    /**
     * given two different lists 
     * expected: null, null, o
     * found:    null, o, o
     */
    @Test
    public void testContainsExact_withDifferntDoubleItemsNull() {
        Object o = new Object();
        Assertions.assertThrows(AssertionFailedError.class, () -> {
            AssertUtil.containsExact(Arrays.asList((Object) null, (Object) null, o),
                    Arrays.asList((Object) null, o, o));
        });
    }

    @Test
    public void testContainsExact_equalsCheckerNonBijectionProblem() {
        AssertUtil.containsExact(Arrays.asList(10, 20), Arrays.asList(10, 20), new LessThanEqualsEqualsChecker());
    }

    /** This is the Probelm: this test should not fail but it does.  - see AssertUtil.containsExact for details. */
    @Disabled
    @Test
    public void testContainsExact_equalsCheckerNonBijectionProblem_otherOrder() {
        AssertUtil.containsExact(Arrays.asList(10, 20), Arrays.asList(20, 10), new LessThanEqualsEqualsChecker());
    }

    private static final class LessThanEqualsEqualsChecker implements EqualsChecker<Integer, Integer> {
        @Override
        public boolean equals(Integer objectT, Integer objectK) {
            return objectT.intValue() <= objectK.intValue();
        }
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
    @Test
    public void testAssertReflectivEqualsWithNotEquals() {
        Assertions.assertThrows(AssertionFailedError.class, () -> {
            AssertUtil.assertReflectivEquals(null, new RefletionObject("a"), new RefletionObject("b"));
        });
    }

    @Test
    public void testAssertIsEmptyOrNull() {
        AssertUtil.isEmptyOrNull(null);
        AssertUtil.isEmptyOrNull(Collections.emptyList());
    }

    @Test
    public void testAssertIsEmptyOrNullWithFilledList() {
        Assertions.assertThrows(AssertionFailedError.class, () -> {
            AssertUtil.isEmptyOrNull(Arrays.asList(1));
        });
    }

    @Test
    public void testHasSize() {
        Assertions.assertThrows(AssertionFailedError.class, () -> {
            AssertUtil.hasSize(1, Arrays.asList(1, 2, 3, 4));
        });
    }

    @Test
    public void testMapHasSize() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);

        Assertions.assertThrows(AssertionFailedError.class, () -> {
            AssertUtil.hasSize(1, map);
        });
    }

}
