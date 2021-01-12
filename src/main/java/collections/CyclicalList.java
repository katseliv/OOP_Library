package collections;

import java.util.Collection;
import java.util.NoSuchElementException;

public class CyclicalList<T> extends DoublyCyclicalList<T> {

    @SafeVarargs
    public CyclicalList(T... values) {
        addAll(values);
    }

    public CyclicalList(Collection<? extends T> list) {
        addAll(list);
    }

    @Override
    public void add(int index, T element) {
        if (element != null) {
            if (index != 0) {
                checkIndex(index);
            }
            Node node = getNodeByIndex(index);
            if (node == null || node == this.getHead()) {
                addFirst(element);
            } else if (node == this.getTail()) {
                addLast(element);
            } else {
                Node prev = getNodeByIndex(index - 1);
                Node addNode = new Node(node, element);
                prev.setNext(addNode);
                this.setSize(this.size() + 1);
            }
        }
    }

    @Override
    public void addFirst(T value) {
        if (value != null) {
            if (this.getHead() == null) {
                this.setHead(new Node(value));
                this.getHead().setNext(this.getHead());
                this.setTail(this.getHead());
            } else {
                Node node = new Node(this.getHead(), value);
                this.getTail().setNext(node);
                this.setHead(node);
            }
            this.setSize(this.size() + 1);
        }
    }

    @Override
    public void addLast(T value) {
        if (value != null) {
            if (this.getHead() == null) {
                addFirst(value);
            } else {
                Node node = new Node(this.getHead(), value);
                this.getTail().setNext(node);
                this.setTail(node);
                this.setSize(this.size() + 1);
            }
        }
    }

    @Override
    public boolean remove(Object object) {
        boolean result;
        if (result = object != null) {
            if (result = !isEmpty()) {
                if (result = this.getHead().getValue().equals(object)) { //super.head.value.equals(object)
                    removeFirst();
                } else {
                    Node delete = null;
                    for (Node node = this.getHead().getNext(); node != this.getHead(); node = node.getNext()) { //Node node = this.head.next; node != this.head; node = node.next
                        if (node.getValue().equals(object)) {
                            delete = node;
                        }
                    }
                    if (result = delete != null) {
                        if (delete == this.getTail()) {
                            removeLast();
                        } else {
                            delete.setValue(delete.getNext().getValue());
                            delete.setNext(delete.getNext().getNext());
                            this.setSize(this.size() - 1);
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
            old = this.getHead().getValue();
            removeFirst();
        } else if (index == this.size() - 1) {
            old = this.getTail().getValue();
            removeLast();
        } else {
            Node node = getNodeByIndex(index);
            if (node != null) {
                old = node.getValue();
                node.setValue(node.getNext().getValue());
                node.setNext(node.getNext().getNext());
                this.setSize(this.size() - 1);
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
        if (this.size() == 1) {
            oldElement = this.getHead().getValue();
            clear();
        } else {
            oldElement = this.getHead().getValue();
            Node newFirst = this.getHead().getNext();
            this.getTail().setNext(newFirst); // = newFirst;
            this.setHead(newFirst); // this.head = newFirst;
            super.setSize(super.size() - 1);
        }
        return oldElement;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("Element not found...");
        }
        T oldElement;
        if (this.size() == 1) {
            oldElement = this.getHead().getValue();
            clear();
        } else {
            oldElement = this.getTail().getValue();
            Node newLast = getNodeByIndex(this.size() - 2);
            newLast.setNext(this.getHead());
            this.setTail(newLast); //this.tail = getTail().getNext();
            this.setSize(this.size() - 1);
        }
        return oldElement;
    }

    @Override
    public Node getNodeByIndex(int index) {
        Node result = null;
        if (index == 0) {
            result = this.getHead();
        } else if (index == this.size() - 1) {
            result = this.getTail();
        } else {
            for (Node node = this.getHead().getNext(); node != this.getHead(); node = node.getNext()) {
                if (--index == 0) {
                    result = node;
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public int lastIndexOf(Object object) {
        int index = -1;
        if (!isEmpty()) {
            if (this.getTail().getValue().equals(object)) {
                index = 0;
            } else {
                int count = 0;
                for (Node node = this.getTail().getNext(); node != this.getTail(); node = node.getNext()) {
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
}
