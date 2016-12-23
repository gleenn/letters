(ns letters.subs
    (:require-macros [reagent.ratom :refer [reaction]])
    (:require [re-frame.core :as re-frame]))

(re-frame/reg-sub
 :name
 (fn [db]
   (:name db)))

(re-frame/reg-sub
 :count
 (fn [db]
   (:count db)))

(re-frame/reg-sub
 :total
 (fn [db]
   (reduce + (vals (:count db)))))
