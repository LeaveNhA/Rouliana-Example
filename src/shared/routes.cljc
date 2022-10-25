(ns routes
  (:require [rouliana.dcompile :refer [dcompile-ep]]
            [rouliana.core.classify :refer [classify-data-formation]]))

(def routes
  [:index "/" {:action :index/handle-index}
   :re-frame "/re-frame" {:action :re-frame/handle-index}
   :assets "/assets/*" {:handler :assets/create-resource-handler}
   :api "/api" {:handler nil}
   [[:musicians
     ["/musicians"
      :get-all :get {:action :musicians/get-all
                     :swagger {:produce ["application/json"
                                         "application/edn"]}}]]]])

(def droutes
  (-> routes dcompile-ep))

(def |>> (partial rouliana.dcompile/|>> droutes))
