(ns letters.server
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.data.json :as json]
            [ring.middleware.reload :refer [wrap-reload]]
            [ring.middleware.stacktrace :refer [wrap-stacktrace]]))

(defroutes handler
           (GET "/hello" [] "<h1>Hello World</h1>")
           (GET "/gimme-data" [] (json/write-str {:hello "darkness, my old friend." :random (rand-int 100)}))
           (route/not-found "<h1>Page not found</h1>"))

(def app
  (-> #'handler
      (wrap-reload '[letter.server])
      (wrap-stacktrace)))
