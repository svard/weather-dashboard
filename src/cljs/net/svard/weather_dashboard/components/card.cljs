(ns ^:figwheel-always net.svard.weather-dashboard.components.card
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :as html :refer-macros [html]]
            [net.svard.weather-dashboard.utils.date :as date]))

(defui TemperatureCard
  static om/IQuery
  (query [_]
    '[:id :title :value :date :type])

  Object
  (render [this]
    (let [{:keys [title value date]} (om/props this)
          date-str (date/format-datetime date)]
      (html [:div {:class "card mdl-shadow--2dp"}
             [:h4 {:class "card-title"}
              [:i {:class "card-title-icon wi wi-thermometer"}]
              [:span title]]
             [:div {:class "card-subtitle"} date-str]
             [:div {:class "card-value"}
              [:span {:class "card-value"} value]
              [:i {:class "wi wi-celsius card-unit-celsius"}]]]))))

(def temperature-card (om/factory TemperatureCard))

(defui PrecipitationCard
  static om/IQuery
  (query [_]
    '[:id :title :value :date :type])
  
  Object
  (render [this]
    (let [{:keys [title value date type] :as item} (om/props this)
          date-str (date/format-datetime date)]
      (html [:div {:class "card mdl-shadow--2dp"}
             [:h4 {:class "card-title"}
              [:i {:class "card-title-icon wi wi-raindrops"}]
              [:span title]]
             [:div {:class "card-subtitle"} date-str]
             [:div {:class "card-value"}
              [:span {:class "card-value"} value]
              [:span {:class "card-unit-millimeter"} "mm"]]]))))

(def precipitation-card (om/factory PrecipitationCard))

(defui HumidityCard
  static om/IQuery
  (query [_]
    '[:id :title :value :date :type])
  
  Object
  (render [this]
    (let [{:keys [title value date type] :as item} (om/props this)
          date-str (date/format-datetime date)]
      (html [:div {:class "card mdl-shadow--2dp"}
             [:h4 {:class "card-title"}
              [:i {:class "card-title-icon wi wi-humidity"}]
              [:span title]]
             [:div {:class "card-subtitle"} date-str]
             [:div {:class "card-value"}
              [:span {:class "card-value"} value]
              [:span {:class "card-unit-percentage"} "%"]]]))))

(def humidity-card (om/factory HumidityCard))
