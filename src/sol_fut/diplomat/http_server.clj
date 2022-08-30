(ns sol-fut.diplomat.http_server
  (:require [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            [sol-fut.diplomat.interceptors :as interceptors]
            [sol-fut.controllers.login-send-code :as login-send-code]
            [sol-fut.controllers.login-valid-code :as login-valid-code]))

(def routes
  (route/expand-routes
    #{["/login/send-code"  :post
       (conj interceptors/common-interceptors
             login-send-code/execute!)
       :route-name :login.send-code]

      ["/login/valid-code" :post
       (conj interceptors/common-interceptors
             login-valid-code/execute!)
       :route-name :login.valid-code]}))

(defn create-server []
  (http/create-server
    {::http/routes routes
     ::http/type   :jetty
     ::http/port   8890}))

(defn start []
  (http/start (create-server)))