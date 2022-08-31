(ns sol-fut.logic.validate-code
  (:require [schema.core :as s]
            [sol-fut.models.login-model :as models.login]
            [clj-time.core :as t]
            [clj-time.coerce :as c]))

(s/defn is-valid-code? [code :- s/Str
                        login :- models.login/Login] :- s/Bool
  (and (= (:login/code login) code)
       (:login/code-is-valid login)
       (t/after? (c/from-date (:login/code-expires-at login)) (t/now))))