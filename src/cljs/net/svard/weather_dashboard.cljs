(ns ^:figwheel-always net.svard.weather_dashboard
  (:require [goog.dom :as gdom]
            [om.next :as om]
            [net.svard.weather_dashboard.app :as app]))

(defmulti read om/dispatch)

(def reconciler
  (om/reconciler
    {:state {:dashboard/items []}
     :parser (om/parser {:read read})}))

(om/add-root! reconciler app/App (gdom/getElement "app"))
