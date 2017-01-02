(ns letters.views.content
  (:require [re-frame.core :as re-frame]
            [letters.views.tree :as tree]))

(def last-id (atom 10000))

(defn id-sequence []
  (swap! last-id inc))

(defn button [event title & opts]
  [:button
   (merge {:on-click #(re-frame/dispatch event) :key (id-sequence)} (first opts)) title])

(defn render-ajax []
  (let [random (re-frame/subscribe [:random])
        hello (re-frame/subscribe [:hello])]
    [:div
     [:div "This is sweet a random number " @random]
     [:div "Hello " @hello]
     (button [:gimme-data] "Gimme a new random")]))

(defn render-counter []
  (let [total (re-frame/subscribe [:total])
        count (re-frame/subscribe [:count])
        counter-page-number (re-frame/subscribe [:counter-page-number])]
    [:div
     [:div "Total " @total]
     [:br]
     [:div
      [:input {:value (str "left " (:left @count)) :readOnly true}]
      (button [:increment :left] "Increment Left")
      [:input {:value (str "right " (:right @count)) :readOnly true}]
      (button [:increment :right] "Increment Right")
      ]
     [:br]
     (condp = @counter-page-number
       1 [:div (button [:reset] "Reset")]
       2 [:div (button [:reverse] "Reverse")]
       3 [:div (button [:recurse] "Recurse")]
       [:div (button [:reset] "Reset")])
     (doall (for [i (range 1 4)]
              (button [:goto-counter-page i] i (when (= @counter-page-number i) {:className "selected"}))))]))

(defn render []
  (let [page-number (re-frame/subscribe [:page-number])]
    (fn []
      [:div
       (condp = @page-number
         "ajax" (render-ajax)
         "counter" (render-counter)
         "tree" (tree/render)
         (tree/render))
       (doall (for [i ["tree" "ajax" "counter"]]
                (button [:goto-page i] i (when (= @page-number i) {:className "selected"}))))
       ])))
