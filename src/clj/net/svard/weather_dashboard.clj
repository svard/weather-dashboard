(ns net.svard.weather-dashboard
  (:require [com.stuartsierra.component :as component]
            [immuconf.config :as config]
            [net.svard.weather-dashboard.components.immutant-web-server :as http]
            [clojure.tools.logging :as log])
  (:gen-class))

(def config (config/load "resources/config.edn" "config.edn"))

(defn system [config]
  (component/system-map
    :immutant (http/new-immutant-web-server config)))

(defn -main [& args]
  (log/info "Starting system")
  (component/start (system config)))
