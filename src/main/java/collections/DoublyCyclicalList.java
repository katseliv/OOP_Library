package collections;

import java.util.*;

public class DoublyCyclicalList<T> implements ICyclicalList<T> {
    private Node head;
    private Node tail;
    private int size;

    protected class Node {
        private Node next;
        private Node prev;
        private T value;

        Node(Node next, Node prev, T value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }

        Node(Node next, T value) {
            this(next, null, value);
        }

        Node(T value) {
            this(null, null, value);
        }

        public Node getNext() {
            return next;
        }

        public Node getPrev() {
            return prev;
        }

        public T getValue() {
            return value;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

    @SafeVarargs
    public DoublyCyclicalList(T... values) {
        addAll(values);
    }

    DoublyCyclicalList(Collection<? extends T> list) {
        addAll(list);
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    @Override
    public int size() {
        return size;
    }

    protected void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object object) {
        return indexOf(object) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node current = head;
            private int count = 0;

            @Override
            public boolean hasNext() {
                return count < size;
//                return current.next != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("Elements not found...");
                }
                T value = current.getValue();
                current = current.getNext();
                this.count++;
                return value;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] values = new Object[this.size];
        if (size() > 0) {
            values[0] = this.head.getValue();
            int count = 1;
            for (Node node = this.head.getNext(); node != this.head; node = node.getNext()) {
                values[count++] = node.getValue();
            }
        }
        return values;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public String toString() {
        return Arrays.toString(this.toArray());
    }

    @Override
    public boolean add(T value) {
        addLast(value);
        return true;
    }

    @Override
    public void add(int index, T element) {
        if (element != null) {
            if (index != 0) {
                checkIndex(index);
            }
            Node node = getNodeByIndex(index);
            if (node == null || node == this.head) {
                addFirst(element);
            } else if (node == this.tail) {
                addLast(element);
            } else {
                Node addNode = new Node(node, node.prev, element);
                node.prev = node.prev.next = addNode;
                this.size++;
            }
        }
    }

    public void checkIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new IndexOutOfBoundsException("Incorrect index output...");
        }
    }

    public Node getNodeByIndex(int index) {
        Node result = null;
        if (index == 0) {
            result = this.head;
        } else if (index == this.size - 1) {
            result = this.tail;
        } else {
            if (this.size / 2 > index) {
                for (Node node = this.head.getNext(); node != this.head; node = node.getNext()) {
                    if (--index == 0) {
                        result = node;
                        break;
                    }
                }
            } else {
                index = this.size - index - 1;
                for (Node node = this.tail.getPrev(); node != this.tail; node = node.getPrev()) {
                    if (--index == 0) {
                        result = node;
                        break;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public void addLast(T value) {
        if (value != null) {
            if (this.head == null) {
                addFirst(value);
            } else {
                Node node = new Node(this.head, this.tail, value);
                this.head.prev = this.tail.next = node;
                this.tail = node;
                this.size++;
            }
        }
    }

    @Override
    public void addFirst(T value) {
        if (value != null) {
            if (this.head == null) {
                this.head = new Node(value);
                this.head.prev = this.head.next = this.head;
                this.tail = this.head;
            } else {
                Node node = new Node(this.head, this.tail, value);
                this.tail.next = this.head.prev = node;
                this.head = node;
            }
            this.size++;
        }
    }

    @Override
    public boolean remove(Object object) {
        boolean result;
        if (result = object != null) {
            if (result = !isEmpty()) {
                if (result = this.head.getValue().equals(object)) {
                    removeFirst();
                } else {
                    Node delete = null;
                    for (Node node = this.head.getNext(); node != this.head; node = node.getNext()) {
                        if (node.getValue().equals(object)) {
                            delete = node;
                        }
                    }
                    if (result = delete != null) {
                        if (delete == this.tail) {
                            removeLast();
                        } else {
                            delete.prev.next = delete.getNext();
                            delete.getNext().prev = delete.prev;
                            this.size--;
                        }
                    }
                }
            }
        }
        return result;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T old = null;
        if (index == 0) {
            old = this.head.getValue();
            removeFirst();
        } else if (index == this.size - 1) {
            old = this.tail.getValue();
            removeLast();
        } else {
            Node node = getNodeByIndex(index);
            if (node != null) {
                old = node.getValue();
                node.prev.next = node.getNext();
                node.getNext().prev = node.prev;
                this.size--;
            }
        }
        return old;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("Element not found...");
        }
        T oldElement;
        if (this.size == 1) {
            oldElement = this.head.getValue();
            clear();
        } else {
            oldElement = this.head.getValue();
            Node newFirst = this.head.getNext();
            newFirst.prev = this.tail;
            this.tail.next = newFirst;
            this.head = newFirst;
            this.size--;
        }
        return oldElement;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Element not found...");
        }
        T oldElement;
        if (this.size == 1) {
            oldElement = this.head.getValue();
            clear();
        } else {
            oldElement = this.tail.getValue();
            Node newLast = this.tail.getPrev();
            newLast.next = this.head;
            this.head.prev = newLast;
            this.tail = newLast;
            this.size--;
        }
        return oldElement;
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        for (Object o : collection) {
            boolean result = contains(o);
            if (!result) {
                return false;
            }
        }
        return true;
    }

    @SafeVarargs
    @Override
    public final void addAll(T... values) {
        boolean result;
        if (result = values != null) {
            for (T value : values) {
                result = result && add(value);
            }
        }
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        boolean result;
        if (result = collection != null && !collection.isEmpty()) {
            for (T value : collection) {
                result = result && add(value);
            }
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        if (collection.isEmpty()) {
            return false;
        }

        for (Object o : collection) {
            remove(o);
        }

        return true;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        return getNodeByIndex(index).getValue();
    }

    @Override
    public void set(int index, T element) {
        T result = null;
        if (element != null) {
            checkIndex(index);
            Node set = getNodeByIndex(index);
            result = set.getValue();
            set.value = element;
        }
    }

    @Override
    public int indexOf(Object object) {
        int index = -1;
        if (object != null && !isEmpty()) {
            if (this.head.getValue().equals(object)) {
                index = 0;
            } else {
                int count = 1;
                for (Node node = this.head.getNext(); node != this.head; node = node.getNext()) {
                    if (node.getValue().equals(object)) {
                        index = count;
                        break;
                    } else {
                        count++;
                    }
                }
            }
        }
        return index;
    }

    @Override
    public int lastIndexOf(Object object) {
        int index = -1;
        if (!isEmpty()) {
            if (this.tail.getValue().equals(object)) {
                index = this.size - 1;
            } else {
                int count = this.size - 2;
                for (Node node = this.tail.getPrev(); node != this.tail; node = node.getPrev()) {
                    if (node.getValue().equals(object)) {
                        index = count;
                        break;
                    } else {
                        count--;
                    }
                }
            }
        }
        return index;
    }

}
