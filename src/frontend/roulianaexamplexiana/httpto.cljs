(ns roulianaexamplexiana.httpto
  (:require
   [routes :refer [|>>]]
   [ajax.core :as ajax]
   [day8.re-frame.http-fx :refer [http-effect]]
   [re-frame.core :as rf]))

(defn process-request
  [{:keys [format response-format end-point]
    :or {format          (ajax/json-request-format)
         response-format (ajax/ring-response-format
                          {:format (ajax/json-response-format
                                    {:keywords? true})})}
    :as request}]
  (assoc request
         :uri    (apply |>> :path    end-point)
         :method (apply |>> :method end-point)
         :format format
         :response-format response-format))

(rf/reg-fx
 :http->
 (comp http-effect process-request))
