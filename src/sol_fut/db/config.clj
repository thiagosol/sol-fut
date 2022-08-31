(ns sol-fut.db.config
  (:require [datomic.api :as d]
            [sol-fut.db.schemas :as db.schemas]
            [sol-fut.config :as config]))

(def db-uri (config/config-by :db-uri))

(defn conn []
  (d/connect db-uri))

(defn start []
  (d/create-database db-uri)
  (db.schemas/create-schema (conn)))

(defn drop-db! []
  (d/delete-database db-uri))