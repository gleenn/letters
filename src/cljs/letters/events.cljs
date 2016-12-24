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
        (update-in db [:count (second event)] inc)))

(re-frame/reg-event-db
  :reset
  (fn [db event]
      (assoc db :count (:count db/default-db))))

(re-frame/reg-event-db
  :reverse
  (fn [db event]
    (let [left (-> db :count :left)
          right (-> db :count :right)]
      (do (.log js/console left right)
          (-> db
           (update-in [:count :right] (fn [_] left))
           (update-in [:count :left] (fn [_] right)))))))
