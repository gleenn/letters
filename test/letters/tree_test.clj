(ns letters.tree-test
  (:require [midje.sweet :refer :all]
            [letters.tree :refer :all])
  ;(:import (letters.tree Node))
  )

  (fact complete-tree
      (complete-tree []) => nil
      (complete-tree [['a 1]]) => ['a 1]
      (complete-tree [['a 1] ['b 2]]) => [['a 1] ['b 2]]
      (complete-tree [1 2 3 4]) => [[1 2] [3 4]]
      (complete-tree [1 2 3 4 5 6 7 8]) => [[[1 2] [3 4]] [[5 6] [7 8]]])

;(fact Node
;      (Node. 1 2 3) => (Node. 1 2 3)
;      (Node. 1 nil nil) => (Node. 1 nil nil))
