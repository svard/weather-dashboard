(ns ^:figwheel-always net.svard.weather_dashboard.app
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :as html :refer-macros [html]]))

(defui Header
  Object
  (render [this]
    (let [{:keys [title]} (om/props this)]
      (html [:header {:class "mdl-layout__header"}
             [:div {:class "mdl-layout-icon"}]
             [:div {:class "mdl-layout__header-row"}
              [:span {:class "mdl-layout__title"} title]]]))))

(def header (om/factory Header))

(defui TemperatureCard
  Object
  (render [this]
    (let [{:keys [title value]} (om/props this)]
      (html [:div {:class "card mdl-shadow--2dp"}
             [:h4 {:class "card-title"}
              [:i {:class "card-title-icon wi wi-thermometer-exterior"}]
              [:span title]]
             [:div {:class "card-subtitle"} "Mon 12:30 PM"]
             [:div {:class "card-value"}
              [:span {:class "card-value-primary"} value]
              [:i {:class "wi wi-celsius card-celsius"}]]]))))

(def temperature-card (om/factory TemperatureCard))

(defui PrecipitationCard
  Object
  (render [this]
    (let [{:keys [title value]} (om/props this)]
      (html [:div {:class "card mdl-shadow--2dp"}
             [:h4 {:class "card-title"}
              [:i {:class "card-title-icon wi wi-raindrop"}]
              [:span title]]
             [:div {:class "card-subtitle"} "Mon 12:30 PM"]
             [:div {:class "card-value"}
              [:span {:class "card-value-primary"} value]]]))))

(def precipitation-card (om/factory PrecipitationCard))

(defui HumidityCard
  Object
  (render [this]
    (let [{:keys [title value]} (om/props this)]
      (html [:div {:class "card mdl-shadow--2dp"}
             [:h4 {:class "card-title"}
              [:i {:class "card-title-icon wi wi-humidity"}]
              [:span title]]
             [:div {:class "card-subtitle"} "Mon 12:30 PM"]
             [:div {:class "card-value"}
              [:span {:class "card-value-primary"} value]
              [:span "%"]]]))))

(def humidity-card (om/factory HumidityCard))

(defui App
  Object
  (render [this]
    (html [:div {:class "mdl-layout mdl-js-layout mdl-layout--fixed-header"}
           (header {:title "Dashboard"})
           [:main {:class "mdl-layout__content"}
            [:div {:class "mdl-grid app-content"}
             [:div {:class "mdl-cell mdl-cell--3-col"}
              (temperature-card {:title "Outdoor"
                                 :value 5.3})]
             [:div {:class "mdl-cell mdl-cell--3-col"}
              (temperature-card {:title "Indoor"
                                 :value 23})]
             [:div {:class "mdl-cell mdl-cell--3-col"}
              (precipitation-card {:title "Precipitation"
                                   :value 0})]
             [:div {:class "mdl-cell mdl-cell--3-col"}
              (humidity-card {:title "Humidity"
                     :value 95})]]]]))

  (componentDidMount [this]
    (.upgradeDom js/componentHandler)))
