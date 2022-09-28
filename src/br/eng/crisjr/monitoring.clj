(ns br.eng.crisjr.monitoring
  (:require [clojure.java.shell :refer [sh]]
            [clojure.string :as string]))

(defn get-ip-address []
  (reduce (fn [result line]
            (or result
                (let [maybe-match (re-find #"inet 192\.168\.\d+\.\d+" line)]
                  (if (nil? maybe-match)
                    nil
                    (-> maybe-match
                        (string/split #"\s")
                        second)))))
          nil
          (-> "ifconfig"
              sh
              :out
              string/split-lines)))
