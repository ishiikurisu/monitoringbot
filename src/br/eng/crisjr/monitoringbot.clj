(ns br.eng.crisjr.monitoringbot
  (:require [morse.handlers :as h]
            [morse.api :as t]
            [morse.polling :as p])
  (:gen-class))

(def token (System/getenv "TELEGRAM_TOKEN"))

(h/defhandler bot-api
  (h/command-fn "hello" (fn [{{id :id :as chat} :chat}]
                          (do
                            (println "bot joined chat: " chat)
                            (t/send-text token id "hi!"))))
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
