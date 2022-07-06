package ua.kpi.fict.acts.it03.asdlabs.lab1.doublelinked;

import ua.kpi.fict.acts.it03.asdlabs.lab1.ListsInterface;

import java.util.NoSuchElementException;

public class List implements ListsInterface {
    private Node head;
    private Node tail;

    public List()
    {
        head = null;
        tail = null;
    }

    private boolean isEmpty()
    {
        return head == null;
    }

    // ##### ADDING #####

    @Override
    public void addFirst(int data){
        Node temp = new Node(data);

        if (isEmpty())
        {
            tail = temp;
        }
        else head.prev = temp;

        temp.next = head;
        head = temp;
    }

    @Override
    public void addLast(int data)
    {
        Node temp = new Node(data);
        if (isEmpty())
        {
            head = temp;
        }
        else
            tail.next = temp;
        temp.prev = tail;
        tail = temp;
    }

    @Override
    public void addByIdx(int data, int index)
    {
        Node current = head;
        int counter = 0;
        while (current != null && counter != index)
        {
            current = current.next;
            counter++;
        }

        Node temp = new Node(data);

        current.prev.next = temp;
        temp.prev = current.prev;
        current.prev = temp;
        temp.next = current;
    }

    // ##### PRINTING #####

    @Override
    public void print()
    {
        Node temp = head;
        while (temp!=null)
        {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // ##### DELETING #####

    @Override
    public void delFirst()
    {
        if (head == null)
            throw new NoSuchElementException();
        if (head.next == null)
            tail = null;
        else head.next.prev = null;
        head = head.next;
    }

    @Override
    public void delLast()
    {
        if (head == null)
            throw new NoSuchElementException();
        if (head.next == null)
            head = null;
        else tail.prev.next = null;
        tail = tail.prev;
    }

    @Override
    public void delAt(int index)
    {
        Node current = head;
        while (current.data != index){
            current = current.next;

            if (current==null)
                return;
        }

        if (current == head)
            delFirst();
        else current.prev.next = current.next;
        if (current == tail)
            delLast();
        else current.next.prev = current.prev;
    }

    // ##### REPLACING #####

    @Override
    public void replaceFirst(int data)
    {
        head.data = data;
    }

    @Override
    public void replaceLast(int obj)
    {
        Node a = new Node(obj);
        a.data = obj;
        tail.data = a.data;
        tail.prev.next = a;
    }

    @Override
    public void replaceAt(int position, int data){
        Node node = new Node(data);
        node.data = data;
        node.next = null;

        if (this.head == null) {
            if (position != 0) {
                return;
            } else {
                this.head = node;
            }
        }

        if (head != null && position == 0) {
            node.next = this.head;
            this.head = node;
            return;
        }

        Node current = this.head;
        Node previous = null;

        int i = 0;

        while (i < position) {
            previous = current;
            current = current.next;

            if (current == null) {
                break;
            }

            i++;
        }

        current.data = node.data;
        previous.next.data = current.data;
        current.prev = previous;
    }

    // ##### SUM OF ELEMENTS #####

    @Override
    public int getSum()
    {
        int result = head.data;
        Node t = head;
        while (t != null)
        {
            if (t.next == null)
                break;
            else
                result = result + t.next.data;
            t = t.next;
        }
        return result;
    }

    // ##### INDEX OF #####

    @Override
    public int indexOf(int obj) {
        int index = 0;
        Node current = this.head;
        while (current != null) {
            if (current.data == obj) {
                return index;
            }
            index++;
            current = current.next;
        }

        return -1;
    }
    // ##################################################
}
