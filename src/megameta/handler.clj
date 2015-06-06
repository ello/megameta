(ns megameta.handler
  (:use [compojure.core]
        [ring.adapter.jetty]
        [megameta.service]
        [ring.middleware.json])
  (:require [compojure.handler :as handler]
            [ring.util.response :refer [response]]
            [compojure.route :as route]))




(defroutes app-routes
  (GET "/ping" [] (response {:message "PONG"}))
  (POST "/embed" {body :body headers :header} (response (embed body headers)))
  (route/not-found (response {:message "Page Not Found"})))


(def app
  (-> app-routes
      wrap-json-response
      wrap-json-body))
