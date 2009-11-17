package com.queomedia.commons.asserts;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import junit.framework.AssertionFailedError;
import junit.framework.ComparisonFailure;

import com.queomedia.commons.checks.Check;
import com.queomedia.commons.equals.EqualsChecker;

/**
 * This tool class provides several checks.
 * This class is designed for use in test cases.
 * 
 * All argument checks throw an {@code IllegalArgumentException} or a subclass if the check fails.
 * All other checks throw an {@code AssertionFailedError} or (@ ComparisonFailure } if the check fails.
 * 
 */
public abstract class AssertUtil {

    /**
     * Util classes need no constructor. 
     */
    private AssertUtil() {
    }

    /**
     * Assert that both time stamps are second precise equals.
     * Background: {@link java.util.Date} is millisecond precice.
     * 
     * @param message additional message for the failure description when the check fails
     * @param expected expected value
     * @param found found value
     */
    public static void secPreciceEquals(final String message, final Date expected, final Date found) {
        if (expected.getTime() / 1000 != found.getTime() / 1000) {
            AssertUtil.failCompare(AssertUtil.format(message,
                    "[Assertion failed] - collection does not contrain expected item"), expected != null ? expected
                    .toString() : "null", found != null ? found.toString() : "null");
        }
    }

    /**
     * Assert that both time stamps are second precise equals.
     * Background: {@link java.util.Date} is millisecond precice.
     * 
     * @param expected expected value
     * @param found found value
     */
    public static void secPreciceEquals(final Date expected, final Date found) {
        AssertUtil.secPreciceEquals(null, expected, found);
    }

    /**
     * Assert that both objects are not equals.
     * 
     * @param o1 the fist object
     * @param o2 the second object
     * @param message additional message for the failure description when the check fails
     */
    public static void notEquals(final String message, final Object o1, final Object o2) {
        if (o1 == o2) {
            AssertUtil.fail(AssertUtil.format(message, "[Assertion failed] - both objects are same but should not be"));
        }
        if ((o1 == null) ^ (o2 == null)) {
            return;
        }

        assert (o1 != null);
        assert (o2 != null);

        if (o1.equals(o2)) {
            AssertUtil.fail(AssertUtil
                    .format(message, "[Assertion failed] - both objects are equals but should not be"));
        }
        return;
    }

    /**
     * Assert that both objects are not equals.
     * 
     * @param o1 the fist object
     * @param o2 the second object
     */
    public static void notEquals(final Object o1, final Object o2) {
        AssertUtil.notEquals(null, o1, o2);
    }

    /**
     * Asserts that two Strings are equal if there whitespace are striped.
     * 
     * @param expected the expected
     * @param found the found
     * @param message additional message for the failure description when the check fails - can be null
     */
    public static void equalsWithoutWhitespace(final String message, final String expected, final String found) {
        Check.notNullArgument(expected, "expected");
        Check.notNullArgument(found, "found");
        String expectedNormalized = expected.replace(" ", "");
        String foundNormalized = found.replace(" ", "");

        if (!expectedNormalized.equals(foundNormalized)) {
            AssertUtil.failCompare(AssertUtil.format(message, "[Assertion failed] - trimed strings are not equal"),
                    expectedNormalized,
                    foundNormalized);
        }
    }

    /**
     * Asserts that two Strings are equal if there whitespace are striped.
     * 
     * @param expected the expected
     * @param found the found
     */
    public static void equalsWithoutWhitespace(final String expected, final String found) {
        AssertUtil.equalsWithoutWhitespace(null, expected, found);
    }

    /**
     * Check that the collection is empty.
     * 
     * @param collection the collection
     * @param message additional message for the failure description when the check fails
     */
    public static void isEmpty(final String message, final Collection<?> collection) {
        Check.notNullArgument(collection, "collection");
        if (collection.size() != 0) {
            AssertUtil.failCompare(AssertUtil.format(message, "[Assertion failed] - no elements excected"),
                    0,
                    collection.size());
        }
    }

