(ns clojure-crud-api.repository.books
  (:require [clojure.java.jdbc :as sql]
            [clojure-crud-api.repository.migrations :refer [db]]))

(defn createbook [book]
  (->
   (sql/insert! db :books book)
   (first)))

(defn getAllbooks []
  (into []
        (sql/query db ["select top 1000 * from books order by id desc "])))

(defn getbooksByauthorId [authorId]
  (into []
        (sql/query db ["select top 1000 * from books where author_id = ? order by id desc", authorId])))

(defn getbook [id]
  (first
   (sql/query db ["select * from books where id = ?" id])))

(defn updatebook [id, book]
  (sql/update! db :books book ["id = ?" id])
  (getbook id))

(defn deletebook [id]
  (sql/delete! db :books ["id = ?" id])
  [])