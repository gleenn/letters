(ns letters.views
  (:require [re-frame.core :as re-frame]))

(defn button [event title]
  [:button {:on-click #(re-frame/dispatch event)} title])

(defn main-panel []
  (let [count (re-frame/subscribe [:count]) total (re-frame/subscribe [:total])]
    (fn []
      [:div
       [:div "Game total: " @total]
       [:br]
       [:div
        [:input {:value (str "left " (:left @count))}]
        (button [:increment :left] "Increment Left")
        [:input {:value (str "right " (:right @count))}]
        (button [:increment :right] "Increment Right")
        ]
       [:br]
       [:div (button [:reset] "Reset")]
       [:div (button [:reverse] "Reverse")]
       ])))