    /**
     * Checks if is empty.
     * 
     * @param collection the collection
     */
    public static void isEmpty(final Collection<?> collection) {
        AssertUtil.isEmpty(null, collection);
    }

    /**
     * Checks for the correct size size.
     * 
     * @param expectedSize the expected size
     * @param foundCollection the collection
     * @param message additional message for the failure description when the check fails
     */
    public static void hasSize(final String message, final int expectedSize, final Collection<?> foundCollection) {
        Check.notNullArgument(foundCollection, "collection");

        if (expectedSize != foundCollection.size()) {
            AssertUtil.failCompare(AssertUtil.format(message, "[Assertion failed] - collection has wrong size"),
                    expectedSize,
                    foundCollection.size());
        }
    }

    /**
     * Checks for the correct size size.
     * 
     * @param size the expected size
     * @param collection the collection
     */
    public static void hasSize(final int size, final Collection<?> collection) {
        AssertUtil.hasSize(null, size, collection);
    }

    /**
     * Checks hat both collections have the same size.
     * 
     * @param message additional message for the failure description when the check fails
     * @param expected the expected collection (size)
     * @param found the found collection (size)
     */
    public static <T, K> void sameSize(final String message, final Collection<T> expected, final Collection<K> found) {
        Check.notNullArgument(expected, "expected");
        Check.notNullArgument(found, "found");

        if (found.size() != expected.size()) {
            AssertUtil.failCompare(AssertUtil.format(message,
                    "[Assertion failed] - collections does not habe the same size - expected collection=" + expected
                            + " found collection=" + found), expected.size(), found.size());
        }
    }

    /**
     * Checks hat both collections have the same size.
     * 
     * @param expected the expected collection (size)
     * @param found the found collection (size)
     */
    public static <T, K> void sameSize(final Collection<T> expected, final Collection<K> found) {
        AssertUtil.sameSize(null, expected, found);
    }

    /**
     * Check that the two collections contains exactly equals elements.
     * The order doesn't matter.
     * 
     * @param expected one collection
     * @param found the other collection
     * @param message additional message for the failure description when the check fails
     */
    public static <T> void containsExact(final String message, final Collection<T> expected, final Collection<T> found) {
        Check.notNullArgument(expected, "expected");
        Check.notNullArgument(found, "found");

        AssertUtil.sameSize(message, expected, found);
        for (T expectedItem : expected) {
            if (!found.contains(expectedItem)) {
                AssertUtil.failCompare(AssertUtil.format(message, "[Assertion failed] - collection + " + found
                        + " does not contrain " + expectedItem), expected, found);
            }
        }
    }

    /**
     * Contains exact.
     * 
     * @param expected the expected
     * @param found the found
     */
    public static <T> void containsExact(final Collection<T> expected, final Collection<T> found) {
        AssertUtil.containsExact(null, expected, found);
    }

    /**
     * Check that the collection contains exactly the one element.
     * 
     * @param expectedItem the expected item can be {@code null}
     * @param message additional message for the failure description when the check fails
     * @param found the found
     */
    public static <T> void containsExact(final String message, final T expectedItem, final Collection<T> found) {
        /* expectedItem can be null */
        Check.notNullArgument(found, "found");

        if (found.size() != 1) {
            AssertUtil.failCompare(AssertUtil.format(message,
                    "[Assertion failed] - collection does not have exactly one item"), expectedItem, found);
        }
        if (!found.contains(expectedItem)) {
            AssertUtil.failCompare(AssertUtil.format(message,
                    "[Assertion failed] - collection does not contrain expected element"), expectedItem, found);
        }
    }

    /**
     * Contains exact.
     * 
     * @param expectedItem the expected item can be {@code null}
     * @param found the found
     */
    public static <T> void containsExact(final T expectedItem, final Collection<T> found) {
        AssertUtil.containsExact(null, expectedItem, found);
    }

