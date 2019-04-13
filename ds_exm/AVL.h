#ifndef AVL_H
#define AVL_H

#include <iostream>
#include <assert.h>
#include <stdlib.h>

#include "PardesExp.h"

using namespace std;

template<class T> class AVL {
private:
  struct Node {
    unsigned long id;
    T data;
    int height;
    Node *left;
    Node *right;
    Node *parent;
  };

  // variables
  Node *root;
  Node *smallest;
  int size = 0;

  // functions
  void removeNode(Node *node);
  void updateHeights(Node *node);
  void updateSmallest();
  int diff(Node *node);
  void balance(Node *node);
  void ll_rotation(Node *node);
  void rr_rotation(Node *node);
  void rl_rotation(Node *node);
  void lr_rotation(Node *node);
  Node *getNode(unsigned long id);
  void ParentPointTo(Node *child, Node *newChild);
  
  void printTree2(Node *ptr, int level);
  
  T* getSortedArray2(T* array, Node *node);
  void rm_all(Node *node);

  
public:
  AVL();
  virtual ~AVL();
  int GetSize();
  void insert(unsigned long id, T data);
  void remove(unsigned long id);
  T getByKey(unsigned long id);
  bool exists(unsigned long id);
  T *getSortedArray();
  T getSmallest();
  void printTree();
};

/********************************* Public Functions *******************************/

template<class T> AVL<T>::AVL() {
  root = NULL;
  smallest = NULL;
  size = 0;
}

template<class T> AVL<T>::~AVL() {
  rm_all(root);
  root = NULL;
  return;
}


template<class T> int AVL<T>::GetSize() {
  return this->size;
}

template<class T> void AVL<T>::insert(unsigned long id, T data) {
   if (exists(id)) {
     throw KeyAlreadyExist();
   }
  
  Node *new_node = new Node;
  new_node->height = 1;
  new_node->left = NULL;
  new_node->right = NULL;
  new_node->parent = NULL;
  new_node->id = id;
  new_node->data = data;
  if (root == NULL) {
    root = new_node;
    smallest = new_node;
    this->size++;
#ifdef DEBUG
    printTree();
#endif
    return;
  } else {
    
    Node *current = root;
    Node *parent = NULL;
    while ((current != NULL) && (current->id != id)) {
      parent = current;
      
      
      if (id <= current->id) {
        current = current->left;
      } else {  
        current = current->right;
      }
    }
    
    
    if (id <= parent->id) {
     
      parent->left = new_node;
    } else {

      parent->right = new_node;
    }
    new_node->parent = parent;
    
    updateHeights(new_node);
    this->balance(new_node);
    this->size++;
    
  }
#ifdef DEBUG
  printTree();
#endif
  return;
}

template<class T> void AVL<T>::remove(unsigned long id) {
  Node *node = getNode(id);
  if (node)
    removeNode(node);
#ifdef DEBUG
  printTree();
#endif
  return;
}

template<class T> T AVL<T>::getByKey(unsigned long id) {
  Node *node = getNode(id);
  if (!node)
    throw KeyDoesNotExist();
  return node->data;
}

template<class T> bool AVL<T>::exists(unsigned long id) {
  return (getNode(id) != NULL);
}

template<class T> T *AVL<T>::getSortedArray() {
  int size = this->size;
  T *result = new T[size];
  getSortedArray2(result, root); 
  return result;
}

template<class T> T AVL<T>::getSmallest() {
  if (smallest)
    return this->smallest->data;
  throw KeyDoesNotExist();
}


template<class T> void AVL<T>::printTree() {
  printTree2(root, 1);
  cout << endl;
}


/*
                     Private Functions
*/
template<class T> void AVL<T>::removeNode(Node *node) {
  if (!(node->left) && !(node->right)) {
    ParentPointTo(node, NULL);
    if (node->parent) {
      updateHeights(node->parent);
      this->balance(node->parent);
    } else {
      this->smallest = NULL;
    }
    this->size--;
    delete node;
#ifdef DEBUG
    cout << "No childs" << endl;
    printTree();
#endif
    return;
  } else if (!(node->left) && (node->right)) {
    ParentPointTo(node, node->right);
    node->right->parent = node->parent;

    if (node->parent) {
      updateHeights(node->parent);
      this->balance(node->parent);
    } else {
      updateSmallest();
    }
    delete node;
    this->size--;
    
#ifdef DEBUG
    cout << "Right exists" << endl;
    printTree();
#endif
    return;
  } else if ((node->left) && !(node->right)) {
    ParentPointTo(node, node->left);
    node->left->parent = node->parent;

    if (node->parent) {
      updateHeights(node->parent);
      this->balance(node->parent);
    } else {
      updateSmallest();
    }
    delete node;
    this->size--;
#ifdef DEBUG
    cout << "Left exists" << endl;
    printTree();
#endif
    return;
  }  else {
    // find new root for subtree
    Node *current = node->right;
    while (current->left)
      current = current->left;

    // switch current and node.
    int backupK = current->id;
    current->id = node->id;
    node->id = backupK;

    T backupT = current->data;
    current->data = node->data;
    node->data = backupT;

    removeNode(current);
#ifdef DEBUG
    printTree();
#endif
  }
}

