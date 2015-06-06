(defproject megameta "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.3.4"]
                 [ring "1.4.0-RC1"]
                 [clj-http "1.1.2"]
                 [cheshire "5.5.0"]
                 [enlive "1.1.5"]
                 [org.clojure/tools.namespace "0.2.10"]]
  :main ^:skip-aot megameta.core
  :target-path "target/%s"
  :repl-options { :init-ns megameta.repl }
  :profiles {:uberjar {:aot :all}})
