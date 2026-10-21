package com.interviewprep.dsa.tree;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

class BinaryTreeTraversalTest {
    @Test
    void nullRootReturnsEmptyTraversals() {
        assertTrue(BinaryTreeTraversal.preorder(null).isEmpty());
        assertTrue(BinaryTreeTraversal.inorder(null).isEmpty());
        assertTrue(BinaryTreeTraversal.postorder(null).isEmpty());
    }

    @Test
    void nodeRejectsNullValue() {
        assertThrows(IllegalArgumentException.class, () -> new BinaryTreeTraversal.Node<String>(null));
    }

    @Test
    void traversesSingleNodeTree() {
        BinaryTreeTraversal.Node<String> root = new BinaryTreeTraversal.Node<>("A");

        assertEquals(List.of("A"), BinaryTreeTraversal.preorder(root));
        assertEquals(List.of("A"), BinaryTreeTraversal.inorder(root));
        assertEquals(List.of("A"), BinaryTreeTraversal.postorder(root));
    }

    @Test
    void traversesBalancedTreeInPreorder() {
        BinaryTreeTraversal.Node<String> root = sampleTree();

        assertEquals(List.of("A", "B", "D", "E", "C", "F", "G"), BinaryTreeTraversal.preorder(root));
    }

    @Test
    void traversesBalancedTreeInorder() {
        BinaryTreeTraversal.Node<String> root = sampleTree();

        assertEquals(List.of("D", "B", "E", "A", "F", "C", "G"), BinaryTreeTraversal.inorder(root));
    }

    @Test
    void traversesBalancedTreeInPostorder() {
        BinaryTreeTraversal.Node<String> root = sampleTree();

        assertEquals(List.of("D", "E", "B", "F", "G", "C", "A"), BinaryTreeTraversal.postorder(root));
    }

    @Test
    void traversesLeftHeavyTree() {
        BinaryTreeTraversal.Node<Integer> root = new BinaryTreeTraversal.Node<>(4);
        root.setLeft(new BinaryTreeTraversal.Node<>(3)
                .setLeft(new BinaryTreeTraversal.Node<>(2)
                        .setLeft(new BinaryTreeTraversal.Node<>(1))));

        assertEquals(List.of(4, 3, 2, 1), BinaryTreeTraversal.preorder(root));
        assertEquals(List.of(1, 2, 3, 4), BinaryTreeTraversal.inorder(root));
        assertEquals(List.of(1, 2, 3, 4), BinaryTreeTraversal.postorder(root));
    }

    @Test
    void traversesRightHeavyTree() {
        BinaryTreeTraversal.Node<Integer> root = new BinaryTreeTraversal.Node<>(1);
        root.setRight(new BinaryTreeTraversal.Node<>(2)
                .setRight(new BinaryTreeTraversal.Node<>(3)
                        .setRight(new BinaryTreeTraversal.Node<>(4))));

        assertEquals(List.of(1, 2, 3, 4), BinaryTreeTraversal.preorder(root));
        assertEquals(List.of(1, 2, 3, 4), BinaryTreeTraversal.inorder(root));
        assertEquals(List.of(4, 3, 2, 1), BinaryTreeTraversal.postorder(root));
    }

    @Test
    void preservesDuplicateValues() {
        BinaryTreeTraversal.Node<String> root = new BinaryTreeTraversal.Node<>("A");
        root.setLeft(new BinaryTreeTraversal.Node<>("A"));
        root.setRight(new BinaryTreeTraversal.Node<>("B").setLeft(new BinaryTreeTraversal.Node<>("A")));

        assertEquals(List.of("A", "A", "B", "A"), BinaryTreeTraversal.preorder(root));
        assertEquals(List.of("A", "A", "A", "B"), BinaryTreeTraversal.inorder(root));
    }

    @Test
    void traversalDoesNotMutateTreeLinks() {
        BinaryTreeTraversal.Node<String> root = sampleTree();
        BinaryTreeTraversal.Node<String> left = root.left();
        BinaryTreeTraversal.Node<String> right = root.right();
        BinaryTreeTraversal.Node<String> leftLeft = left.left();
        BinaryTreeTraversal.Node<String> rightRight = right.right();

        BinaryTreeTraversal.preorder(root);
        BinaryTreeTraversal.inorder(root);
        BinaryTreeTraversal.postorder(root);

        assertSame(left, root.left());
        assertSame(right, root.right());
        assertSame(leftLeft, root.left().left());
        assertSame(rightRight, root.right().right());
    }

    private static BinaryTreeTraversal.Node<String> sampleTree() {
        BinaryTreeTraversal.Node<String> root = new BinaryTreeTraversal.Node<>("A");
        BinaryTreeTraversal.Node<String> b = new BinaryTreeTraversal.Node<>("B");
        BinaryTreeTraversal.Node<String> c = new BinaryTreeTraversal.Node<>("C");
        BinaryTreeTraversal.Node<String> d = new BinaryTreeTraversal.Node<>("D");
        BinaryTreeTraversal.Node<String> e = new BinaryTreeTraversal.Node<>("E");
        BinaryTreeTraversal.Node<String> f = new BinaryTreeTraversal.Node<>("F");
        BinaryTreeTraversal.Node<String> g = new BinaryTreeTraversal.Node<>("G");

        root.setLeft(b).setRight(c);
        b.setLeft(d).setRight(e);
        c.setLeft(f).setRight(g);

        return root;
    }
}
