(ns megameta.kush-test
  (:require [clojure.test :refer :all]
            [megameta.kush :refer :all]))


(deftest test-pull-resource
  (testing "retrieving a URL with kush")
  (let [response (pull-resource "http://www.google.com")]
    (is (= clojure.lang.LazySeq (class response)))))
