(ns megameta.kush
  (:use cheshire.core)
  (:require [net.cgrand.enlive-html :as html]
            [clj-http.client :as client]))

(defn- pull-html-resource [url]
  (html/html-resource (java.net.URL. url)))

(defn-  meta-tags [url]
  (filter :property (map :attrs (html/select (pull-html-resource url) [:html :head :meta]))))

(defn get-og-tags [url]
  (filter #(re-find #"og:(.*)" (:property %)) (meta-tags url)))

(defn get-oembed [url]
  (let [response (client/get url)]
    (try
      (parse-string (:body response))
      (catch Exception e nil))))


(defn dispatch-request [url]
  (let [og (get-og-tags url)]
    (case og
      nil (get-oembed url)
      og
      )))
