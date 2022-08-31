(defproject sol-fut "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.1"]
                 [io.pedestal/pedestal.service       "0.5.10"]
                 [io.pedestal/pedestal.route       "0.5.10"]
                 [io.pedestal/pedestal.jetty         "0.5.10"]
                 [org.clojure/data.json "2.4.0"]
                 [org.slf4j/slf4j-simple "2.0.0"]
                 [com.datomic/datomic-free "0.9.5697"]
                 [prismatic/schema "1.4.0"]
                 [clj-http "3.12.3"]
                 [clj-time "0.15.2"]]

  :main sol-fut.core
  :repl-options {:init-ns sol-fut.core})
