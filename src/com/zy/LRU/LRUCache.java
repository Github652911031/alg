package com.zy.LRU;

import java.util.Hashtable;

/**
 * lru算法的实现
 * 可以参考linkedHashMap
 * 主要思路就是一个hashmap加上双向链表，链表头部和尾部节点
 * 不记录数据
 *
 */
public class LRUCache {
    private Hashtable<String, DLinkedNode>
            cache = new Hashtable<String, DLinkedNode>();
    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        head.pre = null;

        tail = new DLinkedNode();
        tail.next = null;

        head.next = tail;
        tail.pre = head;
    }

    public Object get(String key) {
        DLinkedNode value = cache.get(key);
        if(value == null){
            return null;
        }
        moveToHead(value);
        return value.getValue();
    }

    public void set(String key, Object value){
        DLinkedNode node = cache.get(key);

        if(node == null){

            DLinkedNode newNode = new DLinkedNode();
            newNode.setKey(key);
            newNode.setValue(value);

            this.cache.put(key, newNode);
            this.addNode(newNode);

            ++count;

            if(count > capacity){
                // pop the tail
                DLinkedNode tail = this.popTail();
                this.cache.remove(tail.getKey());
                --count;
            }
        }else{
            // update the value.
            node.setValue(value);
            this.moveToHead(node);
        }
    }

    private void moveToHead(DLinkedNode value) {
        removeNode(value);
        addNode(value);
    }

    private void addNode(DLinkedNode value) {
        value.next = head.next;
        head.next = value;
        value.pre = head;
        value.next.pre = value;
    }



    /**
     * Remove an existing node from the linked list.
     */
    private void removeNode(DLinkedNode node){
        DLinkedNode pre = node.pre;
        DLinkedNode next = node.next;

        pre.next = next;
        next.pre = pre;
    }


    // pop the current tail.
    private DLinkedNode popTail(){
        DLinkedNode res = tail.pre;
        this.removeNode(res);
        return res;
    }


}
