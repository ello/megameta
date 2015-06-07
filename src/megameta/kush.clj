(ns megameta.kush
  (:use cheshire.core)
  (:require [net.cgrand.enlive-html :as html]
            [clj-http.client :as client]))

(defn- pull-html-resource [url]
  (html/html-resource (java.net.URL. url)))

(defn get-og-tags [url]
  (let [metas (filter :property (map :attrs (html/select (pull-html-resource url) [:html :head :meta])))]
    (filter #(re-find #"og:(.*)" (:property %)) metas)))

(defn get-oembed [url]
  (let [response (client/get url)]
    (try
      (parse-string (:body response))
      (catch Exception e nil))))
