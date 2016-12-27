(ns letters.views.content
  (:require [re-frame.core :as re-frame]))

(def last-id (atom 10000))

(defn id-sequence []
  (swap! last-id inc))

(defn button [event title & opts]
  [:button
   (merge {:on-click #(re-frame/dispatch event) :key (id-sequence)} (first opts)) title])

(defn render []
  (let [count (re-frame/subscribe [:count])
        total (re-frame/subscribe [:total])
        page-number (re-frame/subscribe [:page-number])
        random (re-frame/subscribe [:random])
        hello (re-frame/subscribe [:hello])]
    (fn []
      [:div
       [:div "This is sweet a random number " @random]
       [:div "Hello " @hello]
       (button [:gimme-data] "Gimme a new random")
       [:div "Game total: " @total]
       [:br]
       [:div
        [:input {:value (str "left " (:left @count)) :readOnly true}]
        (button [:increment :left] "Increment Left")
        [:input {:value (str "right " (:right @count)) :readOnly true}]
        (button [:increment :right] "Increment Right")
        ]
       [:br]
       (condp = @page-number
         1 [:div (button [:reset] "Reset")]
         2 [:div (button [:reverse] "Reverse")]
         3 [:div (button [:recurse] "Recurse")]
         [:div (button [:reset] "Reset")])
       [:div
        (doall (for [i (range 1 4)]
                 (button [:goto-page i] i (when (= @page-number i) {:className "selected"}))))]
       ])))
