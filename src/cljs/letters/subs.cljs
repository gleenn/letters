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
  :page-number
  (fn [db]
    (:page-number db)))

(re-frame/reg-sub
  :total
  (fn [db]
    (reduce + (vals (:count db)))))

(re-frame/reg-sub
  :random
  (fn [db]
    (-> db :gimme-data :random)))

(re-frame/reg-sub
  :hello
  (fn [db]
    (-> db :gimme-data :hello)))

(re-frame/reg-sub
  :logged-in
  (fn [db]
    (:logged-in db)))
