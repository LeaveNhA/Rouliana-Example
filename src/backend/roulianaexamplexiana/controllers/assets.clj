(ns roulianaexamplexiana.controllers.assets
  (:require [reitit.ring :as ring]))

(def create-resource-handler
  (ring/create-resource-handler {:path "/"}))

(comment
  (.routes (ring/router
         ["assets/*" (ring/create-resource-handler {:path "/"})])))
