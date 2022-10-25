(ns roulianaexamplexiana.events
  (:require
   [re-frame.core :as re-frame]
   [roulianaexamplexiana.httpto]
   [roulianaexamplexiana.db :as db]))

(re-frame/reg-event-db
 :api:users:get-all:success
 (fn [_ [_ {body :body}]]
   (js/console.info (str "API response body: " (clj->js body)))
   {:names (apply str (interpose ", " body))}))

(re-frame/reg-event-db
 :api:users:get-all:fail
 (fn [_ _]
   {:names "{API failed.}"}))

(re-frame/reg-event-fx
 :api:users:get-all
 (fn [_ _]
   {:http-> {:end-point    [:api :musicians :get-all]
             :on-success   [:api:users:get-all:success]
             :on-failure   [:api:users:get-all:fail]}}))

(re-frame/reg-event-db
 ::initialize-db
 (fn [_ _]
   db/default-db))
