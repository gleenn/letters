(defproject letters "0.1.0-SNAPSHOT"
  :dependencies [
                 [compojure "1.5.1"]
                 [day8.re-frame/http-fx "0.1.3"]
                 [figwheel-sidecar "0.5.0"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.9.229"]
                 [org.clojure/data.json "0.2.6"]
                 [ring "1.5.0"]
                 [re-frame "0.9.1"]
                 [reagent "0.6.0"]
                 ]

  :main letters.core

  :plugins [[lein-cljsbuild "1.1.4"]]

  :min-lein-version "2.5.3"

  :source-paths ["src/clj"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :figwheel {
             :css-dirs     ["resources/public/css"]
             :ring-handler letters.server/app
             }

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
