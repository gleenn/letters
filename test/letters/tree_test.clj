(ns letters.tree-test
  (:require [midje.sweet :refer :all]
            [letters.tree :refer :all]))

(fact tree
      (complete-tree 1) => [nil nil]
      (complete-tree 3) => [[[nil nil] [nil nil]] [[nil nil] [nil nil]]]
      (complete-tree 1 [1]) => 1
      (complete-tree 2 [1 2]) => [1 2]
      (complete-tree 3 [1 2 3 4]) => [[1 2] [3 4]]
      (complete-tree 4 [1 2 3 4 5 6 7 8]) => [[[1 2] [3 4]] [[5 6] [7 8]]]
      )
