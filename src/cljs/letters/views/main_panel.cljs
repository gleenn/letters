(ns letters.views.main-panel
  (:require [re-frame.core :as re-frame]
            [letters.views.content :as content]
            [letters.views.sign-in-form :as sign-in-form]
            ))

(defn render []
  (let [logged-in (re-frame/subscribe [:logged-in])]
    (if @logged-in
      (content/render)
      (sign-in-form/render))))
