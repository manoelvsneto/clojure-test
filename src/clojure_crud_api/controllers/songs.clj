(ns clojure-crud-api.controllers.books
  (:require [clojure-crud-api.repository.books :as books]
            [compojure.core :refer [defroutes GET POST PUT DELETE]]
            [compojure.coercions :refer [as-int]]
            [ring.util.response :refer [response]]))

(defn getAllbooks [] (books/getAllbooks))

(defn getbooksFromauthor [authorId] (books/getbooksByauthorId authorId))

(defn getbook [id] (books/getbook id))

(defn createbook [book] (books/createbook book))

(defn updatebook [id book] (books/updatebook id book))

(defn deletebook [bookId] (books/deletebook bookId))


(defroutes routes
  (GET "/books" []
    (response
     (getAllbooks)))
  (GET "/books/author/:authorId{[0-9]+}" [authorId :<< as-int]
    (response
     (getbooksFromauthor authorId)))
  (GET "/books/:bookId{[0-9]+}" [bookId :<< as-int]
    (response
     (getbook bookId)))
  (POST "/books" request
    (response
     (createbook (request :body))))
  (PUT "/books/:bookId{[0-9]+}" [bookId :<< as-int :as request]
    (response
     (updatebook bookId (request :body))))
  (DELETE "/books/:bookId{[0-9]+}" [bookId :<< as-int]
    (response
     (deletebook bookId))))