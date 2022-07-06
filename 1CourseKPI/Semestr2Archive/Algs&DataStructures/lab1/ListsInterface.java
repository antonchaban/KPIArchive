package ua.kpi.fict.acts.it03.asdlabs.lab1;

public interface ListsInterface {
    void addLast(int object);
    void addFirst(int object);
    void addByIdx(int object, int index);
    void delAt(int index);
    void delFirst();
    void delLast();
    void replaceAt(int index, int newVal);
    void replaceFirst(int obj);
    void replaceLast(int obj);
    int indexOf(int obj);
    int getSum();
    void print();
}
