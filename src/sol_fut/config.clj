(ns sol-fut.config)

(def config-map
  {:db-uri
   (or (get-in (System/getenv) ["SOL_FUT_URI_DB"])
               "datomic:free://localhost:4334/sol-fut?user=admin&password=datomic")

   :uri-whatsapp-send-code
   (or (get-in (System/getenv) ["SOL_FUT_WHATSAPP_URI_SEND_CODE"])
               "http://localhost:5000/send")

   :client-id-whatsapp-send-code
   (or (get-in (System/getenv) ["SOL_FUT_WHATSAPP_CLIENT_ID_SEND_CODE"])
               "clientid")

   :access-key-whatsapp-send-code
   (or (get-in (System/getenv) ["SOL_FUT_WHATSAPP_ACCESS_KEY_SEND_CODE"])
               "accesskey")

   :time-expires-whatsapp-code-in-minutes
   (or (read-string (get-in (System/getenv) ["SOL_FUT_WHATSAPP_EXPIRES_CODE_IN_MINUTES"]))
       10)})

(defn config-by [key]
  (key config-map))