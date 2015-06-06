(ns megameta.kush
  (:require [clj-http.client :as client]))

(defonce page (atom #{}))

(defn pull-resource [url]
  (client/get url))
