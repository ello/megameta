(ns megameta.handler
  (:use [compojure.core]
        [ring.adapter.jetty]
        [ring.middleware.json])
  (:require [compojure.handler :as handler]
            [megameta.kush :as kush]
            [ring.util.response :refer [response]]
            [compojure.route :as route]))


(defn embed [body]
  (kush/dispatch-request (:url body)))

(defroutes app-routes
  (GET "/ping" [] (response {:message "PONG"}))
  (POST "/embed" {body :body headers :header} (response (embed body)))
  (route/not-found (response {:message "Page Not Found"})))


(def app
  (-> app-routes
      wrap-json-response
      wrap-json-body))