    /**
     * Check that the two collections contains equals (by a specific definition) elements.
     * The order doesn't matter.
     * 
     * @param message additional message for the failure description when the check fails
     * @param expected one collection
     * @param found the found
     * @param equalsChecker the equals checker
     */
    public static <T, K> void containsExact(final String message, final Collection<T> expected,
            final Collection<K> found, final EqualsChecker<T, K> equalsChecker) {
        Check.notNullArgument(expected, "expected");
        Check.notNullArgument(found, "found");
        Check.notNullArgument(equalsChecker, "equalsChecker");

        AssertUtil.sameSize(message, expected, found);
        for (T expectedObject : expected) {
            boolean ok = false;
            for (K foundObject : found) {
                if (equalsChecker.equals(expectedObject, foundObject)) {
                    ok = true;
                    break;
                }
            }
            if (!ok) {
                AssertUtil.failCompare(AssertUtil.format(message, "collections does not contain equal elements "
                        + "first not found element=" + expectedObject), expected, found);
            }
        }
    }

    /**
     * Check that the two collections contains equals (by a specific definition) elements.
     * The order doesn't matter.
     * 
     * @param expected one collection
     * @param found the found
     * @param equalsChecker the equals checker
     */
    public static <T, K> void containsExact(final Collection<T> expected, final Collection<K> found,
            final EqualsChecker<T, K> equalsChecker) {
        AssertUtil.containsExact(null, expected, found, equalsChecker);
    }

    /**
     * Check that the two collections contains equals (by a specific definition) elements.
     * The order doesn't matter.
     * 
     * @param expectedObject the expected object
     * @param found the found collection
     * @param equalsChecker the equals checker
     * @param message additional message for the failure description when the check fails
     */
    public static <T, K> void containsExact(final String message, final T expectedObject, final Collection<K> found,
            final EqualsChecker<T, K> equalsChecker) {
        Check.notNullArgument(found, "found");
        Check.notNullArgument(equalsChecker, "equalsChecker");

        AssertUtil.hasSize(message, 1, found);
        if (!equalsChecker.equals(expectedObject, found.iterator().next())) {
            AssertUtil.failCompare(AssertUtil.format(message, "collection does not contain expected (one) element"),
                    expectedObject,
                    found);
        }
    }

    /**
     * Check that the two collections contains equals (by a specific definition) elements.
     * The order doesn't matter.
     * 
     * @param expectedObject the expected object
     * @param found the found collection
     * @param equalsChecker the equals checker
     */
    public static <T, K> void containsExact(final T expectedObject, final Collection<K> found,
            final EqualsChecker<T, K> equalsChecker) {
        AssertUtil.containsExact(null, expectedObject, found, equalsChecker);
    }

    /**
     * Check that the two collections contains exactly equals elements in the same order.
     * 
     * @param expected one collection
     * @param message additional message for the failure description when the check fails
     * @param found the found
     */
    public static <T> void sameOrder(final String message, final List<T> expected, final List<T> found) {
        Check.notNullArgument(expected, "expected");
        Check.notNullArgument(found, "found");

        AssertUtil.sameSize(message, expected, found);
        int size = expected.size();
        for (int i = 0; i < size; i++) {
            if (!expected.get(i).equals(found.get(i))) {
                AssertUtil.failCompare(AssertUtil.format(message,
                        "[Assertion failed] - the elements have not the same order - " + "first difference at index "
                                + i + " - expected element=" + expected.get(i) + ", found element=" + found.get(i)),
                        expected,
                        found);
            }
        }
    }

    /**
     * Check that the two collections contains exactly equals elements in the same order.
     * 
     * @param expected one collection
     * @param found the found
     */
    public static <T> void sameOrder(final List<T> expected, final List<T> found) {
        AssertUtil.sameOrder(null, expected, found);
    }

