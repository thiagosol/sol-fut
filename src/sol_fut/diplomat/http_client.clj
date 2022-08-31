(ns sol-fut.diplomat.http-client
  (:require [clj-http.client :as client]
            [sol-fut.config :as config]
            [clojure.data.json :as json]))

(def uri-whatsapp-send-code  (config/config-by :uri-whatsapp-send-code))
(def client-id-whatsapp-send-code (config/config-by :client-id-whatsapp-send-code))
(def access-key-whatsapp-send-code (config/config-by :access-key-whatsapp-send-code))

(defn send-code[phone message]
  (json/read-str (:body (client/post uri-whatsapp-send-code
                                     {:form-params  {:clientId  client-id-whatsapp-send-code
                                                     :accessKey access-key-whatsapp-send-code
                                                     :phone     phone
                                                     :message   message}
                                      :content-type :json})) :key-fn keyword))