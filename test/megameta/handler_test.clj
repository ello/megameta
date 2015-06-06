(ns megameta.handler-test
  (:use ring.mock.request)
  (:require [clojure.test :refer :all]
            [megameta.handler :refer :all]))

(deftest test-app
  (testing "ack server endpoint"
    (let [response (app (request :get "/ping"))]
      (is (= (:status response) 200))
      (is (= (get-in response [:headers "Content-Type"]) "application/json; charset=utf-8"))))

  (testing "post embed endpoint"
    (let [response (app (request :post "/embed"))]
      (is (= (:status response) 200))
      (is (= (get-in response [:headers "Content-Type"]) "application/json; charset=utf-8"))))

  (testing "not found route"
    (let [response (app (request :get "/haxorz/database"))]
      (is (= (:status response) 404)))))
