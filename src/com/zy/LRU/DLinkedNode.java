package com.zy.LRU;

public class DLinkedNode {
    private String key;
    private Object value;

    protected DLinkedNode pre;
    protected DLinkedNode next;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public DLinkedNode getPre() {
        return pre;
    }

    public void setPre(DLinkedNode pre) {
        this.pre = pre;
    }

    public DLinkedNode getNext() {
        return next;
    }

    public void setNext(DLinkedNode next) {
        this.next = next;
    }
}
