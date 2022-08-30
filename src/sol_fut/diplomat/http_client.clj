(ns sol-fut.diplomat.http-client
  (:require [clj-http.client :as client]))

(def uri-whatsapp-send-code "http://localhost:5000/send")
(def client-id-whatsapp-send-code "clientid")
(def access-key-whatsapp-send-code "accesskey")

(defn send-code[phone message]
  (client/post uri-whatsapp-send-code {:form-params {:clientId  client-id-whatsapp-send-code
                                                     :accessKey access-key-whatsapp-send-code
                                                     :phone     phone
                                                     :message   message}
                                       :content-type :json}))