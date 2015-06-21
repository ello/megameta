(ns megameta.redis-test
  (:require [clojure.test :refer :all]
            [megameta.redis :refer :all]))

(deftest test-redis-connection
  (initialize-pool {host: "localhost" port: 6379 })
  (testing "set and retrieve a key value pair"
    (let [response (-set "foo" "bar")])

    ))
