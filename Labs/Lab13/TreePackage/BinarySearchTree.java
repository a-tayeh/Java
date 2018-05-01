
package TreePackage;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

public class BinarySearchTree<T extends Comparable<? super T>> extends
        BinaryTree<T> implements SearchTreeInterface<T> {

    BinarySearchTree() {
        super();
    }

    BinarySearchTree(T rootData) {
        super(rootData);
    }

    public void setTree(T rootData) {
        throw new UnsupportedOperationException();
    }

    public void setTree(T rootData, TreePackage.BinaryTreeInterface<T> leftTree,
            TreePackage.BinaryTreeInterface<T> rightTree) {
        throw new UnsupportedOperationException();
    }

    private T findEntry(TreePackage.BinaryNode<T> rootNode, T entry) {
            T result = null;
        if (rootNode.equals(null)) {
            return null;
        } else if (entry.compareTo(rootNode.getData()) == 0) {
            result = rootNode.getData();
        } else if (entry.compareTo(rootNode.getData()) < 0) {
                 result = addEntry(rootNode.getLeftChild(), entry);

        }
        else {

                result = addEntry(rootNode.getRightChild(), entry);
        }
        return result;

    }


    public T getEntry(T entry) {
        return findEntry(getRootNode(), entry);
    }

    public boolean contains(T entry) {
        return getEntry(entry) != null;
    }

    private T addEntry(BinaryNode<T> rootNode, T newEntry) {
        T result = null;
        if (newEntry.compareTo(rootNode.getData()) == 0) {
            result = rootNode.getData();
            rootNode = new BinaryNode<T>(newEntry);
        } else if (newEntry.compareTo(rootNode.getData()) < 0) {
            if (rootNode.hasLeftChild())
            {
                result = addEntry(rootNode.getLeftChild(), newEntry);
            } else {
                rootNode.setLeftChild((new BinaryNode<T>(newEntry)));

            }
        } else {
            if (rootNode.hasRightChild()) {
                result = addEntry(rootNode.getRightChild(), newEntry);
            } else {
                rootNode.setRightChild(new BinaryNode<T>(newEntry));
            }
        }

        return result;

    }

    public T add(T newEntry) {
        T result = null;
        if (isEmpty()) {
            setRootNode(new BinaryNode<>(newEntry));
        } else {
            result = addEntry(getRootNode(), newEntry);
        }
        return result;
    }

    public T remove(T entry) {
        ReturnObject oldEntry = new ReturnObject(null);
        BinaryNode<T> newRoot = removeEntry(getRootNode(), entry, oldEntry);
        setRootNode(newRoot);
        return oldEntry.get();
    }
// An object input parameter used with the removeEntry method that allows // for the return of the element stored in the removed node.

    private class ReturnObject {

        private T item;

        private ReturnObject(T entry) {
            item = entry;
        }

        public T get() {
            return item;
        }

        public void set(T entry) {
            item = entry;
        }
    }

    private BinaryNode<T> removeEntry(BinaryNode<T> rootNode, T entry,
            ReturnObject oldEntry) {
        if (rootNode != null) {
            T rootData = rootNode.getData();
            int comparison = entry.compareTo(rootData);
            if (comparison == 0) { // entry == root entry
                oldEntry.set(rootData);
                rootNode = removeFromRoot(rootNode);
            } else if (comparison < 0) // entry < root entry
            {
                BinaryNode<T> leftChild = rootNode.getLeftChild();
                BinaryNode<T> subtreeRoot = removeEntry(leftChild, entry, oldEntry);
                rootNode.setLeftChild(subtreeRoot);
            } else { // entry > root entry
                BinaryNode<T> rightChild = rootNode.getRightChild();
                rootNode.setRightChild(removeEntry(rightChild, entry, oldEntry));
            }
        }
        return rootNode;

    }

    //
//
//
    private BinaryNode<T> removeFromRoot(BinaryNode<T> rootNode) {

        if (rootNode.hasLeftChild() && rootNode.hasRightChild()) {
            BinaryNode<T> leftSubtreeRoot = rootNode.getLeftChild();
            BinaryNode<T> largestNode = findLargest(leftSubtreeRoot);
            rootNode.setData(largestNode.getData());
            rootNode.setLeftChild(removeLargest(leftSubtreeRoot));
        } //
        else if (rootNode.hasRightChild()) //Case 2: rootNode has at most one child
        {
            rootNode = rootNode.getRightChild();
        } else {
            rootNode = rootNode.getLeftChild();
        }
        return rootNode;
    }

    private BinaryNode<T> findLargest(BinaryNode<T> rootNode) {
// TODO: complete method implementation

        if (rootNode.hasRightChild()) {
            rootNode = findLargest(rootNode.getRightChild());
        }
        return rootNode;
    }
    //Returns: The node containing the largest entry in the
    //tree.
    //tree. tree.
    private BinaryNode<T> removeLargest(BinaryNode<T> rootNode) //Finds the node containing the largest entry in a given tree. Parameter: rootNode A reference to the root node of the tree.
    {
        if (rootNode.hasRightChild()) {
            BinaryNode<T> rightChild = rootNode.getRightChild();
            rightChild = removeLargest(rightChild);
            rootNode.setRightChild(rightChild);
        } else {
            rootNode = rootNode.getLeftChild();
        }
        return rootNode;
    }
}
