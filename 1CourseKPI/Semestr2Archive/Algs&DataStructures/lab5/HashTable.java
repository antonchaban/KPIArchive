package ua.kpi.fict.acts.it03.asdlabs.lab5;

import java.util.Arrays;

public class HashTable {
    private int TABLE_SIZE;
    private int size;
    private HashEntry[] table;

    public HashTable(int TABLE_SIZE) {
        this.TABLE_SIZE = TABLE_SIZE;
        this.size = 0;
        this.table = new HashEntry[TABLE_SIZE];
    }

    private class HashEntry {
        @Override
        public String toString() {
            return "HashEntry{" +
                    "key=" + key +
                    ", value=" + value +
                    ", next=" + next +
                    '}';
        }

        Integer key, value;
        HashEntry next;

        HashEntry(Integer key, Integer value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

    public int getSize() {
        return size;
    }

    public void clear() {
        size = 0;
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    }

    public Integer get(Integer key) {
        int hash = key.hashCode() % TABLE_SIZE;
        HashEntry curr = table[hash];
        while (curr != null) {
            if (curr.key == key)
                return curr.value;
            curr = curr.next;
        }
        return null;
    }

    public void insert(Integer key, Integer value) {
        int hash = key.hashCode() % TABLE_SIZE;
        remove(key);
        HashEntry next = table[hash];
        table[hash] = new HashEntry(key, value);
        table[hash].next = next;
    }

    @Override
    public String toString() {
        return "HashTable{" +
                "TABLE_SIZE=" + TABLE_SIZE +
                ", size=" + size +
                ", table=" + Arrays.toString(table) +
                '}';
    }

    public Integer remove(Integer key) {
        int hash = key.hashCode() % TABLE_SIZE;
        HashEntry curr = table[hash];
        if (curr == null)
            return null;
        else if (curr.key == key) {
            table[hash] = null;
            return curr.value;
        }
        while (curr.next != null) {
            if (curr.next.key == key) {
                Integer ret = curr.next.value;
                curr.next = curr.next.next;
                return ret;
            }
            curr = curr.next;
        }
        return null;
    }
}
