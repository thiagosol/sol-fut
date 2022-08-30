(ns sol-fut.models.login-model
  (:require [schema.core :as s])
  (:import (java.util UUID)))

(defn uuid [] (UUID/randomUUID))

(def Login
  {:login/id                              UUID
   (s/optional-key :login/phone)          s/Str
   (s/optional-key :login/code)           s/Str})

(s/defn new-login :- Login
        ([phone code]
         (new-login (uuid) phone code))
        ([uuid phone code]
         {:login/id      uuid
          :login/phone    phone
          :login/code    code}))