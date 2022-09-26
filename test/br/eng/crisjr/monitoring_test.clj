(ns br.eng.crisjr.monitoring-test
  (:require [clojure.test :refer :all]
            [br.eng.crisjr.monitoring :refer :all]))

(defn- is-ip-address [ip]
  (->> (clojure.string/split ip #"\.")
       (map #(Integer/parseInt %))
       (map #(> 256 %))
       (reduce #(and %1 %2) true)))

(deftest ip-test
  (testing "can get a machine's IP address"
    (let [ip (get-ip-address)]
      (is (is-ip-address ip)))))
