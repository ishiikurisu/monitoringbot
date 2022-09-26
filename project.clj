(defproject br.eng.crisjr.monitoringbot "0.0.0"
  :description "Tiny monitoring bot"
  :url "http://www.crisjr.eng.br"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.11.1"]
                 [morse "0.4.3"]]
  :main ^:skip-aot br.eng.crisjr.monitoringbot
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
