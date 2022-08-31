(ns sol-fut.controllers.login-send-code
  (:require [sol-fut.logic.generate-code :as l.g.code]
            [sol-fut.db.login-db :as db.login]
            [sol-fut.models.login-model :as models.login]
            [sol-fut.diplomat.http-client :as http-client])
  (:import (java.util UUID)))

(defn create-and-send-code! [uuid phone]
  (let [code               (l.g.code/generate-code)
        code-expires-at    (l.g.code/generate-code-expires-at)
        login              (models.login/new-login uuid phone code code-expires-at)
        message            (str "O seu código de ativação é: " code)
        response-send-code (http-client/send-code phone message)]
    (when (= (:message response-send-code) "Sucesso")
      (db.login/upsert! login))))

(defn execute! [{:keys [data]}]
  (let [login (db.login/login-by-phone (:phone data))
        login-id (:login/id login (UUID/randomUUID))]
    (if (l.g.code/can-generate-new-code? login)
      (do (create-and-send-code! login-id (:phone data))
          {:status 201 :body {:message "Código criado e enviado no whatsapp."}})
      {:status 400 :body {:message "Código já enviado, tente novamente mais tarde."}})))