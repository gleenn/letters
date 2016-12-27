(ns letters.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            [letters.events]
            [letters.subs]
            [letters.views.main-panel :as main-panel]
            [letters.config :as config]))


(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (println "dev mode")))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [main-panel/render]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))