    /**
     * Check that the two collections contains exactly equals (by a specific definition) elements in the same order.
     * 
     * @param expected one collection
     * @param equalsChecker the equals definition
     * @param message additional message for the failure description when the check fails
     * @param found the found
     */
    public static <T, K> void sameOrder(final String message, final List<T> expected, final List<K> found,
            final EqualsChecker<T, K> equalsChecker) {
        Check.notNullArgument(expected, "expected");
        Check.notNullArgument(found, "found");

        AssertUtil.sameSize(expected, found);
        int size = expected.size();
        for (int i = 0; i < size; i++) {
            try {
                if (!equalsChecker.equals(expected.get(i), found.get(i))) {
                    AssertUtil.failCompare(AssertUtil.format(message,
                            "[Assertion failed] - the elements have not the same order - "
                                    + "first difference at index " + i + " - expected element=" + expected.get(i)
                                    + ", found element=" + found.get(i)), expected, found);
                }
            } catch (RuntimeException e) {
                throw new IllegalArgumentException("[Exception while assertion check] - the elements have not the same order"
                        + "(first difference at index "
                        + i
                        + " (expected="
                        + expected.get(i)
                        + " found="
                        + found.get(i) + ")) - expected list + " + expected + " found list " + found,
                        e);
            }
        }
    }

    /**
     * Check that the two collections contains exactly equals (by a specific definition) elements in the same order.
     * 
     * @param expected one collection
     * @param equalsChecker the equals definition
     * @param found the found
     */
    public static <T, K> void sameOrder(final List<T> expected, final List<K> found,
            final EqualsChecker<T, K> equalsChecker) {
        AssertUtil.sameOrder(null, expected, found, equalsChecker);
    }

    /**
     * Check that the elements of expects are element of found too (by a specific definition) elements.
     * The order doesn't matter.
     * {@code found} can have some more elements.
     * 
     * @param message additional message for the failure description when the check fails
     * @param expectedObject the expected object
     * @param found the found
     * @param equalsChecker the specific equals definition
     */
    public static <T, K> void containsAtLeast(final String message, final T expectedObject, final Collection<K> found,
            final EqualsChecker<T, K> equalsChecker) {
        Check.notNullArgument(found, "found");
        Check.notNullArgument(equalsChecker, "equalsChecker");

        for (K foundObject : found) {
            if (equalsChecker.equals(expectedObject, foundObject)) {
                return;
            }
        }
        AssertUtil.failCompare(AssertUtil.format(message,
                "[Assertion failed] - expected object not found in collection"), expectedObject, found);
    }

    /**
     * Check that the elements of expects are element of found too (by a specific definition) elements.
     * The order doesn't matter.
     * {@code found} can have some more elements.
     * 
     * @param expectedObject the expected object
     * @param found the found
     * @param equalsChecker the specific equals definition
     */
    public static <T, K> void containsAtLeast(final T expectedObject, final Collection<K> found,
            final EqualsChecker<T, K> equalsChecker) {
        AssertUtil.containsAtLeast(null, expectedObject, found, equalsChecker);
    }

    /**
     * Check that the elements of expects are element of found too (by a specific definition) elements.
     * The order doesn't matter.
     * {@code found} can have some more elements.
     * 
     * @param expected one collection
     * @param equalsChecker the specific equals definition
     * @param message additional message for the failure description when the check fails
     * @param found the found
     */
    public static <T, K> void containsAtLeast(final String message, final Collection<T> expected,
            final Collection<K> found, final EqualsChecker<T, K> equalsChecker) {
        Check.notNullArgument(expected, "expected");
        Check.notNullArgument(found, "found");
        Check.notNullArgument(equalsChecker, "equalsChecker");

        for (T expectedObject : expected) {
            AssertUtil.containsAtLeast(message, expectedObject, found, equalsChecker);
        }
    }

    /**
     * Check that the elements of expects are element of found too (by a specific definition) elements.
     * The order doesn't matter.
     * {@code found} can have some more elements.
     * 
     * @param expected one collection
     * @param equalsChecker the specific equals definition
     * @param found the found
     */
    public static <T, K> void containsAtLeast(final Collection<T> expected, final Collection<K> found,
            final EqualsChecker<T, K> equalsChecker) {
        AssertUtil.containsAtLeast(null, expected, found, equalsChecker);
    }

