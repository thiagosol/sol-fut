(ns sol-fut.db.login-db
  (:require [datomic.api :as d]
            [sol-fut.db.config :as db.config]
            [sol-fut.db.schemas :as db.schemas]
            [schema.core :as s]
            [sol-fut.models.login-model :as models.login]))

(s/defn login-by-phone [phone] :- (s/maybe models.login/Login)
        (let [result (d/pull (d/db (db.config/conn)) '[*] [:login/phone phone])
              login (db.schemas/->entity result)]
          (when (:login/phone login)
            login)))

(s/defn login-by-phone-and-code [phone, code] :- (s/maybe models.login/Login)
  (db.schemas/->entity
    (d/q '[:find (pull ?login [*]) .
           :in $ ?phone ?code
           :where [?login :login/phone ?phone]
                  [?login :login/code  ?code]] (d/db (db.config/conn)) phone code)))

(s/defn upsert! [login :- models.login/Login]
  (d/transact (db.config/conn) [login]))