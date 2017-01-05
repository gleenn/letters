(ns letters.tree-test
  (:require [midje.sweet :refer :all]
            [letters.tree :refer :all]))

(fact node
      (node 1 2) => (node 1 2))

(fact count-left-tree
      (count-left-tree []) => nil
      (count-left-tree [['a 1]]) => (leaf 'a 1)
      (count-left-tree [['a 1] ['b 2]]) => (node (leaf 'a 1) (leaf 'b 2))
      (count-left-tree [['m 1] ['n 2] ['o 3] ['p 4]]) => (node
                                                           (node
                                                             (leaf 'm 1) (leaf 'n 2))
                                                           (node
                                                             (leaf 'o 3) (leaf 'p 4)))
      ;(count-left-tree [1 2 3 4 5 6 7 8]) => [[[1 2] [3 4]] [[5 6] [7 8]]]
      )


(fact tree-size
      (tree-size nil) => 0
      (tree-size 'a) => 1
      (tree-size ['a 'b]) => 2
      (tree-size [['a 'c] 'b]) => 3)

(fact pull-letter
      (let [example-tree (count-left-tree [['a 1] ['b 2]])]
        (pull-letter example-tree 1)))
;(fact Node
;      (Node. 1 2 3) => (Node. 1 2 3)
;      (Node. 1 nil nil) => (Node. 1 nil nil))
