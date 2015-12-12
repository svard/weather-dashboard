(ns net.svard.weather-dashboard.components.immutant-web-server
  (:require [com.stuartsierra.component :as component]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [immutant.web :as web]
            [clojure.java.io :refer [resource]]
            [ring.middleware.format :refer [wrap-restful-format]]
            [clojure.tools.logging :as log]))

(defroutes app-routes
  (GET "/" [] (resource "public/index.html"))
  (context "/api" []
    (GET "/dashboard/items" [] "Dashboards"))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-restful-format app-routes :formats [:transit-json]))

(defrecord ImmutantWebServer [config]
  component/Lifecycle
  (start [component]
    (let [{port :port host :host mode :mode} config]
      (case mode
        :prod (do
                (log/info (str "Starting web server at " host ":" port))
                (web/run app (dissoc config :mode)))
        :dev (do
               (log/info (str "Starting web server in development mode at " host ":" port))
               (web/run-dmc app (dissoc config :mode))))
      component))

  (stop [component]
    (log/info "Stopping web server")
    (web/stop)
    component))

(defn new-immutant-web-server [{:keys [http]}]
  (->ImmutantWebServer http))
