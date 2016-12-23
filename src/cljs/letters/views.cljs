(ns letters.views
  (:require [re-frame.core :as re-frame]))

(defn main-panel []
  (let [count (re-frame/subscribe [:count]) total (re-frame/subscribe [:total])]
    (fn []
      [:div
       [:div "Game total: " @total]
       [:div
        [:input {:value (str "left " (:left @count))}]
        [:button {:on-click #(re-frame/dispatch [:increment :left])} "increment left"]
        [:input {:value (str "right " (:right @count))}]
        [:button {:on-click #(re-frame/dispatch [:increment :right])} "increment right"]
        [:button {:on-click #(re-frame/dispatch [:reset])} "reset"]
        ]
       ])))
