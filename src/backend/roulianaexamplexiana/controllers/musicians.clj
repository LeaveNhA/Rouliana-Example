(ns roulianaexamplexiana.controllers.musicians
  (:require
   [ring.util.response :as ring]
   [jsonista.core :refer [write-value-as-string]]))

(defn get-all
  [state]
  (assoc state
         :response
         (-> ["Mozart"
              "Beethoven"
              "Chopin"]
             write-value-as-string
             ring/response
             (ring/header "Content-Type"
                          "application/json; charset=utf-8"))))
