(ns megameta.queue
  (:require megameta.redis :as mredis))


(defn enqueue [queue item]
  (with-jedis
    (do
      (let [queue-name (which-queue-name-for queue)]
        (qdis.jedis/-sadd queue-set queue-name)
        (let [item-uuid (which-item-uuid-for queue-name (qdis.jedis/-incr queue-uuid))]
          (qdis.jedis/-set item-uuid item)
          (qdis.jedis/-lpush queue-name item-uuid)
          item-uuid)))))

(defn dequeue [queue]
  (with-jedis
    (do
      (let [result (let [queue-name (which-queue-name-for queue)]
                     (let [item-uuid (qdis.jedis/-rpop queue-name)]
                       (if (nil? item-uuid)
                         :queue-not-found-or-is-empty
                         (let [item (qdis.jedis/-get item-uuid)]
                           (qdis.jedis/-del item-uuid)
                           {:item-uuid item-uuid :item item}))))]
        result))))
