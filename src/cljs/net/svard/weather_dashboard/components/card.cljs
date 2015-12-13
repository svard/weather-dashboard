(ns ^:figwheel-always net.svard.weather-dashboard.components.card
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :as html :refer-macros [html]]
            [net.svard.weather-dashboard.utils.date :as date]))

(defn type->unit [type]
  (case type
    :temperature [:i {:class "wi wi-celsius card-unit-celsius"}]
    :precipitation [:span {:class "card-unit-millimeter"} "mm"]
    :humidity [:span {:class "card-unit-percentage"} "%"]))

(defn type->icon [type]
  (case type
    :temperature "wi-thermometer"
    :precipitation "wi-raindrops"
    :humidity "wi-humidity"))

(defui Card
  static om/IQuery
  (query [_]
    '[:id :title :value :date :type])
  
  Object
  (render [this]
    (let [{:keys [title value date type] :as item} (om/props this)
          date-str (date/format-datetime date)]
      (html [:div {:class "card mdl-shadow--2dp"}
             [:h4 {:class "card-title"}
              [:i {:class (str "card-title-icon wi " (type->icon type))}]
              [:span title]]
             [:div {:class "card-subtitle"} date-str]
             [:div {:class "card-value"}
              [:span {:class "card-value"} value]
              (type->unit type)]]))))

(def card (om/factory Card))