template<class T> void AVL<T>::updateHeights(Node *node) {
  Node *current = node;
  while (current != NULL) {
    int leftHeight = 0, rightHeight = 0;
    if (current->left) {
      leftHeight = current->left->height;
    }
    if (current->right) {
      rightHeight = current->right->height;
    }
    current->height =
        ((leftHeight > rightHeight) ? leftHeight : rightHeight) + 1;
    current = current->parent;
  }
}

template<class T> void AVL<T>::updateSmallest() {
  Node *current = root;
  if (!current) {
    this->smallest = NULL;
    return;  // empty tree
  }
  while (current->left != NULL) {
    current = current->left;
  }
  this->smallest = current;
}

template<class T> int AVL<T>::diff(Node *node) {
  int l_height = 0;
  int r_height = 0;
  
  if (node->left) {
    l_height = node->left->height;
  }
  if (node->right) {
    r_height = node->right->height;
  }
  return l_height - r_height;
}

template<class T> void AVL<T>::balance(Node *node) {
  int balanceFactor = diff(node);
  if (balanceFactor >= 2) {  //left Heavy
    if (diff(node->left) >= 0) {
      ll_rotation(node);
    } else {
      lr_rotation(node);
    }
  } else if (balanceFactor <= -2) {
    if (diff(node->right) > 0) {
      rl_rotation(node);
    } else {
      rr_rotation(node);
    }
  }
  if (node->parent) {
    balance(node->parent);
  }

  updateSmallest();
}

template<class T> void AVL<T>::ll_rotation(Node *node) {
  Node *parent = node->parent;
  Node *lChild = node->left;

  // parent-node relation
  ParentPointTo(node, lChild);
  lChild->parent = parent;

  Node *lrChild = node->left->right;
  lChild->right = node;
  node->parent = lChild;
  
  node->left = lrChild;
  if (lrChild)
    lrChild->parent = node;

  updateHeights(node);
}

template<class T> void AVL<T>::rr_rotation(Node *node) {
  Node *parent = node->parent;
  Node *rChild = node->right;

  // parent-node relation
  ParentPointTo(node, rChild);
  rChild->parent = parent;

  Node *rlChild = node->right->left;
  // node-child relation
  rChild->left = node;
  node->parent = rChild;
  // restore lost chain
  node->right = rlChild;
  if (rlChild)
    rlChild->parent = node;

  updateHeights(node);
}

template<class T> void AVL<T>::lr_rotation(Node *node) {
  rr_rotation(node->left);
  ll_rotation(node);
}

template<class T> void AVL<T>::rl_rotation(Node *node) {
  ll_rotation(node->right);
  rr_rotation(node);
}

template<class T> typename AVL<T>::Node *AVL<T>::getNode(unsigned long id) {
  Node *current = root;
  while ((current != NULL) && (current->id != id)) {
    if (id < current->id) {  // left subtree
      current = current->left;
    } else {  // right subtree
      current = current->right;
    }
  }
  return current;
}

template<class T> void AVL<T>::ParentPointTo(Node *child, Node *newChild) {
  if (child->parent == NULL) {
    root = newChild;
  } else {
    if (child->parent->left == child) {
      child->parent->left = newChild;
    } else {
      child->parent->right = newChild;
    }

  }
}


template<class T> void AVL<T>::printTree2(Node *ptr, int level) {
  int i;
  if (ptr) {
    printTree2(ptr->right, level + 1);
    cout << "\n";
    if (ptr == root) {
      cout << "Root ->";
    }

    for (i = 0; i < level && ptr != root; i++) {
      cout << "       ";
    }
    cout << ptr->id;


    printTree2(ptr->left, level + 1);
  }
}



template<class T> T* AVL<T>::getSortedArray2(T *array, Node *node) {
  if (node == NULL)
    return array;
  array = getSortedArray2(array, node->left);
  *array = node->data;
  array++;
  return getSortedArray2(array, node->right);
}

template<class T> void AVL<T>::rm_all(Node *node) {
  if (node == NULL)
    return;
  rm_all(node->left);
  rm_all(node->right);
  delete node;
}

#endif  // AVL_H
