(ns letters.database
  (:require [korma.db :refer :all]
            ;;[korma.core :refer :all]
            ))

(defdb db (postgres {:db       "letters_dev"
                     :user     "postgres"
                     ;:password ""
                     }))
