(ns letters.tree)

(defprotocol Node)
(defrecord Branch [left right] Node)
(defn branch [left right] (->Branch left right))
(defrecord Leaf [letter count] Node)
(defn leaf [letter count] (->Leaf letter count))

(defn count-left-tree
  [vals]
  (let [count-vals (count vals)]
    (if (> count-vals 1)
      (let [[left-vals right-vals] (split-at (/ count-vals 2) vals)]
        (branch (count-left-tree (vec left-vals))
                (count-left-tree (vec right-vals))))
      (if-let [value (first vals)]
        (apply leaf value)))))

(defn tree-size [tree]
  (cond
    (nil? tree) 0
    (not (vector? tree)) 1
    :else (apply + (map tree-size tree))))

(defn pull-letter
  ([tree]
   (let [random-letter-index (int (rand (tree-size tree)))]
     (pull-letter random-letter-index)))
  ([tree letter-index]
    ))
