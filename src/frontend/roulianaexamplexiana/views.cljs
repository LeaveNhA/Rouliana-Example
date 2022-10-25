(ns roulianaexamplexiana.views
  (:require
   [re-frame.core :as re-frame]
   [roulianaexamplexiana.subs :as subs]))

(defn main-panel []
  (let [names (re-frame/subscribe [::subs/names])]
    [:div
     (when @names [:h2 "\"" @names " never died. They simply became music.\""])
     [:br]
     (when (not @names)
       [:button {:on-click #(re-frame/dispatch [:api:users:get-all])}
        "Click me to surprise!"])]))
