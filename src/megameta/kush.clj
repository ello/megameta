(ns megameta.kush
  (:use [clojure.string :refer [blank?]])
  (:require [net.cgrand.enlive-html :as html]))

(defn- pull-html-resource [url]
  (html/html-resource (java.net.URL. url)))

(defn get-og-tags [url]
  (let [metas (filter :property (map :attrs (html/select (pull-html-resource url) [:html :head :meta])))]
    (filter #(re-find #"og:(.*)" (:property %)) metas)))
