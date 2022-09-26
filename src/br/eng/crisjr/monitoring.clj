(ns br.eng.crisjr.monitoring
  (:require [clojure.java.shell :refer [sh]]))

(defn get-ip-address []
  ; TODO complete me!
  (let [ifconfig (:out (sh "ifconfig"))]
    (println ifconfig)
    "77.77.77.77"))
