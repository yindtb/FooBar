package binarysearchtree;

import structures.ListNode;
import binarysearchtree.BSTToSortedDoublyLinkedList.DoublyLinkedList;

/**
 * Created by xuanwang on 12/1/16.
 */
public class DoublyLinkedListToBalancedBinarySearchTreeBST {
    //convert (circular) doubly linkedlist to balanced bst, O(n) time, O(logn) space
    /**
     * @param head: The first node of linked list.
     * @return: a tree node
     */
    private BSTToSortedDoublyLinkedList.DoublyLinkedList curr;

    public DoublyLinkedList sortedListToBST(DoublyLinkedList head) {
        if (head == null) {
            return head;
        }
        // 1.if it's a circular list that tail connects to head
        // curr.prev.next = null;
        // curr.prev = null;
        // 2.if it's a circular list that tail connects to any node
        // ListNode tail = getTail(head);
        // tail.next.prev = null;
        // tail.next = null;
        // after cutting the cycle, we can count nodes and build trees
        curr = head;
        int size = getSize(head);
        return buildTree(size);
    }

    private int getSize(DoublyLinkedList head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    private DoublyLinkedList buildTree(int size) {
        if (size <= 0) {
            return null;
        }
        DoublyLinkedList left = buildTree(size / 2);
        DoublyLinkedList root = curr;//curr is the root of these two subtrees
        curr = curr.next;
        DoublyLinkedList right = buildTree(size - 1 - size / 2);
        root.prev = left;
        root.next = right;
        return root;
    }

    //this method is almost same as Linked List Cycle 2
    public ListNode getTail(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != slow) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        while (head != slow.next) {
            head = head.next;
            slow = slow.next;
        }
        return slow;//return the tail
    }


}
