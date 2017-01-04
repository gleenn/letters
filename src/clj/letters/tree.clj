(ns letters.tree)

;(defprotocol Node)
;(defrecord Node [left right value])

(defn complete-tree
  [vals]
  (let [count-vals (count vals)]
    (if (> count-vals 1)
      (let [[left-vals right-vals] (split-at (/ count-vals 2) vals)]
        [(complete-tree (vec left-vals))
         (complete-tree (vec right-vals))])
      (first vals))))
