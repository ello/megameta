(ns megameta.handler
  (:use [compojure.route]
        [compojure.core])
  (:require [ring.middleware.reload :as reload]))

(defroutes megameta-routes
  (GET "/" req (str req)))
