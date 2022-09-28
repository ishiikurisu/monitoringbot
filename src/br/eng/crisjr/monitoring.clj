(ns br.eng.crisjr.monitoring
  (:require [clojure.java.shell :refer [sh]]
            [clojure.string :as string]))


(defn get-ip-address []
  ; TODO complete me!
  (let [ifconfig (:out (sh "ifconfig"))
        lines (string/split-lines ifconfig)
        limit (count lines)]
    (loop [n 0]
      (if (= n limit) 
        nil
        (let [line (nth lines n)
              maybe-match (re-find #"inet 192\.168\.\d+\.\d+" line)]
          (if (nil? maybe-match)
            (recur (inc n))
            (second (string/split maybe-match #"\s"))))))))
