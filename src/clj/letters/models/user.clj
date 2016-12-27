(ns letters.models.user
  (:require [korma.core :refer [defentity entity-fields select fields where]]
            [korma.db :refer :all]))

(defentity users (entity-fields :id :email :password :created_at :updated_at))

(defn all [] (select users
                     ;(fields :email) ;; wtf...
                     ))


