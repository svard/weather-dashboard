(ns ^:figwheel-always net.svard.weather-dashboard.app
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :as html :refer-macros [html]]
            [cljsjs.material]
            [net.svard.weather-dashboard.components.card :as cc]))

(enable-console-print!)

(defui Header
  Object
  (render [this]
    (let [{:keys [title]} (om/props this)]
      (html [:header {:class "mdl-layout__header"}
             [:div {:class "mdl-layout-icon"}]
             [:div {:class "mdl-layout__header-row"}
              [:span {:class "mdl-layout__title"} title]]]))))

(def header (om/factory Header))

;; (defn dashboard-item [idx {:keys [date] :as items}]
;;   (html [:div {:class "mdl-cell mdl-cell--3-col"
;;                :key idx}
;;          (cc/card items)]))

(defui DashboardItem
  static om/Ident
  (ident [_ {:keys [type id]}]
    [type id])

  static om/IQuery
  (query [_]
    {:temperature (om/get-query cc/Card)
     :precipitation (om/get-query cc/Card)
     :humidity (om/get-query cc/Card)})

  Object
  (render [this]
    (let [{:keys [id] :as item} (om/props this)]
      (html [:div {:class "mdl-cell mdl-cell--3-col"
                   :key id}
             (cc/card item)]))))

(def dashboard-item (om/factory DashboardItem))

(defui App
  static om/IQuery
  (query [_]
    [{:dashboard/items (om/get-query DashboardItem)}])
  
  Object
  (render [this]
    (let [{:keys [dashboard/items]} (om/props this)]
      (html [:div {:class "mdl-layout mdl-js-layout mdl-layout--fixed-header"}
             (header {:title "Dashboard"})
             [:main {:class "mdl-layout__content"}
              [:div {:class "mdl-grid app-content"}
               (map dashboard-item items)
               ]]])))

  (componentDidMount [this]
    (.upgradeDom js/componentHandler)))
