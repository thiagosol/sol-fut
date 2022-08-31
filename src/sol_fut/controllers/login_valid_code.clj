(ns sol-fut.controllers.login-valid-code
  (:require [sol-fut.db.login-db :as db.login]
            [sol-fut.logic.validate-code :as l.v.code]
            [schema.core :as s]
            [sol-fut.models.login-model :as models.login]))

(s/defn update-login! [login :- models.login/Login]
  (-> login
      (assoc :login/code-is-valid false)
      db.login/upsert!))

(defn execute! [{:keys [data]}]
  (let [phone (:phone data)
        code  (:code data)
        login (db.login/login-by-phone-and-code phone code)]
    (if (l.v.code/is-valid-code? code login)
      (do (update-login! login)
          {:status 200 :body {:id    (:login/id login)
                            :token "781hn9j1ab"}})

      {:status 404 :body {:message "Telefone ou código inválido."}})))