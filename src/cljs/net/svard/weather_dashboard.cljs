(ns ^:figwheel-always net.svard.weather-dashboard
  (:require [goog.dom :as gdom]
            [om.next :as om]
            [net.svard.weather-dashboard.app :as app]))

(defonce init-data
  {:dashboard/items [{:id 0
                      :title "Outdoor"
                      :date 1449940371875
                      :value 5.3
                      :type :temperature}
                     {:id 1
                      :title "Indoor"
                      :value 23
                      :date 1449940371875
                      :type :temperature}
                     {:id 2
                      :title "Precipitation"
                      :value 0
                      :date 1449940371875
                      :type :precipitation}
                     {:id 3
                      :title "Humidity"
                      :value 95
                      :date 1449940371875
                      :type :humidity}]})

(defmulti read om/dispatch)

(defmethod read :dashboard/items
  [{:keys [query state]} key _]
  (let [st @state]
    {:value (om/db->tree query (get st key) st)}))

(defmulti mutate om/dispatch)

(defmethod mutate 'dashboard/update-item
  [{:keys [state]} _ {:keys [type id value date]}]
  {:value {:keys [:date :value]}
   :action (fn []
             (swap! state update-in [type id] assoc :value value :date date))})

(def reconciler
  (om/reconciler
    {:state init-data
     :parser (om/parser {:read read :mutate mutate})}))

(om/add-root! reconciler app/App (gdom/getElement "app"))
