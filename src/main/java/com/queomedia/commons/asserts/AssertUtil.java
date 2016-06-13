package com.queomedia.commons.asserts;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import junit.framework.AssertionFailedError;
import junit.framework.ComparisonFailure;

import org.apache.commons.lang.builder.EqualsBuilder;

import com.queomedia.commons.checks.Check;
import com.queomedia.commons.equals.EqualsChecker;
import com.queomedia.commons.equals.NativeEqualsChecker;

/**
 * This tool class provides several checks.
 * This class is designed for use in test cases.
 *
 * All checks throw an {@link AssertionFailedError} or its subclass (@link ComparisonFailure} if the check fails.
 *
 */
public abstract class AssertUtil {

    /**
     * Util classes need no constructor.
     */
    private AssertUtil() {
        super();
    }

    /**
     * Milliseconds per second.
     */
    private static final int MILLISEC_PER_SECOND = 1000;

    /**
     * Assert that both time stamps are second precise equals.
     * Background: {@link java.util.Date} is millisecond precise.
     *
     * @param message additional message for the failure description when the check fails
     * @param expected expected value
     * @param found found value
     */
    public static void secPreciceEquals(final String message, final Date expected, final Date found) {
        if (expected.getTime() / MILLISEC_PER_SECOND != found.getTime() / MILLISEC_PER_SECOND) {
            AssertUtil.failCompare(AssertUtil.format(message,
                    "[Assertion failed] - collection does not contrain expected item"),
                    expected != null ? expected.toString() : "null",
                    found != null ? found.toString() : "null");
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
     * Check that the collection is empty or null.
     *
     * @param collection the collection
     * @param message additional message for the failure description when the check fails
     */
    public static void isEmptyOrNull(final String message, final Collection<?> collection) {
        if (collection != null && collection.size() != 0) {
            AssertUtil.failCompare(AssertUtil.format(message, "[Assertion failed] - no elements expected"),
                    0,
                    collection.size());
        }
    }

    /**
     * Check that the collection is empty or null.
     *
     * @param collection the collection
     * @param message additional message for the failure description when the check fails
     */
    public static void isEmptyOrNull(final Collection<?> collection) {
        AssertUtil.isEmptyOrNull(null, collection);
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
     * Checks for the correct size size.
     *
     * @param expectedSize the expected size
     * @param foundMap the mao
     * @param message additional message for the failure description when the check fails
     */
    public static void hasSize(final String message, final int expectedSize, final Map<?, ?> foundMap) {
        Check.notNullArgument(foundMap, "collection");

        if (expectedSize != foundMap.size()) {
            AssertUtil.failCompare(AssertUtil.format(message, "[Assertion failed] - map has wrong size"),
                    expectedSize,
                    foundMap.size());
        }
    }

    /**
     * Checks for the correct size size.
     *
     * @param expectedSize the expected size
     * @param foundMap the map
     * @param message additional message for the failure description when the check fails
     */
    public static void hasSize(final int expectedSize, final Map<?, ?> foundMap) {
        hasSize(null, expectedSize, foundMap);
    }

    /**
     * Checks hat both collections have the same size.
     *
     * @param message additional message for the failure description when the check fails
     * @param expected the expected collection (size)
     * @param found the found collection (size)
     * @param <T> the type of the expected object
     * @param <K> the type of the found objects
     */
    public static <T, K> void sameSize(final String message, final Collection<? extends T> expected,
            final Collection<? extends K> found) {
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
     * @param <T> the type of the expected object
     * @param <K> the type of the found objects
     */
    public static <T, K> void sameSize(final Collection<? extends T> expected, final Collection<? extends K> found) {
        AssertUtil.sameSize(null, expected, found);
    }

    /**
     * Check that the two collections contains exactly equals elements.
     * The order doesn't matter.
     * 
     * <p>
     * To pass this check, it is required that both collections contains same number appears for each element.
     * For example:
     * {@code
     *    containsExact("message", Arrays.asList(1,1,2), Arrays.asList(1,1,2))  //pass
     *    containsExact("message", Arrays.asList(1,1,2), Arrays.asList(1,2,2))  //fail
     * }
     * </p>
     *
     * @param expected one collection
     * @param found the other collection
     * @param message additional message for the failure description when the check fails
     * @param <T> The type of expected and found objects
     */
    public static <T> void containsExact(final String message, final Collection<? extends T> expected,
            final Collection<? extends T> found) {
        Check.notNullArgument(expected, "expected");
        Check.notNullArgument(found, "found");

        containsExact(message, expected, found, NativeEqualsChecker.<T> getInstance());
    }

    /**
     * Check that both collections contains exact equal items.
     * 
     * <p>
     * See {@link #containsExact(String, Collection, Collection)} for details.
     * </p>
     *  
     * @param expected the expected
     * @param found the found
     * @param <T> The type of expected and found objects
     * @see #containsExact(String, Collection, Collection)
     */
    public static <T> void containsExact(final Collection<? extends T> expected, final Collection<? extends T> found) {
        AssertUtil.containsExact(null, expected, found);
    }

    /**
     * Check that the collection contains exactly the one element.
     *
     * @param expectedItem the expected item can be {@code null}
     * @param message additional message for the failure description when the check fails
     * @param found the found
     * @param <T> The type of expected and found objects
     */
    public static <T> void containsExact(final String message, final T expectedItem, final Collection<? extends T> found) {
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
     * Check that the collection contains exactly the one element.
     *
     * @param expectedItem the expected item can be {@code null}
     * @param found the found
     * @param <T> The type of expected and found objects
     * @see #containsExact(String, Object, Collection, EqualsChecker) 
     */
    public static <T> void containsExact(final T expectedItem, final Collection<? extends T> found) {
        AssertUtil.containsExact(null, expectedItem, found);
    }

    /**
     * Check that the two collections contains equals (by a specific definition) elements.
     * The order doesn't matter.
     * 
     * 
     * <p>
     * To pass this check, it is required that both collections contains same number appears for each element.
     * For example:
     * {@code
     *    containsExact("message", Arrays.asList(1,1,2), Arrays.asList(1,1,2), NativeEqualsChecker.getInstance())  //pass
     *    containsExact("message", Arrays.asList(1,1,2), Arrays.asList(1,2,2), NativeEqualsChecker.getInstance())  //fail
     * }
     * </p>
     * 
     * <p>
     * Whenever the equals checker is not bijectiv (is not surjective (Rechtseindeutig) and not injective (linkseideutig)),
     * this mean, one expected element match at least two found elements and one found element is matched by at least
     * two expected elements.
     * 
     * for example:
     * expected: 10, 20
     * found:    10, 20
     * equalsChecker:  expected <= found
     * 
     * Then the test will pass, when found has the order (10, 20), but will fail, when found has the oder (20, 10)
     * This is not intended! - but it is the current behavior. No implementation should been build on this behavior,
     * because it will be fixed as soon as I have an idea how to fix it.
     * 
     * Other scenario:
     * Matcher {@code (A->1, B->2, AB->1 as well as 2) }
     * expected: AB, A, B, B
     * found:     1, 1, 2, 2,  - pass
     * found:     2, 1, 1, 2,  - fail
     * </p>
     * 
     *
     * @param message additional message for the failure description when the check fails
     * @param expected one collection
     * @param found the found
     * @param equalsChecker the equals checker
     * @param <T> the type of the expected object
     * @param <K> the type of the found objects
     */
    public static <T, K> void containsExact(final String message, final Collection<? extends T> expected,
            final Collection<? extends K> found, final EqualsChecker<T, K> equalsChecker) {
        Check.notNullArgument(expected, "expected");
        Check.notNullArgument(found, "found");
        Check.notNullArgument(equalsChecker, "equalsChecker");

        AssertUtil.sameSize(message, expected, found);

        //copy found to an list, to make sure that the order for each serach loop stay the same
        List<K> foundList = new ArrayList<K>(found);
        //default element value for an new boolean array is FALSE        
        boolean[] foundAllreadyMatchedElements = new boolean[found.size()];

        for (T expectedObject : expected) {
            boolean objectFound = false;

            final int size = foundList.size();
            for (int i = 0; i < size; i++) {
                if (!foundAllreadyMatchedElements[i] && equalsChecker.equals(expectedObject, foundList.get(i))) {
                    objectFound = true;
                    foundAllreadyMatchedElements[i] = true;
                    break;
                }
            }
            if (!objectFound) {
                AssertUtil.failCompare(AssertUtil.format(message,
                        "[Assertion failed] - collections does not contain equal elements "
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
     * @param <T> the type of the expected object
     * @param <K> the type of the found objects
     */
    public static <T, K> void containsExact(final Collection<? extends T> expected,
            final Collection<? extends K> found, final EqualsChecker<T, K> equalsChecker) {
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
     * @param <T> the type of the expected object
     * @param <K> the type of the found objects
     */
    public static <T, K> void containsExact(final String message, final T expectedObject,
            final Collection<? extends K> found, final EqualsChecker<T, K> equalsChecker) {
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
     * @param <T> the type of the expected object
     * @param <K> the type of the found objects
     */
    public static <T, K> void containsExact(final T expectedObject, final Collection<? extends K> found,
            final EqualsChecker<T, K> equalsChecker) {
        AssertUtil.containsExact(null, expectedObject, found, equalsChecker);
    }

    /**
     * Check that the two collections contains exactly equals elements in the same order.
     *
     * @param expected one collection
     * @param message additional message for the failure description when the check fails
     * @param found the found
     * @param <T> The type of expected and found objects
     */
    public static <T> void sameOrder(final String message, final List<? extends T> expected,
            final List<? extends T> found) {
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
     * @param <T> The type of expected and found objects
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
     * @param <T> the type of the expected object
     * @param <K> the type of the found objects
     */
    public static <T, K> void sameOrder(final String message, final List<? extends T> expected,
            final List<? extends K> found, final EqualsChecker<T, K> equalsChecker) {
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
     * @param <T> the type of the expected object
     * @param <K> the type of the found objects
     */
    public static <T, K> void sameOrder(final List<? extends T> expected, final List<? extends K> found,
            final EqualsChecker<T, K> equalsChecker) {
        AssertUtil.sameOrder(null, expected, found, equalsChecker);
    }

    /**
     * Check that the elements of expects are element of found too (by a specific definition) elements.
     * The order doesn't matter.
     * {@code found} can have some more elements.
     * 
     * This method is only for internal use
     *
     * @param expectedObject the expected object
     * @param found the found
     * @param equalsChecker the specific equals definition
     * @param <T> the type of the expected object
     * @param <K> the type of the found objects
     */
    private static <T, K> boolean isContainingAtLeast(final T expectedObject, final Collection<? extends K> found,
            final EqualsChecker<T, K> equalsChecker) {

        for (K foundObject : found) {
            if (equalsChecker.equals(expectedObject, foundObject)) {
                return true;
            }
        }
        return false;
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
     * @param <T> the type of the expected object
     * @param <K> the type of the found objects
     */
    public static <T, K> void containsAtLeast(final String message, final T expectedObject,
            final Collection<? extends K> found, final EqualsChecker<T, K> equalsChecker) {
        Check.notNullArgument(found, "found");
        Check.notNullArgument(equalsChecker, "equalsChecker");

        if (!isContainingAtLeast(expectedObject, found, equalsChecker)) {
            AssertUtil.failCompare(AssertUtil.format(message,
                    "[Assertion failed] - expected object not found in collection"), expectedObject, found);
        }
    }

    /**
     * Check that the elements of expects are element of found too (by a specific definition) elements.
     * The order doesn't matter.
     * {@code found} can have some more elements.
     *
     * @param expectedObject the expected object
     * @param found the found
     * @param equalsChecker the specific equals definition
     * @param <T> the type of the expected object
     * @param <K> the type of the found objects
     */
    public static <T, K> void containsAtLeast(final T expectedObject, final Collection<? extends K> found,
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
     * @param <T> the type of the expected object
     * @param <K> the type of the found objects
     */
    public static <T, K> void containsAtLeast(final String message, final Collection<? extends T> expected,
            final Collection<? extends K> found, final EqualsChecker<T, K> equalsChecker) {
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
     * @param <T> the type of the expected object
     * @param <K> the type of the found objects
     */
    public static <T, K> void containsAtLeast(final Collection<? extends T> expected,
            final Collection<? extends K> found, final EqualsChecker<T, K> equalsChecker) {
        AssertUtil.containsAtLeast(null, expected, found, equalsChecker);
    }

    /**
     * Assert that the collection contains the item.
     * The Collection can have other items too.
     *
     * @param expectedItem the expected item
     * @param found the found
     * @param message additional message for the failure description when the check fails
     * @param <T> The type of expected and found objects
     */
    public static <T> void containsAtLeast(final String message, final T expectedItem,
            final Collection<? extends T> found) {
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
     * @param <T> The type of expected and found objects
     */
    public static <T> void containsAtLeast(final T expectedItem, final Collection<? extends T> found) {
        AssertUtil.containsAtLeast(null, expectedItem, found);
    }

    /**
     * Assert that the collection contains the expected items.
     * The Collection can have other items too.
     *
     * @param found the found
     * @param expectedItems the expected items
     * @param message additional message for the failure description when the check fails
     * @param <T> The type of expected and found objects
     */
    public static <T> void containsAtLeast(final String message, final Collection<? extends T> expectedItems,
            final Collection<? extends T> found) {
        Check.notNullArgument(expectedItems, "expectedItems");
        Check.notNullArgument(found, "found");

        containsAtLeast(message, expectedItems, found, NativeEqualsChecker.<T> getInstance());
    }

    /**
     * Assert that the collection contains the expected items.
     * The Collection can have other items too.
     *
     * @param found the found
     * @param expectedItems the expected items
     * @param <T> The type of expected and found objects
     */
    public static <T> void containsAtLeast(final Collection<? extends T> expectedItems,
            final Collection<? extends T> found) {
        Check.notNullArgument(expectedItems, "expectedItems");
        Check.notNullArgument(found, "found");

        containsAtLeast(null, expectedItems, found);
    }

    /**
     * Assert that the collection contains the expected item.
     * The Collection can have other items too.
     * This method is an alias for {@link #containsAtLeast(String, Object, Collection)}.
     *
     * @param expectedItem the expected item
     * @param found the found
     * @param message additional message for the failure description when the check fails
     * @param <T> The type of expected and found objects
     * @see AssertUtil#containsAtLeast(String Object, Collection)
     */
    public static <T> void contains(final String message, final T expectedItem, final Collection<? extends T> found) {
        containsAtLeast(message, expectedItem, found);
    }

    /**
     * Assert that the collection contains the expected item.
     * The Collection can have other items too.
     * This method is an alias for {@link #containsAtLeast(Object, Collection)}.
     *
     * @param expectedItem the expected item
     * @param found the found
     * @param <T> The type of expected and found objects
     * @see AssertUtil#containsAtLeast(Object, Collection)
     */
    public static <T> void contains(final T expectedItem, final Collection<? extends T> found) {
        containsAtLeast(expectedItem, found);
    }

    /**
     * Assert that the collection contains the expected items.
     * The Collection can have other items too.
     * This method is an alias for {@link #containsAtLeast(String, Collection, Collection)}.
     *
     * @param found the found
     * @param expectedItems the expected items
     * @param message additional message for the failure description when the check fails
     * @param <T> The type of expected and found objects
     * @see AssertUtil#containsAtLeast(String, Collection, Collection)
     */
    public static <T> void contains(final String message, final Collection<? extends T> expectedItems,
            final Collection<? extends T> found) {
        containsAtLeast(message, expectedItems, found);
    }

    /**
     * Assert that the collection contains the expected items.
     * The Collection can have other items too.
     * This method is an alias for {@link #containsAtLeast(Collection, Collection)}.
     *
     * @param found the found
     * @param expectedItems the expected items
     * @param <T> The type of expected and found objects
     * @see AssertUtil#containsAtLeast(Collection, Collection)
     */
    public static <T> void contains(final Collection<? extends T> expectedItems, final Collection<? extends T> found) {
        containsAtLeast(expectedItems, found);
    }

    /**
     * Assert that the collection does not contains the item.
     * The Collection can have other items.
     *
     * @param notExpectedItem the not expected item
     * @param found the found
     * @param message additional message for the failure description when the check fails
     * @param equalsChecker the specific equals definition     * 
     * @param <T> The type of expected and found objects
     * @param <K> the type of the found objects
     */
    public static <T, K> void containsNot(final String message, final T notExpectedItem,
            final Collection<? extends K> found, final EqualsChecker<T, K> equalsChecker) {
        Check.notNullArgument(found, "found");

        if (isContainingAtLeast(notExpectedItem, found, equalsChecker)) {
            AssertUtil.fail(AssertUtil.format(message, "[Assertion failed] - colection + " + found
                    + " does contain the not expected item " + notExpectedItem));
        }
    }

    /**
     * Assert that the collection does not contains the item.
     * The Collection can have other items.
     *
     * @param notExpectedItem the not expected item
     * @param found the found
     * @param equalsChecker the specific equals definition     * 
     * @param <T> The type of expected and found objects
     * @param <K> the type of the found objects
     */
    public static <T, K> void containsNot(final T notExpectedItem, final Collection<? extends K> found,
            final EqualsChecker<T, K> equalsChecker) {
        Check.notNullArgument(found, "found");

        containsNot(null, notExpectedItem, found, equalsChecker);
    }

    /**
     * Assert that the collection does not contains the item.
     * The Collection can have other items.
     *
     * @param notExpectedItem the not expected item
     * @param found the found
     * @param message additional message for the failure description when the check fails
     * @param equalsChecker the specific equals definition     * 
     * @param <T> The type of expected and found objects
     * @param <K> the type of the found objects
     */
    public static <T, K> void containsNot(final String message, final Collection<? extends T> notExpectedItems,
            final Collection<? extends K> found, final EqualsChecker<T, K> equalsChecker) {
        Check.notNullArgument(found, "found");

        for (T notExpectedItem : notExpectedItems) {
            containsNot(message, notExpectedItem, found, equalsChecker);
        }
    }

    /**
     * Assert that the collection does not contains the item.
     * The Collection can have other items.
     *
     * @param notExpectedItem the not expected item
     * @param found the found
     * @param message additional message for the failure description when the check fails
     * @param equalsChecker the specific equals definition     * 
     * @param <T> The type of expected and found objects
     * @param <K> the type of the found objects
     */
    public static <T, K> void containsNot(final Collection<? extends T> notExpectedItems,
            final Collection<? extends K> found, final EqualsChecker<T, K> equalsChecker) {
        Check.notNullArgument(found, "found");

        containsNot(null, notExpectedItems, found, equalsChecker);
    }

    /**
     * Assert that the collection does not contains the item.
     * The Collection can have other items.
     *
     * @param notExpectedItem the not expected item
     * @param found the found
     * @param message additional message for the failure description when the check fails
     * @param <T> The type of expected and found objects
     */
    public static <T> void containsNot(final String message, final T notExpectedItem,
            final Collection<? extends T> found) {
        Check.notNullArgument(found, "found");

        if (found.contains(notExpectedItem)) {
            AssertUtil.fail(AssertUtil.format(message, "[Assertion failed] - colection + " + found
                    + " does contain the not expected item " + notExpectedItem));
        }
    }

    /**
     * Contains not.
     *
     * @param notExpectedItem the not expected item
     * @param found the found
     * @param <T> The type of expected and found objects
     */
    public static <T> void containsNot(final T notExpectedItem, final Set<? extends T> found) {
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

    /**
     * Check if both objects are equals.
     * To compare them reflection is used instead of the equals method,
     *
     * @param <T> the generic type
     * @param message the message
     * @param expected the expected
     * @param actual the actual
     */
    public static <T> void assertReflectivEquals(final String message, final T expected, final T actual) {
        Check.notNullArgument(actual, "actual");
        Check.notNullArgument(expected, "expected");

        if (!EqualsBuilder.reflectionEquals(expected, actual)) {
            AssertUtil.failCompare(AssertUtil.format(message, "[Assertion failed] - objects are not reflectiv equals"),
                    expected != null ? expected.toString() : "null",
                    actual != null ? actual.toString() : "null");

        }
    }

    /**
     * Check if both objects are equals.
     * To compare them reflection is used instead of the equals method,
     *
     * @param <T> the generic type
     * @param expected the expected
     * @param actual the actual
     */
    public static <T> void assertReflectivEquals(final T expected, final T actual) {
        assertReflectivEquals(null, expected, actual);
    }
}
