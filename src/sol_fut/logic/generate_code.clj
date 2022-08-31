(ns sol-fut.logic.generate-code
  (:require [schema.core :as s]
            [sol-fut.models.login-model :as models.login]
            [clj-time.core :as t]
            [clj-time.coerce :as c]
            [sol-fut.config :as config])
  (:import (java.util Date)))

(def time-expires-whatsapp-code-in-minutes  (config/config-by :time-expires-whatsapp-code-in-minutes))

(defn code-layout-random [acc _]
  (if (= acc 0)
    (str (rand-int 10) (rand-int 10))
    (str acc (rand-int 10))))

(defn generate-code []
  (let [layout [0 0 0 0]]
    (reduce code-layout-random layout)))

(s/defn can-generate-new-code? [login :- models.login/Login] :- s/Bool
  (or (nil? login)
      (not (:login/code-is-valid login))
      (t/after? (t/now) (c/from-date (:login/code-expires-at login)))))

(s/defn generate-code-expires-at [] :- Date
  (c/to-date (t/plus (t/now) (t/minutes time-expires-whatsapp-code-in-minutes))))