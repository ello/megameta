(ns megameta.kush
  (:use [clojure.string :refer [blank?]])
  (:require [net.cgrand.enlive-html :as html]))

(defn pull-resource [url]
  (html/html-resource (java.net.URL. url)))

(defn og-match [str]
  (not (blank? str)))

(defn get-metas [url]
  (let [metas (filter :property (map :attrs (html/select (pull-resource url) [:html :head :meta])))]
    (filter #(re-find #"og:(.*)" (:property %)) metas)))
