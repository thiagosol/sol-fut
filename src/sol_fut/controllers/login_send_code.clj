(ns sol-fut.controllers.login-send-code
  (:require [sol-fut.logic.generate-code :as logic.generate-code]
            [sol-fut.db.login-db :as db.login]
            [sol-fut.models.login-model :as models.login]
            [sol-fut.diplomat.http-client :as http-client]))

(defn create-and-send-code! [phone]
  (let [code    (logic.generate-code/generate-code)
        login   (models.login/new-login phone code)
        message (str "O seu código de ativação é: " code)]
    (db.login/upsert! login)
    (http-client/send-code phone message)))

(defn execute! [{:keys [data]}]
  (if (nil? (db.login/login-by-phone (:phone data)))
    (do (create-and-send-code! (:phone data))
        {:status 201 :body {:message "Código criado e enviado no whatsapp."}})
    {:status 400 :body {:message "Código já enviado, tente novamente mais tarde."}}))