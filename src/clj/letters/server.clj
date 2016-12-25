(ns letters.server
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [clojure.data.json :as json]))

(defroutes handler
           (GET "/hello" [] "<h1>Hello World</h1>")
           (GET "/gimme-data" [] (json/write-str {:hello "darkness" :random (rand-int 100)}))
           (route/not-found "<h1>Page not found</h1>"))
