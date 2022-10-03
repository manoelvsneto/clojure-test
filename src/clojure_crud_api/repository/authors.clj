(ns clojure-crud-api.repository.authors
  (:require [clojure.java.jdbc :as sql]
            [clojure-crud-api.repository.migrations :refer [db]]))

(defn createauthor [author]
  (->
   (sql/insert! db :authors author)
   (first)))

(defn getAllauthors []
  (into [] (sql/query db ["select top 1000 * from authors order by id desc"])))

(defn getauthor [id]
  (first (sql/query db ["select * from authors where id = ?" id])))

(defn updateauthor [id author]
  (sql/update! db :authors author ["id = ?" id])
  (getauthor id))

(defn deleteauthor [id]
  (sql/delete! db :authors ["id = ?" id])
  [])