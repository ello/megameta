(ns megameta.redis
  (:import [redis.clients.jedis Jedis JedisPool]))


(def ^:dynamic *jedis-pool* (ref nil))



(defn initialize-pool
  "Takes a Hash of your redis configuration. Expects keys :host and :port to exist. Host should be a type String, port should be of type java.Lang.Long "
  [redis-config]
  (dosync (ref-set *jedis-pool* (JedisPool. (:host redis-config) (:port redis-config)))))

(defn finalize-pool
  "Closes the Jedis pool"
  []
  (.destroy *jedis-pool*))

(defn connect
  "Connect to an already initialized Jedis pool. Requires the pool to be initialized first."
  []
  (let [jedis (.getResource @*jedis-pool*)]
    (.select jedis 0)
    jedis ))

(defn disconnect
  "Disconnect from the Jedis Pool"
  [jedis]
  (.returnResource @*jedis-pool* jedis))

(def jedis)

(defmacro with-jedis [& exprs]
  `(do
     (binding [jedis (connect)]
       (let [result# ~@exprs]
         (disconnect jedis)
         result#))))

(defn -set
  ([jedis* key value] (.set jedis* key value))
  ([key value] (-set jedis key value)))

(defn -get
  ([jedis* key] (.get jedis* key))
  ([key] (-get jedis key)))

(defn -del
  ([jedis* key] (.del jedis* (into-array [key])))
  ([key] (-del jedis key)))

(defn -sadd
  ([jedis* set-key value] (.sadd jedis* set-key value))
  ([set-key value] (.sadd jedis set-key value)))

(defn -incr
  ([jedis* key] (.incr jedis* key))
  ([key] (.incr jedis key)))

(defn -lpush
  ([jedis* list-key value] (.lpush jedis* list-key value))
  ([list-key value] (.lpush jedis list-key value)))

(defn -rpop
  ([jedis* list-key] (.rpop jedis* list-key))
  ([list-key] (.rpop jedis list-key)))
