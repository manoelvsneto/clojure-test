(ns clojure-crud-api.controllers.authors
  (:require [clojure-crud-api.repository.authors :as authors]
            [compojure.core :refer [defroutes GET POST PUT DELETE]]
            [compojure.coercions :refer [as-int]]
            [ring.util.response :refer [response]]))

(defn getAllauthors [] (authors/getAllauthors))

(defn getauthor [id] (authors/getauthor id))

(defn createauthor [author] (authors/createauthor author))

(defn updateauthor [id author] (authors/updateauthor id author))

(defn deleteauthor [id] (authors/deleteauthor id))


(defroutes routes
  (GET "/authors" []
    (response
     (getAllauthors)))
  (GET "/authors/:authorId{[0-9]+}" [authorId :<< as-int]
    (response
     (getauthor authorId)))
  (POST "/authors" request
    (response
     (createauthor (request :body))))
  (PUT "/authors/:authorId{[0-9]+}" [authorId :<< as-int :as request]
    (response
     (updateauthor authorId (request :body))))
  (DELETE "/authors/:authorId{[0-9]+}" [authorId :<< as-int]
    (response
     (deleteauthor authorId))))