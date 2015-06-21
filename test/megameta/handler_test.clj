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
    (let [response (app (request :post "/embed" {:body {:url "http://www.youtube.com/oembed?url=http%3A//youtube.com/watch%3Fv%3DM3r2XDceM6A&format=json"}}))]
      (is (= (:status response) 200))
      (is (= (get-in response [:headers "Content-Type"]) "application/json; charset=utf-8"))))

  (testing "not found route"
    (let [response (app (request :get "/haxorz/database"))]
      (is (= (:status response) 404)))))