    /**
     * Assert that the collection contains the item.
     * The Collection can have other items too.
     * 
     * @param expectedItem the expected item
     * @param found the found
     * @param message additional message for the failure description when the check fails
     */
    public static <T> void contains(final String message, final T expectedItem, final Collection<T> found) {
        Check.notNullArgument(found, "found");

        if (!found.contains(expectedItem)) {
            AssertUtil.failCompare(AssertUtil.format(message,
                    "[Assertion failed] - collection does not contrain expected item"), expectedItem, found);
        }
    }

    /**
     * Contains.
     * 
     * @param expectedItem the expected item
     * @param found the found
     */
    public static <T> void contains(final T expectedItem, final Collection<T> found) {
        AssertUtil.contains(null, expectedItem, found);
    }

    /**
     * Assert that the collection contains the expected items.
     * The Collection can have other items too.
     * 
     * @param found the found
     * @param expectedItems the expected items
     */
    public static <T> void contains(final Collection<T> expectedItems, final Collection<T> found) {
        Check.notNullArgument(expectedItems, "expectedItems");
        Check.notNullArgument(found, "found");

        for (T exptetedItem : expectedItems) {
            AssertUtil.contains(exptetedItem, found);
        }
    }

    /**
     * Assert that the collection does not contains the item.
     * The Collection can have other items.
     * 
     * @param notExpectedItem the not expected item
     * @param found the found
     * @param message additional message for the failure description when the check fails
     */
    public static <T> void containsNot(final String message, final T notExpectedItem, final Set<T> found) {
        Check.notNullArgument(found, "found");

        if (found.contains(notExpectedItem)) {
            AssertUtil.fail(AssertUtil.format(message, "[Assertion failed] - colection + " + found
                    + " does contrain the not expected item " + notExpectedItem));
        }
    }

    /**
     * Contains not.
     * 
     * @param notExpectedItem the not expected item
     * @param found the found
     */
    public static <T> void containsNot(final T notExpectedItem, final Set<T> found) {
        AssertUtil.containsNot(null, notExpectedItem, found);
    }

    /**
     * Fails a test with the given message.
     * 
     * @param message failure description 
     */
    static public void fail(final String message) {
        throw new AssertionFailedError(message);
    }

    /**
     * Fail compare.
     * 
     * @param message the failure description - can be {@code null}
     * @param expected the expected
     * @param actual the actual
     */
    static public void failCompare(final String message, final String expected, final String actual) {
        throw new ComparisonFailure(message, expected, actual);
    }

    /**
     * Fail compare.
     * 
     * @param message additional message for the failure description - can be {@code null}
     * @param expected the expected
     * @param actual the actual
     */
    static public void failCompare(final String message, final int expected, final int actual) {
        AssertUtil.failCompare(message, Integer.toString(expected), Integer.toString(actual));
    }

    /**
     * Fail compare.
     * 
     * @param message additional message for the failure description - can be {@code null}
     * @param expected the expected
     * @param actual the actual
     */
    static public void failCompare(final String message, final Collection<?> expected, final Collection<?> actual) {
        AssertUtil.failCompare(message, expected.toString(), actual.toString());
    }

    /**
     * Fail compare.
     * 
     * @param message additional message for the failure description - can be {@code null}
     * @param expected the expected
     * @param actual the actual
     */
    static public void failCompare(final String message, final Object expected, final Collection<?> actual) {
        AssertUtil.failCompare(message, expected != null ? expected.toString() : null, actual.toString());
    }

    /**
     * Format.
     * 
     * @param message additional message for the failure description - can be {@code null}
     * @param cause the cause
     * 
     * @return the string
     */
    public static String format(final String message, final String cause) {
        /* message can be null */
        Check.notNullArgument(cause, "cause");
        if (message == null) {
            return cause;
        } else {
            return message + " " + cause;
        }
    }
}
