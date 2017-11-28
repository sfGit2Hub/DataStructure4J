package org.data.stucture;

/**
 * Created by SF on 2017/11/26.
 * 一个简单的平衡二叉树
 * Adelson-Velskii and Landis
 */
public class AVLTree<T extends Comparable> {
    private static class AVLTreeNode<T> {
        private T element;
        private AVLTreeNode<T> left;
        private AVLTreeNode<T> right;
        private int height;

        public AVLTreeNode(T element, AVLTreeNode<T> left, AVLTreeNode<T> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }

        public AVLTreeNode(T element) {
            this(element, null, null);
        }
    }

    private AVLTreeNode<T> root;
//    AVL 树每个节点的左子树和右子树的高度最多相差1
    private static final int ALLOWED_IMBALANCE = 1;

    private int height(AVLTreeNode<T> node) {
        return node == null ? -1 : node.height;
    }

    public T insert(T ele) {
        root =  insert(ele, root);
        return root.element;
    }

    public T remove(T ele) {
        return remove(ele, root).element;
    }

    private AVLTreeNode<T> remove(T ele, AVLTreeNode<T> node) {
        if (node == null) {
            return node;
        }
        int compareResult = ele.compareTo(node.element);

        if (compareResult < 0) {
            node.left = remove(ele, node.left);
        } else if (compareResult > 0) {
            node.right = remove(ele, node.right);
        } else {
            node = (node.left != null) ? node.left : node.right;
        }
        return balance(node);
    }

    public boolean isEmpty(){
        return root == null;
    }

    public void printTree() {
        if (isEmpty()) {
            System.out.println("Empty tree");
        } else {
            printTree(root);
        }
    }

    /**
     * 中序遍历
     * @param node
     */
    private void printTree(AVLTreeNode<T> node) {
        if (node != null) {
            printTree(node.left);
            System.out.println(node.element);
            printTree(node.right);
        }
    }

    /**
     * 插入子树
     * @param ele
     * @param node
     * @return  返回一此子树的根节点
     */
    private AVLTreeNode<T> insert(T ele, AVLTreeNode<T> node) {
        if (node == null) {
            return new AVLTreeNode<T>(ele, null, null);
        }

        int compareResult = ele.compareTo(node.element);

        if (compareResult < 0) {
            node.left = insert(ele, node.left);
        } else if (compareResult > 0) {
            node.right = insert(ele, node.right);
        } else {
            // TODO
//            暂时不存储重复数据
        }
        return balance(node);
    }

    private AVLTreeNode<T> balance(AVLTreeNode<T> node) {
        if (node == null)
            return node;
        if (height(node.left) - height(node.right) > ALLOWED_IMBALANCE) {
            if (height(node.left.left) >= height(node.left.right))
                node = rotateWithLeftChild(node);
            else
                node = doubleWithLeftChild(node);
        } else if (height(node.right) - height(node.left) > ALLOWED_IMBALANCE) {
            if (height(node.right.right) >= height(node.right.left)) {
                node = rotateWithRightChild(node);
            } else {
                node = doubleWithRightChild(node);
            }
        }
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    private AVLTreeNode<T> doubleWithRightChild(AVLTreeNode<T> k3) {
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }

    private AVLTreeNode<T> rotateWithRightChild(AVLTreeNode<T> k2) {
        AVLTreeNode<T> k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(k2.height, height(k1.right)) + 1;
        return k1;
    }


    private AVLTreeNode<T> doubleWithLeftChild(AVLTreeNode<T> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     * 旋转二叉树的左子树
     * 对于AVL树，这是一种单旋情况
     * 先更新节点的高度height,然后返回
     * 其中k1, k2根据公式命名
     * @param k2
     * @return
     */
    private AVLTreeNode<T> rotateWithLeftChild(AVLTreeNode<T> k2) {
        AVLTreeNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }


}
