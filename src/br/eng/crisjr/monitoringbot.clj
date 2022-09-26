(ns br.eng.crisjr.monitoringbot
  (:require [clojure.edn :as edn]
            [morse.handlers :as h]
            [morse.api :as t]
            [morse.polling :as p]
            [br.eng.crisjr.monitoring :as m])
  (:gen-class))

(def token (System/getenv "TELEGRAM_TOKEN"))
(def allowlist (-> "allowlist.edn" slurp edn/read-string))

(h/defhandler bot-api
  (h/command-fn "hello" (fn [{{id :id :as chat} :chat}]
                          (do
                            (println "--- # bot joined chat")
                            (println "who: " chat)
                            (t/send-text token id "hi!"))))
  (h/command-fn "ip" (fn [{{id :id :as chat} :chat}]
                       (let [ip (m/get-ip-address)
                             msg (if (some #{id} allowlist)
                                   (str "IP: " ip)
                                   "You are not allowed to do that")]
                         (println "--- # internal IP")
                         (println (str "who: " chat))
                         (t/send-text token id msg))))
  (h/message message (println "received message")))

(def channel (p/start token bot-api))
(.addShutdownHook (Runtime/getRuntime) 
                  (Thread. (fn [] 
                    (do
                      (p/stop channel)
                      (println "# shutting down")))))
 
(defn -main
  "Bot entry point"
  [& args]
  (println "# bot entry point")
  (while true nil))
