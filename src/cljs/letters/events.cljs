(ns letters.events
  (:require [re-frame.core :as re-frame]
            [day8.re-frame.http-fx]
            [letters.db :as db]
            [ajax.core :as ajax]))

(re-frame/reg-event-db
  :initialize-db
  (fn [_ _]
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
      (-> db
          (update-in [:count :right] (fn [_] left))
          (update-in [:count :left] (fn [_] right))))))

(re-frame/reg-event-db
  :recurse
  (fn [db event]
    (re-frame/dispatch [:reset])))

(re-frame/reg-event-db
  :goto-page
  (fn [db event]
    (assoc db :page-number (second event))))

(re-frame/reg-event-fx                                      ;; note the trailing -fx
  ;:handler-with-http                                       ;; usage:  (dispatch [:handler-with-http])
  :gimme-data                                               ;; usage:  (dispatch [:handler-with-http])
  (fn [{:keys [db]} _]                                      ;; the first param will be "world"
    {:db         (assoc db :show-twirly true)               ;; causes the twirly-waiting-dialog to show??
     :http-xhrio {:method          :get
                  :uri             "http://localhost:3449/gimme-data"
                  :timeout         8000                     ;; optional see API docs
                  :response-format (ajax/json-response-format {:keywords? true}) ;; IMPORTANT!: You must provide this.
                  :on-success      [:gimme-data-success]
                  :on-failure      [:gimme-data-success]}}))

(re-frame/reg-event-db
  :gimme-data-success
  (fn [db event]
      (assoc db :gimme-data (second event))))

(re-frame/reg-event-db
  :gimme-data-failure
  (fn [_ event]
    (do (.log js/console (str event)) (js/alert "Failed during da gimme-data!"))))
