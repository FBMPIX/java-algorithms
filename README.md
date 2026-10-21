# Java Algorithms

Java implementations of sorting, searching, tree traversal, and graph algorithms. These are independent solutions originally developed during algorithm practice; this portfolio repo is separate from my private study tracker.

## Implementations

| Module | Focus |
| --- | --- |
| [Insertion Sort](insertion-sort/src/main/java/com/interviewprep/dsa/sort/InsertionSort.java) | In-place iterative sorting |
| [Binary Search](binary-search/src/main/java/com/interviewprep/dsa/search/BinarySearch.java) | Searching sorted arrays |
| [Binary Tree Traversal](binary-tree-traversal/src/main/java/com/interviewprep/dsa/tree/BinaryTreeTraversal.java) | Recursive preorder, inorder, and postorder traversals |
| [Breadth-First Search](breadth-first-search/src/main/java/com/interviewprep/dsa/graph/BreadthFirstSearch.java) | Queue-based graph traversal, reachability, shortest paths |
| [Depth-First Search](depth-first-search/src/main/java/com/interviewprep/dsa/graph/DepthFirstSearch.java) | Recursive graph traversal and path detection |

## Build and test

Requires **JDK 17+** and **Apache Maven**.

```bash
mvn test
```

Individual exercise:

```bash
mvn -pl breadth-first-search test
```

## Authorship

- **Algorithm implementations:** Written by me as independent exercise solutions.
- **Automated JUnit tests and exercise scaffolding:** AI-generated. I did **not** write the automated test suites.
- Examples are educational and should not be confused with production libraries.
- No bundled third-party textbooks, private progress tracking, or personal review notes are included.
