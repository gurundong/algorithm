package com.grd.example;

public class MyList {
    public static void main(String[] args) throws Exception {
        Node node1 = new Node("grd");
        Node node2 = new Node("zjx");
        MyList list = new MyList();
        list.addNode(node1);
        list.addNode(node2);
        list.insertNodeByIndex(1,new Node("hhh"));
        list.print();
        list.delNodeByIndex(2);
        list.print();
        System.out.println("获取第一个数据："+list.get(1));
        System.out.println("获取第二个数据："+list.get(2));
        System.out.println("获取第一个节点Node："+list.getNode(1));
        System.out.println("获取第二个节点Node："+list.getNode(2));
    }

    public static final int STATUS_SUCCESS = 1;

    public static final int STATUS_FAIL = 0;

    // 链表头数据
    private Node head;

    /**
     * 获取指定index的node节点数据
     * @param index 指定index
     * @return 返回node.getData的数据
     */
    public Object get(int index) throws Exception {
        checkIndex(index);
        if(head == null)
        {
            throw new Exception("链表节点为0，没有数据");
        }
        // index为1读取head节点数据
        if(index == 1){
            return head.getData();
        }
        Node temp = head;
        int pointer = 0;
        // 链表不为空，则指针移到第一个node处
        pointer++;
        while (pointer <= length()){
            // 如果插入位置等于指针处
            if(index == pointer){
                return temp.getData();
            }
            // 移动指针
            temp = temp.getNext();
            pointer++;
        }
        return null;
    }

    /**
     * 获取指定index的node节点
     * @param index 指定index
     * @return 返回node.getData的数据
     */
    public Node getNode(int index) throws Exception {
        checkIndex(index);
        if(head == null)
        {
            throw new Exception("链表节点为0，没有数据");
        }
        // index为1读取head节点数据
        if(index == 1){
            return head;
        }
        Node temp = head;
        int pointer = 0;
        // 链表不为空，则指针移到第一个node处
        pointer++;
        while (pointer <= length()){
            // 如果插入位置等于指针处
            if(index == pointer){
                return temp;
            }
            // 移动指针
            temp = temp.getNext();
            pointer++;
        }
        return null;
    }

    // 增加操作，直接在链表后插入新增节点即可，将原本最后一个节点的next指向新的节点
    public int addNode(Node node){
        if(node == null){
            System.out.println("插入的node不能为null");
            return STATUS_FAIL;
        }
        Node temp = head;               // 移动的指针，头结点指针是链表指针的初始位置
        // 判断头节点是否为空
        if(temp == null){
            head = node;
            return STATUS_FAIL;
        }
        // 不断移动指针到栈尾
        while (temp.getNext() != null){
            temp = temp.getNext();
        }
        // 插入新node
        temp.setNext(node);
        return STATUS_SUCCESS;
    }

    /**
     * 插入node到指定位置后面,插入时必须有head头node
     * @param index 指定插入位置，指定后将指定位置node节点整体后移
     * @return 1正确0错误
     */
    public int insertNodeByIndex(int index,Node node) throws Exception {
        // 判断插入位置合不合法
        checkIndex(index);
        // 记录指针位置
        int pointer = 0;
        Node temp = head;
        if(temp == null){
            System.out.println("链表至少有一个node。");
            return STATUS_FAIL;
        }
        // 链表不为空，则指针移到第一个node处
        pointer++;
        while (pointer <= length()){
            // 如果插入位置等于指针处
            if(index == pointer){
                // 前一个节点  当前节点（index）  下一个节点
                // temp       node=temp.next   tempNext=node.next
                Node tempNext = temp.getNext();
                temp.setNext(node);
                node.setNext(tempNext);
                return STATUS_SUCCESS;
            }
            // 移动指针
            temp = temp.getNext();
            pointer++;
        }
        return STATUS_FAIL;
    }

    /**
     * 删除指定位置的node，index传1可连head node也删除，换head头
     * @param index 指定位置
     */
    public int delNodeByIndex(int index) throws Exception {
        // 判断插入位置合不合法
        checkIndex(index);
        // 记录指针位置
        int pointer = 0;
        Node temp = head;
        if(temp == null){
            System.out.println("链表至少有一个node。");
            return STATUS_FAIL;
        }
        // 链表不为空，则指针移到第一个node处
        pointer++;
        // 删head头逻辑
        if(index == 1){
            // 如果只有一个head头
            if(temp.getNext() == null){
                head = null;
            }else {
                // 如果Length>1,则换head头
                head = temp.getNext();
            }
        }else {
            while (pointer <= length()){
                // 如果插入位置等于指针处
                if(index == pointer+1){
                    // 前一个节点  当前节点（index）  下一个节点
                    // temp       temp.next        temp.next.next
                    Node tempNext = temp.getNext().getNext();
                    temp.setNext(tempNext);
                    return STATUS_SUCCESS;
                }
                // 移动指针
                temp = temp.getNext();
                pointer++;
            }
        }

        return STATUS_FAIL;
    }

    /**
     * 获取链表长度
     * @return 返回链表长度
     */
    public int length(){
        int length = 0;
        Node temp = head;
        // 判断头节点是否为空
        if(temp != null){
            // 如果head不为空，则说明长度为1
            length++;
            while (temp.getNext() != null){
                length++;
                temp = temp.getNext();
            }
        }
        return length;
    }

    /**
     * 打印链表
     */
    public void print(){
        if(head == null){
            System.out.println("[]");
        }
        int poniter = 0;
        StringBuilder s = new StringBuilder();
        Node temp = head;
        poniter++;
        System.out.println("第"+poniter+"个node的数据为"+head.getData());
        s.append("["+head.getData());
        while (temp.getNext() != null){
            poniter++;
            temp = temp.getNext();
            System.out.println("第"+poniter+"个node的数据为"+temp.getData());
            s.append(","+temp.getData());
        }
        s.append("]");
        System.out.println("node数据集为："+s.toString());
    }

    public void checkIndex(int index) throws Exception {
        // 判断插入位置合不合法
        if(index<1 || index>length()+1){
            System.out.println("插入的位置不合法。");
            throw new Exception("插入的位置不合法。");
        }
    }

}

class Node{
    // 存放节点数据
    private Object data;

    // 存放下一个节点
    private Node next;

    public Node(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
