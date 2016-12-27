(ns letters.models.base
  (:require [korma.core :refer [defentity entity-fields select]]
            [korma.db :refer :all]
            [clojure.data.json :as json])
  (:import (java.sql Timestamp)))

(extend-type Timestamp
  json/JSONWriter
  (-write [date out]
    (json/-write (str date) out)))
