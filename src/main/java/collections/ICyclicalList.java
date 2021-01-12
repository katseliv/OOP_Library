package collections;

import java.util.*;

public interface ICyclicalList<T> extends Collection<T>{

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list
     */
    int size();

    /**
     * Returns true if this list contains no elements.
     *
     * @return true if this list contains no elements
     */
    boolean isEmpty();

    /**
     * Returns true if this list contains the specified element.
     * @param object element whose presence in this list is to be tested
     * @return true if this list contains the specified element
     */
    boolean contains(Object object);

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return an iterator over the elements in this list in proper sequence
     */
    Iterator<T> iterator();

    /**
     * Returns an array containing all of the elements in this list in proper
     * sequence (from first to last element).
     * @return an array containing all of the elements in this list in proper
     *         sequence
     */
    Object[] toArray();

    // Modification Operations
    /**
     * Appends the specified element to the end of this list.
     * @param element element to be appended to this list
     * @return true (as specified by Collection)
     */
    boolean add(T element);

    /**
     * Appends the specified element to the end of this list.
     * @param value element to be appended to this list
     */
    void addLast(T value);

    /**
     * Appends the specified element to the beginning of this list.
     * @param value element to be appended to this list
     */
    void addFirst(T value);

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present (optional operation).
     * @param object element to be removed from this list, if present
     * @return true if this list contained the specified element
     */
    boolean remove(Object object);

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.
     * @return the element at the first position in this list
     */
    T removeFirst();

    /**
     * Removes the last occurrence of the specified element from this list,
     * if it is present.
     * @return the element at the last position in this list
     */
    T removeLast();

    // Bulk Modification Operations

    /**
     * Returns true if this list contains all of the elements of the
     * specified collection.
     * @param  collection collection to be checked for containment in this list
     * @return true if this list contains all of the elements of the
     *         specified collection
     */
    boolean containsAll(Collection<?> collection);

    void addAll(T... values);

    /**
     * Appends all of the elements in the specified collection to the end of
     * this list, in the order that they are returned by the specified
     * collection's iterator (optional operation).
     * @param collection collection containing elements to be added to this list
     * @return true if this list changed as a result of the call
     */
    boolean addAll(Collection<? extends T> collection);

    /**
     * Removes from this list all of its elements that are contained in the
     * specified collection (optional operation).
     * @param collection collection containing elements to be removed from this list
     * @return true if this list changed as a result of the call
     */
    boolean removeAll(Collection<?> collection);

    /**
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     */
    void clear();


    // Comparison and hashing
    /**
     * Compares the specified object with this list for equality.
     * @param o the object to be compared for equality with this list
     * @return true if the specified object is equal to this list
     */
    boolean equals(Object o);

    /**
     * Returns the hash code value for this list.
     * @return the hash code value for this list
     */
    int hashCode();


    // Positional Access Operations
    /**
     * Returns the element at the specified position in this list.
     * @param index index of the element to return
     * @return the element at the specified position in this list
     */
    T get(int index);

    /**
     * Replaces the element at the specified position in this list with the
     * specified element (optional operation).
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     */
    void set(int index, T element);

    /**
     * Inserts the specified element at the specified position in this list
     * (optional operation).
     * @param index index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    void add(int index, T element);

    /**
     * Removes the element at the specified position in this list (optional
     * operation).
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     */
    T remove(int index);


    // Search Operations
    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * @param object element to search for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
    int indexOf(Object object);

    /**
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * @param object element to search for
     * @return the index of the last occurrence of the specified element in
     *         this list, or -1 if this list does not contain the element
     */
    int lastIndexOf(Object object);

}
