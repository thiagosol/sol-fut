(ns sol-fut.core
  (:require [sol-fut.diplomat.http_server :as diplomat.http_server]
            [sol-fut.db.config :as db.config]))

;(db.config/drop-db!)
(db.config/start)
(diplomat.http_server/start)