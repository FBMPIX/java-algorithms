package com.interviewprep.dsa.tree;

import java.util.ArrayList;
import java.util.List;

public final class BinaryTreeTraversal {
    private BinaryTreeTraversal() {
    }

    public static final class Node<T> {
        private final T value;
        private Node<T> left;
        private Node<T> right;

        public Node(T value) {
            if (value == null) {
                throw new IllegalArgumentException("value cannot be null");
            }
            this.value = value;
        }

        public T value() {
            return value;
        }

        public Node<T> left() {
            return left;
        }

        public Node<T> right() {
            return right;
        }

        public Node<T> setLeft(Node<T> left) {
            this.left = left;
            return this;
        }

        public Node<T> setRight(Node<T> right) {
            this.right = right;
            return this;
        }
    }

    public static <T> List<T> preorder(Node<T> root) {
        List<T> list = new ArrayList<>();
        preorderHelper(root, list);
        return list;
    }

    private static <T> void preorderHelper(Node<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        list.add(node.value());
        preorderHelper(node.left(), list);
        preorderHelper(node.right(), list);
    }

    public static <T> List<T> inorder(Node<T> root) {
        List<T> list = new ArrayList<>();
        inorderHelper(root, list);
        return list;
    }

    private static <T> void inorderHelper(Node<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        inorderHelper(node.left(), list);
        list.add(node.value());
        inorderHelper(node.right(), list);
    }

    public static <T> List<T> postorder(Node<T> root) {
        List<T> list = new ArrayList<>();
        postorderHelper(root, list);
        return list;
    }

    private static <T> void postorderHelper(Node<T> node, List<T> list) {
        if (node == null) {
            return;
        }
        postorderHelper(node.left(), list);
        postorderHelper(node.right(), list);
        list.add(node.value());
    }
}
