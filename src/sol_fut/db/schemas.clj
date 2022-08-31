(ns sol-fut.db.schemas
  (:require [datomic.api :as d]
            [clojure.walk :as walk]))

(def schema [; Login
             {:db/ident       :login/id
              :db/valueType   :db.type/uuid
              :db/cardinality :db.cardinality/one
              :db/unique      :db.unique/identity}

             {:db/ident       :login/phone
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/unique      :db.unique/identity}

             {:db/ident       :login/code
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one}

             {:db/ident       :login/code-expires-at
              :db/valueType   :db.type/instant
              :db/cardinality :db.cardinality/one}

             {:db/ident       :login/code-is-valid
              :db/valueType   :db.type/boolean
              :db/cardinality :db.cardinality/one}])

(defn dissoc-db-id [entity]
  (if (map? entity)
    (dissoc entity :db/id)
    entity))

(defn ->entity [entities]
  (walk/prewalk dissoc-db-id entities))

(defn create-schema [conn]
  (d/transact conn schema))