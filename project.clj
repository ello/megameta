(defproject megameta "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.4"]
                 [ring "1.4.0-RC1"]
                 [ring/ring-defaults "0.1.5"]
                 [ring/ring-json "0.3.1"]
                 [ring/ring-codec "1.0.0"]
                 [ring/ring-ssl "0.2.1"]
                 [ring/ring-mock "0.2.0"]
                 [clj-http "1.1.2"]
                 [cheshire "5.5.0"]
                 [com.taoensso/carmine "2.11.1"]
                 [enlive "1.1.5"]
                 [org.clojure/tools.namespace "0.2.10"]]
  :ring {:handler megameta.handler/app }
  :target-path "target/%s"
  :repl-options { :init-ns megameta.repl }
  :profiles {:uberjar {:aot :all}})
