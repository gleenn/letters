(ns letters.events
    (:require [re-frame.core :as re-frame]
              [letters.db :as db]))

(re-frame/reg-event-db
 :initialize-db
 (fn  [_ _]
   db/default-db))

(re-frame/reg-event-db
  :increment
  (fn [db event]
      (update-in db [:count] inc)))

(re-frame/reg-event-db
  :reset
  (fn [db event]
      (assoc db :count 0)))
