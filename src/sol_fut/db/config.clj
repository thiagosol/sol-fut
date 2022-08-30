(ns sol-fut.db.config
  (:require [datomic.api :as d]
            [sol-fut.db.schemas :as db.schemas]))

(def db-uri "datomic:free://localhost:4334/sol-fut?password=datomic")

(defn conn []
  (d/connect db-uri))

(defn start []
  (d/create-database db-uri)
  (db.schemas/create-schema (conn)))

(defn drop-db! []
  (d/delete-database db-uri))