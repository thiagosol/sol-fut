(ns sol-fut.db.config
  (:require [datomic.api :as d]
            [sol-fut.db.schemas :as db.schemas]))

(def db-uri (or (get-in (System/getenv) ["SOL_FUT_URI_DB"])
                "datomic:free://localhost:4334/sol-fut?user=admin&password=datomic"))

(defn conn []
  (d/connect db-uri))

(defn start []
  (d/create-database db-uri)
  (db.schemas/create-schema (conn)))

(defn drop-db! []
  (d/delete-database db-uri))