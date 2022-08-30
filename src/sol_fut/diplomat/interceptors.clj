(ns sol-fut.diplomat.interceptors
  (:require [clojure.data.json :as json]
            [io.pedestal.http.body-params :as body-params]))

(def request-json-params-to-data
  {:name :interceptor-request-json-params-to-data
   :enter
   (fn [context]
     (assoc-in context [:request :data] (get-in context [:request :json-params])))})

(def response-convert-to-json
  {:name :interceptor-response-json
   :leave
   (fn [context]
     (-> context
         (assoc-in [:response :body] (json/write-str (get-in context [:response :body])))
         (assoc-in [:response :headers] {"Content-Type" "application/json"})))})

(def common-interceptors
  [(body-params/body-params)
   request-json-params-to-data
   response-convert-to-json])

