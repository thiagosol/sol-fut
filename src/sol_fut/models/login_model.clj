(ns sol-fut.models.login-model
  (:require [schema.core :as s])
  (:import (java.util UUID)
           (java.util Date)))

(def Login
  {:login/id                               UUID
   (s/optional-key :login/phone)           s/Str
   (s/optional-key :login/code)            s/Str
   (s/optional-key :login/code-expires-at) Date
   (s/optional-key :login/code-is-valid)   s/Bool})

(s/defn new-login :- Login
  [uuid phone code code-expires-at]
  {:login/id              uuid
   :login/phone           phone
   :login/code            code
   :login/code-expires-at code-expires-at
   :login/code-is-valid   true})