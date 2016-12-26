(ns letters.tree)

(defn complete-tree
  ([size]
   (if (> size 1)
     [(complete-tree (dec size)) (complete-tree (dec size))]
     [nil nil]))
  ([size vals]
   (if (> size 1)
     (let [[left-vals right-vals] (split-at (/ (count vals) 2) vals)]
         [(complete-tree (dec size) left-vals)
        (complete-tree (dec size) right-vals)])
     (first vals))))
