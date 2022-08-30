(ns sol-fut.controllers.login-valid-code
  (:require [sol-fut.db.login-db :as db.login]))

(defn execute! [{:keys [data]}]
  (if-let [login (db.login/login-by-phone-and-code (:phone data) (:code data))]
    {:status 200 :body {:id    (:login/id login)
                        :token "781hn9j1a"}}
    {:status 404 :body {:message "Telefone ou código inválido."}}))