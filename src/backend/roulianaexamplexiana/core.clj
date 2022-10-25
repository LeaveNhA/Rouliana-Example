(ns roulianaexamplexiana.core
  (:require
   [routes :refer [routes]]
   [roulianaexamplexiana.controllers.index :as index]
   [roulianaexamplexiana.controllers.re-frame :as re-frame]
   [roulianaexamplexiana.controllers.swagger :as swagger]
   [roulianaexamplexiana.controllers.musicians :as musicians]
   [roulianaexamplexiana.controllers.assets :as assets]
   [xiana.config :as config]
   [xiana.db :as db]
   [xiana.interceptor :as interceptors]
   [xiana.rbac :as rbac]
   [xiana.route :as routes]
   [xiana.swagger :as xsw]
   [xiana.session :as session]
   [xiana.webserver :as ws]
   [reitit.ring :as ring]
   [clojure.walk]
   [ring.util.response]
   [rouliana.parse :as rouliana]
   [xiana.commons :refer [rename-key]]))

(defn ->system
  [app-cfg]
  (-> (config/config app-cfg)
      (rename-key :framework.app/auth :auth)
      xsw/->swagger-data
      routes/reset
      rbac/init
      session/init-backend
      #_db/connect
      #_db/migrate!
      ws/start))

(def app-cfg
  {:routes (rouliana/parse-ep routes)
   :route-pure routes
   :router-interceptors     []
   :controller-interceptors [(interceptors/muuntaja)
                             interceptors/params
                             session/guest-session-interceptor
                             interceptors/view
                             interceptors/side-effect
                             #_db/db-access
                             rbac/interceptor]})

(defn -main
  [& _args]
  (->system app-cfg))
