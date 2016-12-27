(defproject letters "0.1.0-SNAPSHOT"
  :dependencies [[compojure "1.5.1"]
                 [conman "0.6.2"]
                 [day8.re-frame/http-fx "0.1.3"]
                 [environ "1.1.0"]
                 [figwheel-sidecar "0.5.0"]
                 [korma "0.4.2"]
                 [log4j "1.2.15" :exclusions [javax.mail/mail
                                              javax.jms/jms
                                              com.sun.jdmk/jmxtools
                                              com.sun.jmx/jmxri]]
                 [migratus "0.8.32"]
                 [migratus-lein "0.4.3"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/java.jdbc "0.4.1"]
                 [org.postgresql/postgresql "9.4-1201-jdbc41"]
                 [org.slf4j/slf4j-log4j12 "1.7.9"] ; hidden dep for migratus
                 [ring "1.5.0"]
                 [re-frame "0.9.1"]
                 [reagent "0.6.0"]]

  :main letters.core

  :plugins [[lein-cljsbuild "1.1.4"]
            [lein-environ "1.1.0"]
            [migratus-lein "0.4.2"]]


  :migratus {:store :database
             :migration-dir "migrations"
             :db ~(get (System/getenv) "DATABASE_URL")}

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {
             :css-dirs     ["resources/public/css"]
             :ring-handler letters.server/app
             ;:server-port 3000
             }
  ;:ring {:handler letters.server/app}

  :profiles
  {:dev
   {:dependencies [[binaryage/devtools "0.8.2"]
                   [midje "1.6.0" :exclusions [org.clojure/clojure]]]
    :plugins      [[lein-figwheel "0.5.7"]
                   [lein-midje "3.2.1"]]
    }}

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/cljs"]
     :figwheel     {:on-jsload "letters.core/mount-root"}
     :compiler     {:main                 letters.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true
                    :preloads             [devtools.preload]
                    :external-config      {:devtools/config {:features-to-install :all}}
                    }}

    {:id           "min"
     :source-paths ["src/cljs"]
     :compiler     {:main            letters.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}

    ]}
  )
