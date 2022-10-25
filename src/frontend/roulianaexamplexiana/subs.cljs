(ns roulianaexamplexiana.subs
  (:require
   [re-frame.core :as re-frame]))

(re-frame/reg-sub
 ::names
 (fn [db]
   (:names db)))
