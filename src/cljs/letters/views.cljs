(ns letters.views
  (:require [re-frame.core :as re-frame]))

(defn main-panel []
  (let [count (re-frame/subscribe [:count])]
    (fn []
      [:div
       [:input {:value (str "happy " @count)}]
       [:button {:on-click #(re-frame/dispatch [:increment])} "increment"]
       [:button {:on-click #(re-frame/dispatch [:reset])} "reset"]
       ])))

;[:input {:value (str "Hi from " @name) :on-change #(re-frame/dispatch [:increment
;                                                                       nil
;                                                                       ;(-> % .-target .-value)
;                                                                       ])}])))
