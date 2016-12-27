(ns letters.server
  (:require [clojure.data.json :as json]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [korma.core :refer [select]]
            [letters.database]
            [letters.models.user :as user]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.stacktrace :refer [wrap-stacktrace]]))

(defroutes handler
           (GET "/hello" [] "<h1>Hello World</h1>")
           (GET "/gimme-data" [] (json/write-str {:hello "darkness, my old friend." :random (rand-int 100)}))
           (GET "/users" [] (json/write-str (user/all)))
           (route/not-found "<h1>Page not found</h1>"))

(defn wrap-logging [app]
  (fn [request]
    (do (println (str request))
        (app request))))

(def app
  (-> #'handler
      (wrap-reload '[letter.server])
      (wrap-stacktrace)
      (wrap-logging)))
