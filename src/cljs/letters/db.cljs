(ns letters.db)

(def default-db
  {:name        "re-frame"
   :logged-in   true
   :count       {:left 0 :right 0}
   :page-number 1})
