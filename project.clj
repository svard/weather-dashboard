(defproject weather-dashboard "0.1.0"
  :description "Weather dashboard"
  :url "http://example.com/FIXME"
  :min-lein-version "2.0.0"
  
  :dependencies [;; clj
                 [org.clojure/clojure "1.8.0-RC4"]
                 [compojure "1.4.0"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-devel "1.4.0"]
                 [ring-middleware-format "0.7.0"]
                 [org.immutant/web "2.1.1"]
                 [org.clojure/tools.logging "0.3.1"]
                 [ch.qos.logback/logback-classic "1.1.3"]
                 [com.stuartsierra/component "0.3.1"]
                 [liberator "0.14.0"]
                 [com.novemberain/monger "3.0.1"]
                 [levand/immuconf "0.1.0"]

                 ;; cljs
                 [org.clojure/clojurescript "1.7.170"]
                 [org.omcljs/om "1.0.0-alpha28"]
                 [sablono "0.5.3"]
                 [cljsjs/react-dom-server "0.14.3-0"]
                 [cljsjs/material "1.0.6-0"]
                 [cljsjs/chartist "0.9.4-1"]]

  :source-paths ["src/clj" "src/cljs"]

  :test-paths ["test/clj" "test/cljs"]

  :main net.svard.weather-dashboard

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]
  
  :plugins [[lein-cljsbuild "1.1.1"]
            [lein-figwheel "0.5.0-2"]
            [lein-less "1.7.5"]
            [cider/cider-nrepl "0.10.0"]]
  
  :cljsbuild {:builds [{:id "dev"
                        :source-paths ["src/cljs"]
                        :figwheel {:on-jsload "net.svard.weather-dashboard/on-js-reload" }
                        :compiler {:main net.svard.weather-dashboard
                                   :asset-path "js/compiled/out"
                                   :output-to "resources/public/js/compiled/index.js"
                                   :output-dir "resources/public/js/compiled/out"
                                   :source-map-timestamp true }}
                       {:id "min"
                        :source-paths ["src/cljs"]
                        :compiler {:output-to "resources/public/js/compiled/index.js"
                                   :main net.svard.weather-dashboard
                                   :optimizations :advanced
                                   :pretty-print false}}]}

  :less {:source-paths ["src/less"]
         :target-path "resources/public/css/compiled"}
  
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]
                        [com.cemerick/piggieback "0.2.1"] 
                        [org.clojure/tools.nrepl "0.2.12"]]}
   :uberjar {:aot [net.svard.weather-dashboard]
             :prep-tasks ["compile" ["cljsbuild" "once" "min"]]}}

  :figwheel {
             ;; :http-server-root "public" ;; default and assumes "resources" 
             ;; :server-port 3449 ;; default
             ;; :server-ip "127.0.0.1" 

             :css-dirs ["resources/public/css", "resources/public/css/compiled"] ;; watch and update CSSb

             ;; Start an nREPL server into the running figwheel process
             :nrepl-port 7888

             :nrepl-middleware ["cider.nrepl/cider-middleware"
                                "refactor-nrepl.middleware/wrap-refactor"
                                "cemerick.piggieback/wrap-cljs-repl"]})
